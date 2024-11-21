package Main;

import java.util.*;
import ElasticSearchOperations.FuzzyLogic;
import ElasticSearchOperations.Search;
import ElasticSearchOperations.Songs;
import Ranking.Ranking;
import Ranking.SongSimilarity;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.apache.http.Header;

public class Main {

    public static void main(String lyrics) {
        String serverUrl = "http://localhost:9200";  // Your Elasticsearch server URL
        String apiKey = "aVlNRVRwTUIwSlEwVFg0OXUzc3c6NzJTRmpjRlVRZ1NWbF9PSk5KT2k2Zw==";
        try {
            // 1. Create the low-level RestClient with API Key in the header
            RestClient lowLevelRestClient = RestClient.builder(
                    new HttpHost("localhost", 9200, "http")
            )
            .setDefaultHeaders(new Header[]{
                    new BasicHeader("Authorization", "ApiKey " + apiKey)
            })
            .build();

            // 2. Create the RestClientTransport with the low-level RestClient
            RestClientTransport transport = new RestClientTransport(
                    lowLevelRestClient, new JacksonJsonpMapper());

            // 3. Create the ElasticsearchClient with the transport
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            // 4. Create an instance of Search, passing in the ElasticsearchClient
            Search search = new Search(esClient);
            FuzzyLogic fuzzysearch= new FuzzyLogic(esClient);


            // 5. Call the searchSongByLyrics method to search based on user input
            List<Songs> exactMatch=search.searchSongByLyrics(lyrics);
            List<Songs> fuzzyMatch=fuzzysearch.searchWithFuzziness(lyrics);
            List<Songs> combined = new ArrayList<>();

        // Add all exact match songs to the combined list
            combined.addAll(exactMatch);

            // Add only unique fuzzy match songs to the combined list (avoiding duplicates)
            for (Songs song : fuzzyMatch) {
                if (!combined.contains(song)) {
                    combined.add(song);
                }
            }
            System.out.println("ranking initiated");
            Ranking newRanks=new Ranking();
            List<SongSimilarity>topSongs=newRanks.calculatingResults(combined, lyrics);    
            for (int i = 0; i < topSongs.size(); i++) {
                SongSimilarity song = topSongs.get(i);
                System.out.printf("%d. Song: %s | Score: %.2f%n", (i + 1), song.getTrackName(), song.getScore());
            } 

            // Close the transport when done
            transport.close();
        } catch (Exception e) {
            System.err.println("Error connecting to Elasticsearch: " + e.getMessage());
        }
    }
}
