import React from "react";

const SearchHero = ({ onAIRecommend, onDLRecommend }) => {
  return (
    <div style={{ textAlign: "center", margin: "20px" }}>
      <button onClick={onAIRecommend}>AI Recommendations (Weka)</button>
      <button onClick={onDLRecommend} style={{ marginLeft: "10px" }}>
        Deep Learning Recommendations
      </button>
    </div>
  );
};

export default SearchHero;
