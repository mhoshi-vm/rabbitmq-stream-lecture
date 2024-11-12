package com.example.consumer;

import org.springframework.data.annotation.Id;

record ConsumerRecord(@Id Integer demoId, String demoText, Integer demoValue) {
}
