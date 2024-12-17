package com.example.uppercase;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
class ObservabilityController {

    ObservabilityRepository observabilityRepository;

    ObjectMapper objectMapper;

    public ObservabilityController(ObservabilityRepository observabilityRepository,ObjectMapper objectMapper) {
        this.observabilityRepository = observabilityRepository;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    List<ObservabilityRecord> getter(){
        return this.observabilityRepository.findAll();
    }

    @RabbitListener(queues = "stream1")
    void Listener(Message message) throws IOException {
        ObservabilityRecord record = this.objectMapper.readValue(new String(message.getBodyAsBinary()), ObservabilityRecord.class);
        this.observabilityRepository.save(record);
    }

}

@Configuration
class ObservabilityConfiguration{
    @Bean
    Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder().deserializerByType(ObservabilityRecord.class, new CustomerObjectDesializer());
    }
}


record ObservabilityRecord(
        @Id
        Integer demoId,
        String demoText,
        Integer demoValue
){}

@Repository
interface ObservabilityRepository extends ListCrudRepository<ObservabilityRecord, Integer>{}