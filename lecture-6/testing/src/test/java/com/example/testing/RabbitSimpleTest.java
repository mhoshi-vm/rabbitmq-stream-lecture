package com.example.testing;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RabbitSimpleTest {

    RabbitSimple rabbitSimple;

    @BeforeEach
    void setup(){
        rabbitSimple = new RabbitSimple();
    }

    @Test
    void simpleTest(){
        assertEquals(rabbitSimple.respond("hoge"), "HOGE");
    }

}