package Main;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import ElasticSearchOperations.ElasticSearchOperations;
import ElasticSearchOperations.Search;
import ElasticSearchOperations.FuzzyLogic;
import ElasticSearchOperations.Songs;
import Ranking.Ranking;
import Ranking.SongSimilarity;
import java.util.ArrayList;
import java.util.List;

public class TestingMain {

    public static ElasticsearchClient initializeClient(String serverUrl, String apiKey) {
        try {
            RestClient lowLevelRestClient = RestClient.builder(
                    new HttpHost("localhost", 9200, "http")
            )
            .setDefaultHeaders(new Header[]{
                    new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .build();

            RestClientTransport transport = new RestClientTransport(
                    lowLevelRestClient, new JacksonJsonpMapper()
            );

            return new ElasticsearchClient(transport);

        } 
        catch (Exception e) {
            System.err.println("Error initializing Elasticsearch client: " + e.getMessage());
            return null;
        }
    }

    // Function to search for lyrics and check if the expected song is found in top results
    public static int searchLyrics(ElasticsearchClient esClient, String lyrics, String expectedSong) {
        try {
            Search search = new Search(esClient);
            FuzzyLogic fuzzySearch = new FuzzyLogic(esClient);

            List<Songs> exactMatch = search.searchSongByLyrics(lyrics);
            List<Songs> fuzzyMatch = fuzzySearch.searchWithFuzziness(lyrics);
            List<Songs> combined = new ArrayList<>();

            combined.addAll(exactMatch);
            for (Songs song : fuzzyMatch) {
                if (!combined.contains(song)) {
                    combined.add(song);
                }
            }
            Ranking ranking = new Ranking();
            List<SongSimilarity> topSongs = ranking.calculatingResults(combined, lyrics);

            int found = 0;
            System.out.println("Ranking results:");
            for (int i = 0; i < topSongs.size(); i++) {
                SongSimilarity song = topSongs.get(i);
                if (expectedSong.equalsIgnoreCase(song.getTrackName())) {
                    found = 1;
                }
                System.out.printf("%d. Song: %s | Score: %.2f%n", (i + 1), song.getTrackName(), song.getScore());
            }

            return found;

        } 
        catch (Exception e) {
            System.err.println("Error during lyric search: " + e.getMessage());
            return 0;
        }
    }
}
