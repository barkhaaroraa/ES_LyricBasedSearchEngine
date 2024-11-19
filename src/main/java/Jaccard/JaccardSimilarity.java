package Jaccard;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Arrays;
import ElasticSearchOperations.Songs;
import Ranking.SongSimilarity;

public class JaccardSimilarity {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList("the", "is", "in", "on", "at", "to", "and", "a", "of", "for", "with", "about"    ));

    public double jaccardSimilarity(String str1, String str2) {
        // System.err.println("jaccard");
        // Preprocess both strings: Convert to lowercase, remove non-alphanumeric characters, and remove stop words
        str1 = preprocessString(str1);
        str2 = preprocessString(str2);

        // Get the n-grams for both strings
        Set<String> nGrams1 = getNGrams(str1, 2);
        Set<String> nGrams2 = getNGrams(str2, 2);

        // Calculate the intersection and union of both sets
        Set<String> intersection = new HashSet<>(nGrams1);
        intersection.retainAll(nGrams2);  // Get common n-grams

        Set<String> union = new HashSet<>(nGrams1);
        union.addAll(nGrams2);  // Combine both sets

        // Jaccard similarity is the ratio of the intersection to the union
        return (double) intersection.size() / union.size();
    }

    private String preprocessString(String str) {
        // Convert to lowercase and remove all non-alphanumeric characters (remove punctuation)
        str = str.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        
        // Split the string into words and filter out stop words
        String[] words = str.split("\\s+");
        StringBuilder processedString = new StringBuilder();
        for (String word : words) {
            if (!STOP_WORDS.contains(word)) {
                processedString.append(word).append(" ");
            }
        }
        return processedString.toString().trim();
    }

    private Set<String> getNGrams(String str, int n) {
        Set<String> nGrams = new HashSet<>();
        for (int i = 0; i <= str.length() - n; i++) {
            nGrams.add(str.substring(i, i + n));
        }
        return nGrams;
    }



    public double calculateJaccardForSongs(Songs song, String songLyric) {
        // Map to store SongSimilarity objects for each song
        
        double similarity = jaccardSimilarity(songLyric, song.getLyrics());
            
        return similarity;
    }

    public static void main(String[] args) {
        // Example usage with your actual list of songs and input lyric
        String fullLyric = "I am phenomenal, I am unstoppable, I am the greatest.";
        String queryLyric = "I am phenomenal";
    }

}
