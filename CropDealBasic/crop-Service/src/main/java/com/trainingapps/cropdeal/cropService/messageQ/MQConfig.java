package com.trainingapps.cropdeal.cropService.messageQ;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MQConfig {

    public static final String PRIMARY_MESSAGE_ROUTING_KEY = "primary message routing key";
    public static final String ORDER_MESSAGE_ROUTING_KEY = "order message routing key";
    public static final String CROP_MESSAGE_ROUTING_KEY = "crop message routing key";
    public static final String USER_MESSAGE_ROUTING_KEY = "user message routing key";
    public static final String ORDERQ = "Orderq";
    public static final String CROPQ = "Cropq";
    public static final String USERQ = "Userq";
    public static final String PRIMARYQ = "Primaryq";
    public static final String ORDERE = "Ordere";
    public static final String CROPE = "Crope";
    public static final String USERE = "Usere";
    public static final String PRIMARYE = "Primarye";

    @Bean
    public Queue orderQueue(){
        return new Queue(ORDERQ);
    }

    @Bean
    public Queue cropQueue(){
        return new Queue(CROPQ);
    }

    @Bean
    public Queue userQueue(){
        return new Queue(USERQ);
    }

    @Bean
    public Queue primaryQueue(){
        return new Queue(PRIMARYQ);
    }
    @Bean
    public TopicExchange orderExchange(){
        return new TopicExchange(ORDERE);
    }
    @Bean
    public TopicExchange cropExchange(){
        return new TopicExchange(CROPE);
    }
    @Bean
    public TopicExchange userExchange(){
        return new TopicExchange(USERE);
    }
    @Bean
    public TopicExchange primaryExchange(){
        return new TopicExchange(PRIMARYE);
    }
    @Bean
    public Binding bindingPrimary(Queue primaryQueue, TopicExchange primaryExchange){
        return BindingBuilder
                .bind(primaryQueue)
                .to(primaryExchange)
                .with(PRIMARY_MESSAGE_ROUTING_KEY);
    }
    @Bean
    public Binding bindingOrder(Queue orderQueue, TopicExchange orderExchange){
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(ORDER_MESSAGE_ROUTING_KEY);
    }
    @Bean
    public Binding bindingCrop(Queue cropQueue, TopicExchange cropExchange){
        return BindingBuilder
                .bind(cropQueue)
                .to(cropExchange)
                .with(CROP_MESSAGE_ROUTING_KEY);
    }
    @Bean
    public Binding bindingUser(Queue userQueue , TopicExchange userExchange){
        return BindingBuilder
                .bind(userQueue)
                .to(userExchange)
                .with(USER_MESSAGE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

}
