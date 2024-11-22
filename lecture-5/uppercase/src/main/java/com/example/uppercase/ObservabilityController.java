package com.example.uppercase;


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.rabbitmq.stream.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.annotation.Id;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
class ObservabilityController {

    ObservabilityRepository observabilityRepository;

    public ObservabilityController(ObservabilityRepository observabilityRepository) {
        this.observabilityRepository = observabilityRepository;
    }

    @GetMapping
    List<ObservabilityRecord> getter(){
        return this.observabilityRepository.findAll();
    }

    @RabbitListener(queues = "stream1")
    void Listener(Message message) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("CustomerObjectDesializer", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(ObservabilityRecord.class, new CustomerObjectDesializer());
        mapper.registerModule(module);


        ObservabilityRecord record = mapper.readValue(new String(message.getBodyAsBinary()), ObservabilityRecord.class);
        this.observabilityRepository.save(record);
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