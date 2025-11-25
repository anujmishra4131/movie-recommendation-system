import React from "react";
import MovieCard from "./MovieCard";

const MovieGrid = ({ movies }) => {
  return (
    <div style={{ display: "flex", flexWrap: "wrap", justifyContent: "center" }}>
      {movies.map((movie) => (
        <MovieCard key={movie.id || movie.title} movie={movie} />
      ))}
    </div>
  );
};

export default MovieGrid;
