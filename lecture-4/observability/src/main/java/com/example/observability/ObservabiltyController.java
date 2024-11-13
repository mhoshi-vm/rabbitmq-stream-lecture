package com.example.observability;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ObservabilityController {

    ObservabilityRepository observabilityRepository;


    public ObservabilityController(ObservabilityRepository observabilityRepository) {
        this.observabilityRepository = observabilityRepository;
    }

    @RabbitListener(queues = "stream1")
    void Listener(Message in) throws JsonProcessingException {
        ObservabilityRecord observabilityRecord = new ObjectMapper().readValue(new String(in.getBodyAsBinary()), ObservabilityRecord.class);
        this.observabilityRepository.save(observabilityRecord);
    }

    @GetMapping
    List<ObservabilityRecord> getAll(){
        return this.observabilityRepository.findAll();
    }
}


record ObservabilityRecord(@Id Integer demoId, String demoText, Integer demoValue){}

@Repository
interface ObservabilityRepository extends ListCrudRepository<ObservabilityRecord, Integer>{}
