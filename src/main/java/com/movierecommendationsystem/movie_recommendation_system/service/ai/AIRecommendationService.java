package com.movierecommendationsystem.movie_recommendation_system.service.ai;

import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import org.springframework.stereotype.Service;
import weka.classifiers.functions.LinearRegression;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

@Service
public class AIRecommendationService {

    // Helper: Encode genre to numeric value
    private double encodeGenre(String genre) {
        if (genre == null) return 0.0;
        return switch (genre.toLowerCase()) {
            case "sci-fi" -> 1.0;
            case "action" -> 2.0;
            case "drama" -> 3.0;
            case "comedy" -> 4.0;
            case "romance" -> 5.0;
            case "thriller" -> 6.0;
            default -> 0.0;
        };
    }

    // Train model on all movie data
    private LinearRegression trainModel(List<Movie> allMovies) throws Exception {
        // Define attributes: genre (numeric), rating (target)
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("genre"));
        attributes.add(new Attribute("rating"));

        Instances dataset = new Instances("MovieData", attributes, allMovies.size());
        dataset.setClassIndex(1);

        for (Movie movie : allMovies) {
            double genreVal = encodeGenre(movie.getGenre());
            double rating = movie.getRating();
            Instance instance = new DenseInstance(2);
            instance.setValue(attributes.get(0), genreVal);
            instance.setValue(attributes.get(1), rating);
            dataset.add(instance);
        }

        LinearRegression model = new LinearRegression();
        model.buildClassifier(dataset);
        return model;
    }

    // Predict a movie’s expected rating using the trained model
    public double predictRating(Movie movie, List<Movie> allMovies) throws Exception {
        LinearRegression model = trainModel(allMovies);

        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("genre"));
        attributes.add(new Attribute("rating"));
        Instances dataset = new Instances("MovieData", attributes, 1);
        dataset.setClassIndex(1);

        Instance instance = new DenseInstance(2);
        instance.setValue(attributes.get(0), encodeGenre(movie.getGenre()));
        instance.setDataset(dataset);

        return model.classifyInstance(instance);
    }

    // Recommend movies based on predicted ratings
    public List<Movie> recommendMovies(List<Movie> allMovies) {
        List<Movie> recommended = new ArrayList<>();

        try {
            LinearRegression model = trainModel(allMovies);
            ArrayList<Attribute> attributes = new ArrayList<>();
            attributes.add(new Attribute("genre"));
            attributes.add(new Attribute("rating"));
            Instances dataset = new Instances("MovieData", attributes, 1);
            dataset.setClassIndex(1);

            for (Movie movie : allMovies) {
                Instance instance = new DenseInstance(2);
                instance.setValue(attributes.get(0), encodeGenre(movie.getGenre()));
                instance.setDataset(dataset);

                double predicted = model.classifyInstance(instance);
                // Add if predicted rating >= average actual rating
                double avgRating = allMovies.stream()
                        .mapToDouble(Movie::getRating)
                        .average()
                        .orElse(0.0);

                if (predicted >= avgRating) {
                    recommended.add(movie);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return recommended;
    }
}
