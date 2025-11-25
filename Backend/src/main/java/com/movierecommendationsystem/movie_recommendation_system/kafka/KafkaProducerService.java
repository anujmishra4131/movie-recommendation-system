package com.movierecommendationsystem.movie_recommendation_system.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMovieEvent(String message) {
        kafkaTemplate.send("movie-events", message);
        System.out.println("🎬 Sent movie event to Kafka: " + message);
    }
}
