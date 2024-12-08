package com.example.testing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

}

@Component
class RabbitSimple{

    @RabbitListener(id = "foo", queues = "foo")
    String respond(String foo) {
        System.out.println("AAAAAA");
        return foo.toUpperCase();
    }
}
