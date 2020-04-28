package com.number.lover.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.number.lover.model.TelPhoneNumber;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Value("${exchange.direct}")
    private String directExchange;

    /*
     * @Value("${exchange.topic}") private String topicExchange;
     * 
     * @Value("${exchange.fanout}") private String fanoutExchange;
     */

    @Value("${routing.direct.1}")
    private String direct1RoutingKey;

    @Value("${routing.direct.2}")
    private String direct2RoutingKey;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendToDirectExchangeAllNumbers(TelPhoneNumber telPhoneNumber) {
        rabbitTemplate.convertAndSend(directExchange, direct1RoutingKey, telPhoneNumber);
    }

    @Override
    public void sendToDirectExchangeLoverNumbers(TelPhoneNumber telPhoneNumber) {
        rabbitTemplate.convertAndSend(directExchange, direct2RoutingKey, telPhoneNumber);
    }

    /*
     * @Override public void sendToTopicExchange(TelPhoneNumber telPhoneNumber,
     * String topic) { rabbitTemplate.convertAndSend(topicExchange, topic,
     * telPhoneNumber); }
     * 
     * @Override public void sendToFanoutExchange(TelPhoneNumber telPhoneNumber) {
     * rabbitTemplate.convertAndSend(fanoutExchange, "", telPhoneNumber); //
     * routingKey is ignored }
     */

}
