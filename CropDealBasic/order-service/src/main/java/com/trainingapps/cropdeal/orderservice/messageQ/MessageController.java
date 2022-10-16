package com.trainingapps.cropdeal.orderservice.messageQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/publish")
public class MessageController {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping("/order-message")
    public String orderMessagePublisher(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        customMessage.setMessage("Hello to fellow order exchanges and queues");
        rabbitTemplate.convertAndSend(MQConfig.ORDERE,
                MQConfig.ORDER_MESSAGE_ROUTING_KEY,
                customMessage);
        return "Message Published";
    }

    @PostMapping("/crop-message")
    public String cropMessagePublisher(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        customMessage.setMessage("Hello to fellow crop exchanges and queues");
        rabbitTemplate.convertAndSend(MQConfig.CROPE,
                MQConfig.CROP_MESSAGE_ROUTING_KEY,
                customMessage);
        return "Message Published";
    }

    @PostMapping("/user-message")
    public String userMessagePublisher(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        customMessage.setMessage("Hello to fellow user exchanges and queues");
        rabbitTemplate.convertAndSend(MQConfig.USERE,
                MQConfig.USER_MESSAGE_ROUTING_KEY,
                customMessage);
        return "Message Published";
    }
}