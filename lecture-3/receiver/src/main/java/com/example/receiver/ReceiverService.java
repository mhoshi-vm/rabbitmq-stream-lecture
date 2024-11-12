package com.example.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ReceiverService {

    ReceiverRepository receiverRepository;

    public ReceiverService(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    @RabbitListener(queues = "stream1", containerFactory = "nativeFactory")
    void test(Message in) throws JsonProcessingException {
        ReceiverRecord record = new ObjectMapper().readValue(new String(in.getBodyAsBinary()), ReceiverRecord.class);
        receiverRepository.save(record);
    }

    List<ReceiverRecord> findAll(){
       return receiverRepository.findAll();
    }
}
