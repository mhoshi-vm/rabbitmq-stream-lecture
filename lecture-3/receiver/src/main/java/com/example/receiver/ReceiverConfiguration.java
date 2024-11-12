package com.example.receiver;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@Configuration
class ReceiverConfiguration {

    @Bean
    FanoutExchange exchange(){
        return new FanoutExchange("handson");
    }

    @Bean
    Queue queue(){
        return QueueBuilder.durable("stream1").stream().build();
    }

    @Bean
    Binding binding(Queue queue, FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }
    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> nativeFactory(Environment env) {
        var factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
        return factory;
    }
}
