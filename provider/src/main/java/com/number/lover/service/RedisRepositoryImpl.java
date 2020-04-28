package com.number.lover.service;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisRepositoryImpl implements RedisRepository {

    private static final Logger           log = LogManager.getLogger(RedisRepositoryImpl.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private SetOperations<String, String> setOperations;
    boolean                               checkFlag;

    /**
     * initialize SetOperations.
     */
    @PostConstruct
    private void init() {
        setOperations = redisTemplate.opsForSet();
    }

    /**
     * check if loverNumber already exists in set.
     */
    @Override
    public boolean existCheck(String checkedNumber) {
        checkFlag = setOperations.isMember("loverNumbers", checkedNumber);
        Set<String> resultSet = setOperations.members("loverNumbers");
        System.out.println("check resultSet:" + resultSet);
        log.info("checked loverNumber is ：{}, checked result is {}。", checkedNumber, checkFlag);
        return checkFlag;
    }

    /**
     * insert items into redisSet
     */
    @Override
    public void addLoverNumber(String loverNumber) {
        //        setOperations.intersect(loverNumber, setContainer);
        setOperations.add("loverNumbers", loverNumber);
        Set<String> resultSet = setOperations.members("loverNumbers");
        System.out.println("add resultSet:" + resultSet);
    }

}
