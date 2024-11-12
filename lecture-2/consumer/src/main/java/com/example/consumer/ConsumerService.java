package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ConsumerService {

    ConsumerRepository consumerRepository;

    public ConsumerService(ConsumerRepository consumerRepository) {
        this.consumerRepository = consumerRepository;
    }

    @RabbitListener(queues = "queue1")
    void Listener(String data) throws JsonProcessingException {
        ConsumerRecord consumerRecord = new ObjectMapper().readValue(data, ConsumerRecord.class);
        consumerRepository.save(consumerRecord);
    }

    List<ConsumerRecord> findAll(){
      return consumerRepository.findAll();
    }

}
