import React from 'react';
import Navbar from './components/Navbar';
import About from './components/About';
import Search from './components/Search';
import './styles/global.css';

function App() {
  const handleSearch = (query) => {
    console.log('Search Query:', query);
    // Call backend API here
  };

  return (
    <div>
      <Navbar />
      <About />
      <Search onSearch={handleSearch} />
    </div>
  );
}

export default App;
