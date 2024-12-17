package com.example.receiver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.stream.Message;
import com.rabbitmq.stream.MessageHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
class ReceiverService {

    ReceiverRepository receiverRepository;

    ObjectMapper objectMapper;

    public ReceiverService(ReceiverRepository receiverRepository, ObjectMapper objectMapper) {
        this.receiverRepository = receiverRepository;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = "stream1")
    void test(Message in, MessageHandler.Context context) throws JsonProcessingException {

        ReceiverEntity record = this.objectMapper.readValue(new String(in.getBodyAsBinary()), ReceiverEntity.class);
        record.setDemoId(context.offset());
        record.setDemoTimestamp(new Date(context.timestamp()));

        receiverRepository.save(record);
    }

    List<ReceiverEntity> findAll(){
       return receiverRepository.findAll();
    }
}
