package TFIDF;
import java.util.*;

import ElasticSearchOperations.Songs;

public class TFIDFReranker {

    // Stopwords list to filter out common words
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
            "the", "is", "in", "on", "at", "to", "and", "a", "of", "for", "with", "about"));

    public void main(String[] args) {
        // Step 1: Define the full song and incomplete lyric (query)
        String fullLyric = "I am phenomenal, I am unstoppable, I am the greatest.";
        String queryLyric = "I am phenomenal";

        // Step 2: Compute TF-IDF for both lyrics and cosine similarity
        // double cosineSimilarity = calculateTFIDFAndCosineSimilarity(fullLyric, queryLyric);

        // Step 3: Output cosine similarity
        // System.out.println("Cosine Similarity: " + cosineSimilarity);
    }

    public double calculateTFIDFAndCosineSimilarity(Songs fullLyric, String queryLyric) {
        // Combine both lyrics into one corpus (for TF-IDF calculation)
        List<String> corpus = Arrays.asList(fullLyric.getLyrics(), queryLyric);

        // Step 1: Calculate IDF for terms in both lyrics
        Map<String, Double> idfScores = calculateIDF(corpus);

        // Step 2: Calculate TF-IDF for both lyrics
        Map<String, Double> tfidfFullLyric = calculateTFIDF(fullLyric.getLyrics(), idfScores);
        Map<String, Double> tfidfQueryLyric = calculateTFIDF(queryLyric, idfScores);

        // Step 3: Calculate cosine similarity between the two TF-IDF vectors
        return calculateCosineSimilarity(tfidfFullLyric, tfidfQueryLyric);
    }

    private Map<String, Double> calculateIDF(List<String> corpus) {
        // System.out.println("tfidf");
        int totalDocuments = corpus.size();
        Map<String, Integer> documentFrequency = new HashMap<>();

        // Calculate document frequency for each term
        for (String doc : corpus) {
            Set<String> termsInDoc = new HashSet<>(Arrays.asList(doc.toLowerCase().split("\\s+")));
            for (String term : termsInDoc) {
                if (!STOPWORDS.contains(term)) {
                    documentFrequency.put(term, documentFrequency.getOrDefault(term, 0) + 1);
                }
            }
        }

        // Calculate IDF for each term with smoothing
        Map<String, Double> idfScores = new HashMap<>();
        for (Map.Entry<String, Integer> entry : documentFrequency.entrySet()) {
            String term = entry.getKey();
            int docCount = entry.getValue();
            double idf = Math.log((double) (totalDocuments + 1) / (docCount + 1)) + 1; // Smoothing
            idfScores.put(term, idf);
        }

        return idfScores;
    }

    private Map<String, Double> calculateTFIDF(String lyric, Map<String, Double> idfScores) {
        Map<String, Double> tfidfScores = new HashMap<>();
        String[] terms = lyric.toLowerCase().split("\\s+");
        Map<String, Integer> termFrequency = new HashMap<>();

        // Count term frequency in the lyric
        for (String term : terms) {
            if (!STOPWORDS.contains(term)) {
                termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
            }
        }

        // Calculate TF-IDF for each term
        for (Map.Entry<String, Integer> entry : termFrequency.entrySet()) {
            String term = entry.getKey();
            int termFreq = entry.getValue();
            double tf = (double) termFreq / terms.length;
            double idf = idfScores.getOrDefault(term, 0.0);
            double tfidf = tf * idf;
            tfidfScores.put(term, tfidf);
        }

        return tfidfScores;
    }

    private double calculateCosineSimilarity(Map<String, Double> tfidf1, Map<String, Double> tfidf2) {
        // Get all terms from both TF-IDF maps
        Set<String> allTerms = new HashSet<>();
        allTerms.addAll(tfidf1.keySet());
        allTerms.addAll(tfidf2.keySet());

        // Calculate dot product and magnitudes
        double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;

        for (String term : allTerms) {
            double tfidfValue1 = tfidf1.getOrDefault(term, 0.0);
            double tfidfValue2 = tfidf2.getOrDefault(term, 0.0);

            dotProduct += tfidfValue1 * tfidfValue2;
            magnitude1 += tfidfValue1 * tfidfValue1;
            magnitude2 += tfidfValue2 * tfidfValue2;
        }

        // Calculate cosine similarity (dot product / (magnitude1 * magnitude2))
        double cosineSimilarity = dotProduct / (Math.sqrt(magnitude1) * Math.sqrt(magnitude2));
        return cosineSimilarity;
    }
}
