package ElasticSearchOperations;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import ElasticSearchOperations.Songs;

public class Search {

    private final ElasticsearchClient esClient;

    public Search (ElasticsearchClient client) {
        this.esClient = client;
    }

    public void searchSongByLyrics() {
        // Take user input for the lyrics to search
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter song lyrics to search: ");
        String lyrics = scanner.nextLine();

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
                .size(5)  // Limit to the top 10 results
            );

            // Execute the search request
            SearchResponse<Songs> response = esClient.search(request, Songs.class);

            // Display results
            List<Hit<Songs>> hits = response.hits().hits();
            if (hits.isEmpty()) {
                System.out.println("No songs found for the given lyrics.");
            } else {
                System.out.println("Top 10 search results:");
                for (Hit<Songs> hit : hits) {
                    Songs song = hit.source();
                    System.out.printf("Song Name: %s, Artist: %s, Lyrics: %s%n",
                            song.getTrack_name(),
                            song.getTrack_artist()
                            ,song.getLyrics());
                }
            }
        } catch (IOException e) {
            System.err.println("Error executing search: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}

