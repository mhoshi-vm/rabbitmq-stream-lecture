package com.example.consumer;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ConsumerRepository extends ListCrudRepository<ConsumerRecord, Integer> {
}
