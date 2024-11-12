package com.example.receiver;

import org.springframework.data.annotation.Id;

record ReceiverRecord (@Id Integer demoId, String demoText, Integer demoValue){
}
