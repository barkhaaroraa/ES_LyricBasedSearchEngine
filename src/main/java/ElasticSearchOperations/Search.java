package ElasticSearchOperations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import ElasticSearchOperations.Songs;

public class Search {

    private final ElasticsearchClient esClient;

    public Search (ElasticsearchClient client) {
        this.esClient = client;
    }

    public void searchSongByLyrics(String lyrics) {
        // Take user input for the lyrics to search
        
        try {
            // Build the search request
            SearchRequest request = SearchRequest.of(s -> s
                .index("songs")  // The index name
                .query(q -> q
                    .match(m -> m
                        .field("lyrics")
                        .query(lyrics)
                    )
                )
                .size(100)  // Limit to the top 10 results
            );

            // Execute the search request
            SearchResponse<Songs> response = esClient.search(request, Songs.class);

            // Display results
            List<Hit<Songs>> hits = response.hits().hits();
            Set<String> uniqueLyrics = new HashSet<>();
            int count=0;

            
            if (hits.isEmpty()) {
                System.out.println("No songs found for the given lyrics.");
            } 
            else {
                System.out.println("Top 10 search results:");
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
                        if (count >= 10) break; // Stop once we have 10 unique results
                    } 
                    else {
                        System.err.println("Hit without source data. Skipping...");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error executing search: " + e.getMessage());
        }
    }

}

