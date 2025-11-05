package com.movierecommendationsystem.movie_recommendation_system.repository;
import com.movierecommendationsystem.movie_recommendation_system.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
}

