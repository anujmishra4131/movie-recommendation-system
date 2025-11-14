import React, { useState } from "react";
import { addNewMovie } from "../services/api";

const AddMovieModal = ({ show, onClose, refreshMovies }) => {
  const [movie, setMovie] = useState({ title: "", genre: "", rating: 0 });

  if (!show) return null;

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await addNewMovie(movie);
      alert("✅ Movie added successfully!");
      refreshMovies();
      onClose();
    } catch (err) {
      alert("❌ Failed to add movie");
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
      <div className="bg-white dark:bg-gray-800 rounded-lg p-6 w-96">
        <h2 className="text-xl font-bold mb-4 dark:text-white">Add New Movie</h2>
        <form onSubmit={handleSubmit} className="space-y-3">
          <input
            type="text"
            placeholder="Title"
            className="w-full p-2 border rounded"
            onChange={(e) => setMovie({ ...movie, title: e.target.value })}
            required
          />
          <input
            type="text"
            placeholder="Genre"
            className="w-full p-2 border rounded"
            onChange={(e) => setMovie({ ...movie, genre: e.target.value })}
            required
          />
          <input
            type="number"
            placeholder="Rating"
            className="w-full p-2 border rounded"
            onChange={(e) => setMovie({ ...movie, rating: parseFloat(e.target.value) })}
            required
          />
          <div className="flex justify-end space-x-2">
            <button type="button" onClick={onClose} className="px-4 py-2 bg-gray-300 rounded">
              Cancel
            </button>
            <button type="submit" className="px-4 py-2 bg-blue-600 text-white rounded">
              Add
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddMovieModal;
