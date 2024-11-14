package com.example.receiver;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ReceiverRepository extends ListCrudRepository<ReceiverEntity, Integer> {
}
