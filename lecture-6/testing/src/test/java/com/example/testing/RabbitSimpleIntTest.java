package com.example.testing;

import com.rabbitmq.client.Channel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.RabbitListenerTestHarness;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest(classes = {RabbitMQTestConfiguration.class})
class RabbitSimpleIntTest {

    private RabbitSimple rabbitSimple;

    @Autowired
    private RabbitListenerTestHarness harness;
    @Autowired
    private TestRabbitTemplate testRabbitTemplate;

    @BeforeEach
    void setup(){
        rabbitSimple = harness.getSpy("foo");
        assertNotNull(rabbitSimple);
    }

    @Test
    void simpleTest(){
        assertEquals(this.testRabbitTemplate.convertSendAndReceive("foo", "foo"), "FOO");
    }
}

