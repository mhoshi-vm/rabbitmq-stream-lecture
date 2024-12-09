package com.example.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@SpringBootApplication
@EnableConfigurationProperties(SupplierApplicationProperties.class)
public class SupplierApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplierApplication.class, args);
    }

}

@Component
class DemoSupplier{

    String message;

    public DemoSupplier(SupplierApplicationProperties supplierApplicationProperties) {
        this.message = supplierApplicationProperties.message();
    }

    @Bean
    Supplier<String> hello(){
        return ()-> this.message;
    }
}
