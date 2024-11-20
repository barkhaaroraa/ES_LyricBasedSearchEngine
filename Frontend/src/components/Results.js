import React from 'react';
import '../styles/Results.css';

function Results({ results }) {
  return (
    <section style={styles.section}>
      <h1>Results</h1>
      {results.length > 0 ? (
        <ul style={styles.list}>
          {results.map((result) => (
            <li key={result.id} style={styles.item}>
              <strong>{result.title}</strong> by {result.artist}
            </li>
          ))}
        </ul>
      ) : (
        <p>No results found. Try again!</p>
      )}
    </section>
  );
}

const styles = {
  section: {
    padding: '50px',
    textAlign: 'center',
    backgroundColor: '#f9f9f9',
  },
  list: {
    listStyleType: 'none',
    padding: 0,
  },
  item: {
    margin: '10px 0',
    fontSize: '18px',
  },
};

export default Results;
