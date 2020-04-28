package com.number.lover;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.number.lover.model.TelPhoneNumber;
import com.number.lover.service.ProducerService;
import com.number.lover.service.RedisRepository;
import com.number.lover.util.UtilTool;

@Component
public class FileWatch {
    private static final Logger log = LogManager.getLogger(FileWatch.class);

    @Value("${mq.server.msg_receive_dir}")
    String watchDir;
    @Autowired
    ProducerService producerService;
    @Autowired
    RedisRepository redisRepository;

    @Scheduled(cron = "0 0/10 * * * ?", initialDelay = 60000 * 1) // 每五分钟执行一次，第一启动延迟1分钟执行
    private void processFile() throws Exception {
        String jsonFileContent;
        List<TelPhoneNumber> list = null;
        log.info("处理监控文件夹：{}下面的所有文件。", watchDir);
        List<String> resultList = UtilTool.getFiles(watchDir);
        for (String file : resultList) {
            jsonFileContent = UtilTool.readFile(file);
            list = JSON.parseArray(jsonFileContent, TelPhoneNumber.class);
            UtilTool.deleteFile(file);
        }

        for (TelPhoneNumber telPhoneNumber : list) {
            producerService.sendToDirectExchangeAllNumbers(telPhoneNumber);
            if (redisRepository.existCheck(telPhoneNumber.getLoverNumber())) {
                producerService.sendToDirectExchangeLoverNumbers(telPhoneNumber);
                continue;
            }
            redisRepository.addLoverNumber(telPhoneNumber.getLoverNumber());

        }
    }

    public void watch() throws Exception {
        try {
            processFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
