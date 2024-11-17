package Main;

import ElasticSearchOperations.ElasticSearchOperations;
import ElasticSearchOperations.FuzzyLogic;
import ElasticSearchOperations.Search;
import ElasticSearchOperations.Songs;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.util.Scanner;

import org.apache.http.Header;

public class Main {

    public static void main(String[] args) {
        String serverUrl = "http://localhost:9200";  // Your Elasticsearch server URL
        String apiKey = "api-key";
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
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter song lyrics to search: ");
            String lyrics = scanner.nextLine();


            // 5. Call the searchSongByLyrics method to search based on user input
            search.searchSongByLyrics(lyrics);
            fuzzysearch.searchWithFuzziness(lyrics);

            // Close the transport when done
            transport.close();
        } catch (Exception e) {
            System.err.println("Error connecting to Elasticsearch: " + e.getMessage());
        }
    }
}
