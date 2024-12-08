package com.example.testing;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TestingApplicationTests {


    @Autowired
    RabbitSimple rabbitSimple;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        Assert.assertEquals(this.rabbitTemplate.convertSendAndReceive("foo","foo"), "FOO");
    }
}
