package com.example.testingstream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class IntTest {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    SimpleStream simpleStream;

    @BeforeEach
    void setup() {
    }

    @Test
    void contextLoads() throws InterruptedException {


        for (int i = 0; i < 100; i++) {

            this.rabbitTemplate.convertAndSend("fanout", "", "aaa");
        }

        Thread.sleep(1000);

        List<SimpleRecord> records = simpleStream.getRecords();
        Assertions.assertEquals(records.size(), 100);

    }

}
