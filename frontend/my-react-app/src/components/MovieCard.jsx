import React from "react";

const MovieCard = ({ movie }) => {
  return (
    <div className="card">
      <h3>{movie.title}</h3>
      <p>🎭 Genre: {movie.genre}</p>
      <p>⭐ Rating: {movie.rating}</p>
      <p>📅 Year: {movie.year}</p>
    </div>
  );
};

export default MovieCard;
