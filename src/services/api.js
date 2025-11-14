import axios from "axios";
import { BASE_URL } from "../config";

export const getAllMovies = async () => {
  const res = await axios.get(`${BASE_URL}/getAll`);
  return res.data;
};

export const addMovie = async (movie) => {
  const res = await axios.post(`${BASE_URL}/addnew`, movie);
  return res.data;
};

export const getAIRecommendations = async () => {
  const res = await axios.get(`${BASE_URL}/recommend/ai`);
  return res.data;
};

export const getDeepLearningRecommendations = async () => {
  const res = await axios.get(`${BASE_URL}/recommend/deeplearning`);
  return res.data;
};
