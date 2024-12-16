package com.example.testingstream;

import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import com.rabbitmq.stream.OffsetSpecification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class TestingStreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingStreamApplication.class, args);
    }

}

@Component
class SimpleStream{

    List<SimpleRecord> records;

    public SimpleStream() {
        this.records = new ArrayList<>();
    }

    @RabbitListener(queues = "foo")
    void counter(Message in, MessageHandler.Context context){
        records.add(new SimpleRecord(context.offset(), new String(in.getBodyAsBinary()).toUpperCase()));
    }

    public List<SimpleRecord> getRecords() {
        return records;
    }
}

record SimpleRecord(
        Long count,
        String message
){}

@Configuration
class SimpleConfig{
    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> rabbitListenerContainerFactory(Environment env) {
        var factory = new StreamRabbitListenerContainerFactory(env);
        factory.setNativeListener(true);
        factory.setConsumerCustomizer((id, builder) -> builder.offset(OffsetSpecification.first()));
        return factory;
    }
}