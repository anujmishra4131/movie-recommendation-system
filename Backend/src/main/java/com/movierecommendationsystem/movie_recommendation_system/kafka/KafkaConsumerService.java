package com.movierecommendationsystem.movie_recommendation_system.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "movie-events", groupId = "movie_group")
    public void consumeMovieEvent(String message) {
        System.out.println("📩 Received movie event: " + message);
    }
}
