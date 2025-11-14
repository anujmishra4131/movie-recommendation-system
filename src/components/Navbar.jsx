import React, { useContext } from "react";
import { Link } from "react-router-dom";
import { ThemeContext } from "../context/ThemeContext";

const Navbar = () => {
  const { theme, toggleTheme } = useContext(ThemeContext);

  return (
    <nav>
      <div className="container" style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h2>🎬 Movie Recommendation</h2>

        <div style={{ display: "flex", gap: "20px", alignItems: "center" }}>
          <Link to="/">Home</Link>
          <Link to="/add">Add Movie</Link>

          {/* 🔘 Dark/Light Switch */}
          <button onClick={toggleTheme}>
            {theme === "light" ? "🌙 Dark" : "☀️ Light"}
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
