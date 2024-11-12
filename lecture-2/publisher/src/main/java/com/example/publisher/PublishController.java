package com.example.publisher;


import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class PublishController {

    RabbitTemplate rabbitTemplate;

    FanoutExchange fanoutExchange;

    public PublishController(RabbitTemplate rabbitTemplate, FanoutExchange fanoutExchange, MessageConverter messageConverter) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setMessageConverter(messageConverter);
        this.fanoutExchange = fanoutExchange;
    }

    @PostMapping
    void poster(@RequestParam String demoText, @RequestParam Integer demoValue){
        this.rabbitTemplate.convertAndSend(this.fanoutExchange.getName(), "", new PublishRecord(demoText, demoValue));
    }
}
