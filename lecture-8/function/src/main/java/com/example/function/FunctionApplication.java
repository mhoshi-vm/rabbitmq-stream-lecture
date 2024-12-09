package com.example.function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@SpringBootApplication
public class FunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionApplication.class, args);
	}

}

@Component
class DemoFunction{

	@Bean
	Function<String, String> happy(){
		return (in) ->{
			return in + "Happy";
		};
	}
}
