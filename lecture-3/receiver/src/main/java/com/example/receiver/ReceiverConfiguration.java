package com.example.receiver;

import com.rabbitmq.stream.Address;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
//import org.springframework.amqp.core.*;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.boot.autoconfigure.amqp.EnvironmentBuilderCustomizer;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.rabbit.stream.support.StreamAdmin;

@Configuration
class ReceiverConfiguration {

    @Bean
    StreamAdmin streamAdmin(Environment environment, CachingConnectionFactory cachingConnectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);
        return new StreamAdmin(environment, streamCreator -> {
            streamCreator.stream("stream1").create();
            rabbitAdmin.declareBinding(BindingBuilder.bind(new Queue("stream1")).to(new FanoutExchange("handson")));
            rabbitAdmin.initialize();
        });
    }

    @Bean
    EnvironmentBuilderCustomizer environmentBuilderCustomizer(RabbitProperties rabbitProperties) {
        RabbitProperties.Stream rabbitStreamProperties = rabbitProperties.getStream();
        Address entryPoint = new Address(rabbitStreamProperties.getHost(), rabbitStreamProperties.getPort());
        return builder -> builder
                .addressResolver(address -> entryPoint );
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> rabbitListenerContainerFactory(Environment env) {
        var factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
        return factory;
    }
}
