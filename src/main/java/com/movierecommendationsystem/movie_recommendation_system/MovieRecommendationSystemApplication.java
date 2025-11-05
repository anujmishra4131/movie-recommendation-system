package com.movierecommendationsystem.movie_recommendation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import com.movierecommendationsystem.movie_recommendation_system.repository.MovieRepository;

@SpringBootApplication
public class MovieRecommendationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieRecommendationSystemApplication.class, args);
        System.out.println("🚀 Movie Recommendation System started successfully!");
    }

    /**
     * Preloads some sample data into the database for testing purposes.
     */
    @Bean
    CommandLineRunner initDatabase(MovieRepository repo) {
        return args -> {
            if (repo.count() == 0) { // ✅ Prevent duplicate inserts on restart
                repo.save(new Movie(null, "Inception", "Sci-Fi", 9.0));
                repo.save(new Movie(null, "The Dark Knight", "Action", 9.1));
                repo.save(new Movie(null, "Interstellar", "Sci-Fi", 8.6));
                repo.save(new Movie(null, "Avengers: Endgame", "Action", 8.4));
                repo.save(new Movie(null, "The Hangover", "Comedy", 7.8));
                repo.save(new Movie(null, "Titanic", "Romance", 8.0));
                repo.save(new Movie(null, "Joker", "Drama", 8.5));
            }
            System.out.println("🎬 Sample movie data loaded successfully.");
        };
    }
}
