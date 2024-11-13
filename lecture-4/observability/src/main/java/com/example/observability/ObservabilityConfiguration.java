package com.example.observability;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@Configuration
class ObservabilityConfiguration {

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> rabbitListenerContainerFactory(Environment environment){
        var factory = new StreamRabbitListenerContainerFactory(environment);
        factory.setNativeListener(true);
        factory.setObservationEnabled(true);
        factory.setConsumerCustomizer(
                (id, builder)-> builder.offset(OffsetSpecification.first())
        );
        return factory;
    }

}
