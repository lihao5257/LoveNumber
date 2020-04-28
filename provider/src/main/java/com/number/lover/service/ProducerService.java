package com.number.lover.service;

import com.number.lover.model.TelPhoneNumber;

public interface ProducerService {

    void sendToDirectExchangeAllNumbers(TelPhoneNumber telPhoneNumber);

    void sendToDirectExchangeLoverNumbers(TelPhoneNumber telPhoneNumber);
    /*
     * void sendToTopicExchange(TelPhoneNumber telPhoneNumber, String topic); void
     * sendToFanoutExchange(TelPhoneNumber telPhoneNumber);
     */
}
