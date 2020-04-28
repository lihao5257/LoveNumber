package com.number.lover.service;

public interface RedisRepository {

    boolean existCheck(String checkedNumber);

    void addLoverNumber(String loverNumber);
}
