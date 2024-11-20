import React, { useState, useRef } from 'react';
import '../styles/Search.css'; // Updated path

function Search() {
  const [query, setQuery] = useState('');
  const [isListening, setIsListening] = useState(false);
  const [mediaRecorder, setMediaRecorder] = useState(null);
  const audioChunks = useRef([]); // To store audio chunks

  const toggleListening = async () => {
    if (isListening) {
      stopListening(); // If already listening, stop recording
    } else {
      await startListening(); // If not listening, start recording
    }
  };

  const startListening = async () => {
    try {
      // Get audio stream from the microphone
      const stream = await navigator.mediaDevices.getUserMedia({ audio: true });

      // Set up MediaRecorder
      const recorder = new MediaRecorder(stream);
      setMediaRecorder(recorder);
      audioChunks.current = []; // Reset audio chunks

      recorder.ondataavailable = (event) => {
        // Push audio data into chunks
        audioChunks.current.push(event.data);
      };

      recorder.onstop = () => {
        // Create a Blob from audio chunks when recording stops
        const audioBlob = new Blob(audioChunks.current, { type: 'audio/wav' });
        console.log('Recording complete. Sending to backend...');
        sendAudioToBackend(audioBlob); // Send to backend
      };

      recorder.start(); // Start recording
      setIsListening(true);
      console.log('Recording started...');
    } catch (error) {
      console.error('Error accessing microphone:', error);
    }
  };

  const stopListening = () => {
    if (mediaRecorder) {
      mediaRecorder.stop(); // Stop recording
      setIsListening(false);
      console.log('Recording stopped...');
    }
  };

  const sendAudioToBackend = async (audioBlob) => {
    const formData = new FormData();
    formData.append('audio', audioBlob, 'recording.wav');

    try {
      const response = await fetch('http://localhost:8080/api/audio', {
        method: 'POST',
        body: formData,
      });

      const result = await response.text();
      console.log('Backend response:', result);
      alert(result); // Display the result (optional)
    } catch (error) {
      console.error('Error sending audio to backend:', error);
    }
  };

  const handleSearch = () => {
    console.log('Search initiated for:', query);
    // Send the query to your backend API
    fetch('http://localhost:5000/api/search', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ query }),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log('Results:', data);
        // Handle results here
      })
      .catch((error) => console.error('Error:', error));
  };

  return (
    <div className="search-section">
      <h1>Search for Songs</h1>
      <div className="search-form">
        <input
          type="text"
          placeholder="Enter lyrics or audio"
          value={query}
          onChange={(e) => setQuery(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
        <button
          className={`mic-button ${isListening ? 'listening' : ''}`}
          onClick={toggleListening}
          title="Use your microphone"
        >
          {isListening ? 'Stop 🎤' : 'Start 🎤'}
        </button>
      </div>
    </div>
  );
}

export default Search;
