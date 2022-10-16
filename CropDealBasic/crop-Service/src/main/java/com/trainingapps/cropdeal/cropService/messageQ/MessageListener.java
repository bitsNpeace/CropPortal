package com.trainingapps.cropdeal.cropService.messageQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.CROPQ)
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
