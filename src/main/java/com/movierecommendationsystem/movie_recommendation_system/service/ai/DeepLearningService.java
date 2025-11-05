package com.movierecommendationsystem.movie_recommendation_system.service.ai;

import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.stereotype.Service;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.api.ndarray.INDArray;

import java.util.*;

@Service
public class DeepLearningService {

    // Converts genre into numeric value for NN input
    private double encodeGenre(String genre) {
        return switch (genre.toLowerCase()) {
            case "sci-fi" -> 1.0;
            case "action" -> 2.0;
            case "drama" -> 3.0;
            case "comedy" -> 4.0;
            default -> 0.0;
        };
    }

    // Train a very small neural network model to learn "rating" patterns
    public MultiLayerNetwork trainModel(List<Movie> movies) {
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .weightInit(WeightInit.XAVIER)
                .updater(new Adam(0.01))
                .list()
                .layer(new DenseLayer.Builder().nIn(2).nOut(5)
                        .activation(Activation.RELU).build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(5).nOut(1).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        // Prepare training data
        double[][] inputArr = new double[movies.size()][2];
        double[][] outputArr = new double[movies.size()][1];

        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            inputArr[i][0] = m.getRating();
            inputArr[i][1] = encodeGenre(m.getGenre());
            outputArr[i][0] = m.getRating(); // simple supervised pattern (demo)
        }

        INDArray input = Nd4j.create(inputArr);
        INDArray labels = Nd4j.create(outputArr);

        for (int epoch = 0; epoch < 20; epoch++) {
            model.fit(input, labels);
        }

        return model;
    }

    // Use trained model to predict user preference
    public double predictUserPreference(Movie movie, List<Movie> allMovies) {
        MultiLayerNetwork model = trainModel(allMovies);

        double genreVal = encodeGenre(movie.getGenre());
        INDArray input = Nd4j.create(new double[]{movie.getRating(), genreVal}, new int[]{1, 2});
        INDArray output = model.output(input);

        return output.getDouble(0);
    }

    // Recommend movies based on predicted preference
    public List<Movie> predictUserPreference(List<Movie> allMovies) {
        List<Movie> recommendations = new ArrayList<>();

        for (Movie m : allMovies) {
            double predicted = predictUserPreference(m, allMovies);
            if (predicted >= 7.5) { // threshold for "good" predicted rating
                recommendations.add(m);
            }
        }

        return recommendations;
    }
}
