package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ConsumerService {

    ConsumerRepository consumerRepository;

    ObjectMapper objectMapper;

    public ConsumerService(ConsumerRepository consumerRepository, ObjectMapper objectMapper) {
        this.consumerRepository = consumerRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "queue1")
    void Listener(String data) throws JsonProcessingException {
        ConsumerRecord consumerRecord = this.objectMapper.readValue(data, ConsumerRecord.class);
        consumerRepository.save(consumerRecord);
    }

    List<ConsumerRecord> findAll(){
      return consumerRepository.findAll();
    }

}
