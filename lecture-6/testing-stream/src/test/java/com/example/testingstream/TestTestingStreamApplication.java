package com.example.testingstream;

import org.springframework.boot.SpringApplication;

public class TestTestingStreamApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestingStreamApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
