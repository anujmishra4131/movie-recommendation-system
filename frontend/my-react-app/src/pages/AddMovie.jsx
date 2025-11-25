import React, { useState } from "react";
import { addMovie } from "../services/api";

const AddMovie = () => {
  const [movie, setMovie] = useState({
    title: "",
    genre: "",
    rating: "",
    year: "",
  });

  const handleChange = (e) => {
    setMovie({ ...movie, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addMovie(movie);
    alert("✅ Movie added successfully!");
    setMovie({ title: "", genre: "", rating: "", year: "" });
  };

  return (
    <div className="container">
      <h2 style={{ textAlign: "center", margin: "20px 0" }}>Add New Movie</h2>
      <form
        onSubmit={handleSubmit}
        style={{ maxWidth: "400px", margin: "0 auto", display: "flex", flexDirection: "column", gap: "10px" }}
      >
        <input name="title" placeholder="Title" value={movie.title} onChange={handleChange} required />
        <input name="genre" placeholder="Genre" value={movie.genre} onChange={handleChange} required />
        <input name="rating" placeholder="Rating" value={movie.rating} onChange={handleChange} required />
        <input name="year" placeholder="Year" value={movie.year} onChange={handleChange} required />
        <button type="submit">Add Movie</button>
      </form>
    </div>
  );
};

export default AddMovie;
