package com.movierecommendationsystem.movie_recommendation_system.controller;

import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import com.movierecommendationsystem.movie_recommendation_system.service.MovieService;
import com.movierecommendationsystem.movie_recommendation_system.service.ai.AIRecommendationService;
import com.movierecommendationsystem.movie_recommendation_system.service.ai.DeepLearningService;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;
    private final AIRecommendationService aiService;
    private final DeepLearningService deepLearningService;

    public MovieController(MovieService movieService,
                           AIRecommendationService aiService,
                           DeepLearningService deepLearningService) {
        this.movieService = movieService;
        this.aiService = aiService;
        this.deepLearningService = deepLearningService;
    }

    // ✅ Get all movies
    @GetMapping("/getAll")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // ✅ Add a new movie
    @PostMapping("/addnew")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    // ✅ Get AI-based (Weka) recommendations
    @GetMapping("/recommend/ai")
    public List<Movie> getAIRecommendations() {
        return movieService.getAIRecommendations();
    }

    // ✅ Get Deep Learning–based recommendations
    @GetMapping("/recommend/deeplearning")
    public List<Movie> getDeepLearningRecommendations() {
        return movieService.getDeepLearningRecommendations();
    }

    // ✅ Predict rating using Weka Linear Regression
    @PostMapping("/predict/weka")
    public double predictRating(@RequestBody Movie movie) {
        try {
            List<Movie> allMovies = movieService.getAllMovies();
            return aiService.predictRating(movie, allMovies);
        } catch (Exception e) {
            throw new RuntimeException("Error predicting rating using AI model: " + e.getMessage());
        }
    }

    // ✅ Predict user preference using Deep Learning
    @PostMapping("/predict/deeplearning")
    public double predictUserPreference(@RequestBody Movie movie) {
        List<Movie> allMovies = movieService.getAllMovies();
        return deepLearningService.predictUserPreference(movie, allMovies);
    }
}
