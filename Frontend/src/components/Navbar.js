import React from 'react';
import '../styles/Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-logo">LyricFinder</div>
      <ul className="navbar-links">
        <li><a href="#about">About</a></li>
        <li><a href="#search">Search</a></li>
        <li><a href="#results">Results</a></li>
      </ul>
    </nav>
  );
};

export default Navbar;
