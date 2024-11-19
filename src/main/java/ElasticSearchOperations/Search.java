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

public class Search {

    private final ElasticsearchClient esClient;

    public Search(ElasticsearchClient client) {
        this.esClient = client;
    }

    /**
     * Search for songs by lyrics and return the top 10 unique results.
     *
     * @param lyrics The lyrics to search for.
     * @return List of top 10 unique Songs (name, lyrics, and artist).
     */
    public List<Songs> searchSongByLyrics(String lyrics) {
        List<Songs> top10Songs = new ArrayList<>();  // List to store top 10 unique songs
        Set<String> uniqueValues = new HashSet<>();  // Set to track unique song properties

        try {
            // Validate input
            if (lyrics == null || lyrics.isEmpty()) {
                System.err.println("Lyrics input is empty. Please provide valid lyrics to search.");
                return top10Songs; // Return empty list if input is invalid
            }

            // Build the search request
            SearchRequest request = SearchRequest.of(s -> s
                .index("songs")  // The index name
                .query(q -> q
                    .match(m -> m
                        .field("lyrics")
                        .query(lyrics)
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
                System.out.println("No songs found for the given lyrics.");
            } else {
                for (Hit<Songs> hit : hits) {
                    Songs song = hit.source();
                    if (song != null) {
                        // Combine fields into a unique identifier
                        String uniqueKey = song.getTrack_name() + "|" + song.getLyrics() + "|" + song.getTrack_artist();

                        // Ensure uniqueness before adding to the list
                        if (uniqueValues.add(uniqueKey)) {  // `add` returns false if uniqueKey already exists in the set
                            top10Songs.add(song);  // Add the song to the list
                            // System.out.printf("Song Name: %s, Lyrics: %s, Artist: %s%n", 
                            count++;
                        }

                        if (count >= 10) break;  // Stop once we have 10 unique results
                    } else {
                        System.err.println("Hit without source data. Skipping...");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error executing search: " + e.getMessage());
        }

        // Return the list of top 10 unique songs
        return top10Songs;
    }
}
