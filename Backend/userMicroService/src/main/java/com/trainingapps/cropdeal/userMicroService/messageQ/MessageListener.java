package com.trainingapps.cropdeal.userMicroService.messageQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = MQConfig.USERQ)
    public void listener(CustomMessage message){
        System.out.println(message);
    }
}
