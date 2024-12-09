package com.example.consumers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@SpringBootApplication
public class ConsumersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumersApplication.class, args);
    }

}

@Component
class DemoConsumer{

    @Bean
    Consumer<String> stdout(){
        return System.out::println;
    }
}