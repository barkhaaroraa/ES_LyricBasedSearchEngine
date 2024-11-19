package ElasticSearchOperations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FuzzyLogic {

    private final ElasticsearchClient esClient;

    public FuzzyLogic(ElasticsearchClient client) {
        this.esClient = client;
    }

    /**
     * Perform a fuzzy search for songs by lyrics and return the top 10 unique results.
     *
     * @param input The phrase to search for with fuzzy matching.
     * @return List of top 10 unique Songs (name, lyrics, and artist).
     */
    public List<Songs> searchWithFuzziness(String input) {
        List<Songs> top10Songs = new ArrayList<>();  // List to store top 10 unique songs
        Set<String> uniqueValues = new HashSet<>();  // Set to track unique song properties

        try {
            // Validate input
            if (input == null || input.isEmpty()) {
                System.err.println("Input for fuzzy search is empty. Please provide valid input.");
                return top10Songs; // Return empty list if input is invalid
            }

            // Build the search request
            SearchRequest request = SearchRequest.of(s -> s
                .index("songs")  // The index name
                .query(q -> q
                    .match(m -> m
                        .field("lyrics")     // Field to search
                        .query(input)        // User input (phrase)
                        .fuzziness("AUTO")   // Set fuzziness for typos
                    )
                )
                .size(100)  // Retrieve up to 100 results
            );

            // Execute the search request
            SearchResponse<Songs> response = esClient.search(request, Songs.class);

            // Get the hits from the search response
            List<Hit<Songs>> hits = response.hits().hits();
            int count = 0;

            if (hits.isEmpty()) {
                System.out.println("No songs found for the given phrase with typos: " + input);
            } else {
                for (Hit<Songs> hit : hits) {
                    Songs song = hit.source();
                    if (song != null) {
                        // Combine fields into a unique identifier
                        String uniqueKey = song.getTrack_name() + "|" + song.getLyrics() + "|" + song.getTrack_artist();

                        if (uniqueValues.add(uniqueKey)) {  // `add` returns false if uniqueKey already exists in the set
                            top10Songs.add(song);  // Add the song to the list
                            // System.out.printf("Song Name: %s, Lyrics: %s, Artist: %s%n", 
                            //     song.getTrack_name(), song.getLyrics(), song.getTrack_artist());
                            count++;
                        }

                        if (count >= 100) break; 
                    } else {
                        System.err.println("Hit without source data. Skipping...");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error executing fuzzy phrase search: " + e.getMessage());
        }

        // Return the list of top 10 unique songs
        return top10Songs;
    }
}
