package com.number.lover.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.number.lover.rabbitmq.consumer.model.TelPhoneNumber;
import com.number.lover.rabbitmq.consumer.model.TelPhoneNumberToEs;

@Service
public class ConsumerService {

    private final String        INDEX_ALL_NUMBER   = "test001";
    private final String        INDEX_LOVER_NUMBER = "test002";

    @Autowired
    TelPhoneNumberDao           telPhoneNumberDao;
    private static final Logger LOGGER             = LoggerFactory.getLogger(ConsumerService.class);

    public static long          countNubmer        = 0;

    @RabbitListener(queues = "${queue.A}")
    public void handleQueueAMessageReception(TelPhoneNumber telPhoneNumber) {
        LOGGER.info(
            "Message received in Queue A : phoneNumer " + countNubmer++ + ":" + telPhoneNumber.getPhoneNumber());
        TelPhoneNumberToEs es = new TelPhoneNumberToEs();
        es.setLoverNumber(telPhoneNumber.getLoverNumber());
        es.setPhoneNumber(telPhoneNumber.getPhoneNumber());
        LOGGER.info("11");
        telPhoneNumberDao.insertPhoneNumber(es, INDEX_ALL_NUMBER);
    }

    @RabbitListener(queues = "${queue.B}")
    public void handleQueueBMessageReception(TelPhoneNumber telPhoneNumber) {
        LOGGER.info("Message received in Queue B : " + telPhoneNumber.getPhoneNumber());
        TelPhoneNumberToEs es = new TelPhoneNumberToEs();
        es.setLoverNumber(telPhoneNumber.getLoverNumber());
        es.setPhoneNumber(telPhoneNumber.getPhoneNumber());
        LOGGER.info("22");
        telPhoneNumberDao.insertPhoneNumber(es, INDEX_LOVER_NUMBER);
    }

}
