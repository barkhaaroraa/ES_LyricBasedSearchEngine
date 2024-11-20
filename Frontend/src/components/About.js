import React from 'react';
import '../styles/About.css';

const About = () => {
  return (
    <section id="about" className="about-section">
      <div className="about-content">
        <h1>Discover Your Sound</h1>
        <p>
          LyricFinder is a cutting-edge tool that helps you identify songs from audio or lyrics in seconds. 
          Let music speak to you—play it, hum it, or type it, and we'll find your perfect match.
        </p>
        <button className="cta-button">Learn More</button>
      </div>
      <div className="sound-wave"></div>
    </section>
  );
};

export default About;
