package com.movierecommendationsystem.movie_recommendation_system.service;



import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import com.movierecommendationsystem.movie_recommendation_system.repository.MovieRepository;
import com.movierecommendationsystem.movie_recommendation_system.service.ai.AIRecommendationService;
import com.movierecommendationsystem.movie_recommendation_system.service.ai.DeepLearningService;

import org.springframework.stereotype.Service;

import java.util.List;
// import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepo;
    private final AIRecommendationService aiService;
    private final DeepLearningService deepLearningService;

    public MovieService(MovieRepository movieRepo, AIRecommendationService aiService, DeepLearningService deepLearningService) {
        this.movieRepo = movieRepo;
        this.aiService = aiService;
        this.deepLearningService = deepLearningService;
    }


    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

   // Normal genre-based ai recommendation
   public List<Movie> getAIRecommendations() {
    List<Movie> allMovies = movieRepo.findAll();
    return aiService.recommendMovies(allMovies);
}
     // Deep learning based recommendation
    public List<Movie> getDeepLearningRecommendations() {
    List<Movie> allMovies = movieRepo.findAll();
    return deepLearningService.predictUserPreference(allMovies);
}

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }
}

