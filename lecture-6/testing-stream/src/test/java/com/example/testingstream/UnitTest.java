package com.example.testingstream;

import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import com.rabbitmq.stream.codec.SimpleCodec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UnitTest {

    SimpleStream rabbitSimple;


    @BeforeEach
    void setup() {
        rabbitSimple = new SimpleStream();
    }

    @Test
    void simpleTest() {
        MessageHandler.Context context = Mockito.mock(MessageHandler.Context.class);
        Mockito.when(context.offset()).thenReturn(0L);
        SimpleCodec simpleCodec = new SimpleCodec();
        Message message = simpleCodec.messageBuilder().addData("hoge".getBytes()).build();
        rabbitSimple.counter(message, context);
        Assertions.assertEquals(rabbitSimple.getRecords().get(0).message(), "HOGE");
    }
}