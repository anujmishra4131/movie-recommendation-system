import React, { useEffect, useState } from "react";
import { getAllMovies, getAIRecommendations, getDeepLearningRecommendations } from "../services/api";
import MovieGrid from "../components/MovieGrid";
import SearchHero from "../components/SearchHero";

const Home = () => {
  const [movies, setMovies] = useState([]);

  const fetchAllMovies = async () => {
    const data = await getAllMovies();
    setMovies(data);
  };

  const handleAIRecommend = async () => {
    const data = await getAIRecommendations();
    setMovies(data);
  };

  const handleDLRecommend = async () => {
    const data = await getDeepLearningRecommendations();
    setMovies(data);
  };

  useEffect(() => {
    fetchAllMovies();
  }, []);

  return (
    <div className="container">
      <h2 style={{ textAlign: "center", marginTop: "20px" }}>🎞️ Movie List</h2>
      <SearchHero onAIRecommend={handleAIRecommend} onDLRecommend={handleDLRecommend} />
      <MovieGrid movies={movies} />
    </div>
  );
};

export default Home;
