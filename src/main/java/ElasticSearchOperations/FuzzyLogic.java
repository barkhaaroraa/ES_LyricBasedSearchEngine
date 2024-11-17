package ElasticSearchOperations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch._types.SortOrder;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FuzzyLogic {

    private final ElasticsearchClient esClient;

    public FuzzyLogic(ElasticsearchClient client) {
        this.esClient = client;
    }

    // Method to perform a fuzzy search on phrases with typos and return only unique top 10 results
    public void searchWithFuzziness(String input) {
        try {
            // Validate input
            if (input == null || input.isEmpty()) {
                System.err.println("Input for fuzzy search is empty. Please provide valid input.");
                return;
            }

            // Build the search request
            SearchRequest request = SearchRequest.of(s -> s
                .index("songs") // Ensure this matches your index name
                .query(q -> q
                    .match(m -> m
                        .field("lyrics")     // Field to search
                        .query(input)        // User input (phrase)
                        .fuzziness("AUTO")   // Set fuzziness for typos
                    )
                )
                .size(100) // Retrieve up to 100 results (more than we need)
                .sort(sort -> sort
                    .field(f -> f
                        .field("_score")    // Sort by relevance score
                        .order(SortOrder.Desc) // Sort descending by score
                    )
                )
            );

            // Execute the search request
            SearchResponse<Songs> response = esClient.search(request, Songs.class);

            // Create a Set to ensure uniqueness based on lyrics
            Set<String> uniqueLyrics = new HashSet<>();
            List<Hit<Songs>> hits = response.hits().hits();

            if (hits.isEmpty()) {
                System.out.println("No songs found for the given phrase with typos: " + input);
            } else {
                System.out.println("Top 10 fuzzy phrase search results (Unique):");
                int count = 0;
                for (Hit<Songs> hit : hits) {
                    Songs song = hit.source();
                    if (song != null) {
                        // Check if the lyrics are already in the set (i.e., ensure uniqueness)
                        if (uniqueLyrics.add(song.getLyrics())) {  // add returns false if the item already exists
                            System.out.printf("Song Name: %s, Artist: %s%n",
                                    song.getTrack_name(),
                                    song.getTrack_artist());
                            count++;
                        }
                        if (count > 10) break; // Stop once we have 10 unique results
                    } else {
                        System.err.println("Hit without source data. Skipping...");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error executing fuzzy phrase search: " + e.getMessage());
        }
    }
}
