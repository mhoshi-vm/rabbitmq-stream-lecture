package com.example.consumer;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ConsumerConfiguration {

    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange("handson");
    }

    @Bean
    Queue queue(){
        return QueueBuilder.durable("queue1").build();
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

}
