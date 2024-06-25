package com.example.kafka_client;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic-1", groupId = "my-group-id")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}