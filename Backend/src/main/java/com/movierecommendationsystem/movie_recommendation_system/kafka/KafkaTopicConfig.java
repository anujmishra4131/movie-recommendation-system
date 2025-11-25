package com.movierecommendationsystem.movie_recommendation_system.kafka;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String MOVIE_TOPIC = "movie-events";

    @Bean
    public NewTopic movieTopic() {
        return TopicBuilder.name(MOVIE_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
