package Ranking;

import java.util.*;
import java.util.stream.Collectors;

public class TFIDFReranker {

    // Stopwords list to filter out common words
    private static final Set<String> STOPWORDS = new HashSet<>(Arrays.asList(
            "the", "is", "in", "on", "at", "to", "and", "a", "of", "for", "with", "about"));

    public static class Document {
        String id; // Unique identifier for the document
        String content; // Lyrics or text content
        double tfidfScore; // Computed TF-IDF score

        public Document(String id, String content) {
            this.id = id;
            this.content = content;
            this.tfidfScore = 0.0;
        }
    }

    public static void main(String[] args) {
        // Step 1: Define query terms (simulate user query)
        String query = "i am phenomenel"; // Example query
        String[] queryTerms = query.toLowerCase().split("\\s+"); // Split the query into terms and normalize

        // Step 2: Manually input song lyrics (simulating Elasticsearch results)
        List<Document> documents = Arrays.asList(
                new Document("1", "Don't run away, it's getting colder..."),
                new Document("2", "Everybody here wants you..."),
                new Document("3", "Santa Claus is comin' to town..."),
                new Document("4", "I am phenomenal..."));

        // Step 3: Compute TF-IDF scores and rerank documents
        List<Document> rerankedDocs = rerankDocuments(documents, queryTerms);

        // Step 4: Output reranked results
        System.out.println("Reranked Documents:");
        for (Document doc : rerankedDocs) {
            System.out.println("ID: " + doc.id + ", Score: " + doc.tfidfScore + ", Content: " + doc.content);
        }
    }

    private static List<Document> rerankDocuments(List<Document> documents, String[] queryTerms) {
        // Step 1: Calculate IDF for query terms
        int totalDocuments = documents.size();
        Map<String, Double> idfScores = new HashMap<>();

        // Calculate IDF for each query term with smoothing
        for (String term : queryTerms) {
            if (STOPWORDS.contains(term)) {
                continue; // Skip stopwords
            }

            int docFrequency = 0;
            for (Document doc : documents) {
                // Normalize the document content to lowercase and split by whitespace
                String[] docTerms = doc.content.toLowerCase().split("\\s+");
                for (String docTerm : docTerms) {
                    if (docTerm.equals(term)) {
                        docFrequency++;
                        break; // If the term is found, no need to check further in this document
                    }
                }
            }

            // IDF formula with smoothing: log((totalDocuments + 1) / (docFrequency + 1)) +
            // 1
            double idf = Math.log((double) (totalDocuments + 1) / (1 + docFrequency)) + 1;
            idfScores.put(term, idf);
            System.out.println("IDF for term '" + term + "': " + idf); // Debugging
        }

        // Step 2: Compute TF-IDF for each document
        for (Document doc : documents) {
            String[] terms = doc.content.toLowerCase().split("\\s+");
            Map<String, Integer> termFrequency = new HashMap<>();

            // Count frequency of each term in the document
            for (String term : terms) {
                termFrequency.put(term, termFrequency.getOrDefault(term, 0) + 1);
            }

            double tfidfScore = 0.0;
            // Compute TF-IDF for each query term
            for (String term : queryTerms) {
                if (STOPWORDS.contains(term)) {
                    continue; // Skip stopwords
                }

                double tf = (double) termFrequency.getOrDefault(term, 0) / terms.length; // Term Frequency
                double idf = idfScores.getOrDefault(term, 0.0); // Inverse Document Frequency
                tfidfScore += tf * idf; // Add TF-IDF of the current term

                // Debugging: print term frequency and score calculation
                System.out.println("Document ID: " + doc.id);
                System.out.println("Term: " + term);
                System.out.println("TF: " + tf + ", IDF: " + idf + ", Term TF-IDF: " + (tf * idf));
            }
            doc.tfidfScore = tfidfScore;
        }

        // Step 3: Sort documents by TF-IDF scores using PriorityQueue
        PriorityQueue<Document> pq = new PriorityQueue<>(new Comparator<Document>() {
            @Override
            public int compare(Document d1, Document d2) {
                // Compare by TF-IDF score in descending order
                return Double.compare(d2.tfidfScore, d1.tfidfScore);
            }
        });

        // Add all documents to the priority queue
        pq.addAll(documents);

        // Step 4: Extract documents from the priority queue and collect the result
        List<Document> sortedDocuments = new ArrayList<>();
        while (!pq.isEmpty()) {
            sortedDocuments.add(pq.poll()); // Poll from priority queue to get documents in sorted order
        }

        return sortedDocuments;
    }
}
