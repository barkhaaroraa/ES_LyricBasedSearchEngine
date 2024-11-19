package Ranking;

import java.util.*;

import ElasticSearchOperations.Songs;
import Jaccard.JaccardSimilarity;
import TFIDF.TFIDFReranker;

public class Ranking {

    public static void main(String[] args) {
        // Mock input: List of all songs and input lyrics
        List<Songs> allSongs = Arrays.asList(
                new Songs("Album1", "Pop", "Indie Pop", "Artist1", "English", 1, "These are the song lyrics one", "Song1"),
                new Songs("Album2", "Rock", "Alt Rock", "Artist2", "English", 2, "Lyrics of song two with joy and fun", "Song2"),
                new Songs("Album3", "Pop", "Synth Pop", "Artist3", "English", 3, "Life is a beautiful journey", "Song3"),
                new Songs("Album4", "Classical", "Instrumental", "Artist4", "English", 4, "This is the fourth song lyrics", "Song4"),
                new Songs("Album5", "Jazz", "Smooth Jazz", "Artist5", "English", 5, "Music makes life beautiful", "Song5")
        );
        String inputLyrics = "Life is beautiful with music and joy";

        // Calculate results and rank the top 20 unique songs
        Ranking ranking = new Ranking();
        List<SongSimilarity> topSongs = ranking.calculatingResults(allSongs, inputLyrics);

        // Display the top-ranked unique songs
        System.out.println("Top Ranked Songs:");
        for (int i = 0; i < topSongs.size(); i++) {
            SongSimilarity song = topSongs.get(i);
            System.out.printf("%d. Song: %s | Artist: %s | Score: %.2f%n", 
                    (i + 1), song.getTrackName(), song.getArtistName(), song.getScore());
        }
    }

    public List<SongSimilarity> calculatingResults(List<Songs> allSongs, String lyrics) {
        // Priority queue for top scores (max-heap)
        PriorityQueue<SongSimilarity> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getScore(), a.getScore()) // Max-heap: highest score first
        );

        // HashSet to track unique song titles
        HashSet<String> uniqueSongs = new HashSet<>();

        // Iterate through all songs, calculate scores, and add to priority queue
        for (Songs song : allSongs) {
            JaccardSimilarity jaccardScore = new JaccardSimilarity();
            TFIDFReranker tfidfScore = new TFIDFReranker();

            // Calculate individual scores
            double j_score = jaccardScore.calculateJaccardForSongs(song, lyrics);
            double tfidf_score = tfidfScore.calculateTFIDFAndCosineSimilarity(song, lyrics);

            // Combine scores (custom weighted scoring logic)
            double score = j_score * 3 + tfidf_score * 2;

            // Create a SongSimilarity object
            SongSimilarity songSimilarity = new SongSimilarity(song.getTrack_name(), song.getTrack_artist(), score);

            // Add to the priority queue
            pq.offer(songSimilarity);
        }

        // Retrieve the top 20 unique results
        List<SongSimilarity> topResults = new ArrayList<>();
        while (!pq.isEmpty() && topResults.size() < 20) {
            SongSimilarity song = pq.poll();

            // Add to results if the song is not already present
            if (uniqueSongs.add(song.getTrackName())) { // Add returns false if the song is already in the set
                topResults.add(song);
            }
        }

        return topResults;
    }
}
