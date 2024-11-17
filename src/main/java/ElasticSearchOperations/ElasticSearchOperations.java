package ElasticSearchOperations;

// import co.elastic.clients.elasticsearch.ElasticsearchClient;
// import co.elastic.clients.transport.rest_client.RestClientTransport;
// import co.elastic.clients.json.jackson.JacksonJsonpMapper;
// import org.elasticsearch.client.RestClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.impl.client.BasicCredentialsProvider;
// import org.apache.http.auth.UsernamePasswordCredentials;
// import org.apache.http.auth.AuthScope;
// import co.elastic.clients.elasticsearch.core.IndexRequest;
// import co.elastic.clients.elasticsearch.core.IndexResponse;

// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.HttpHost;
// import org.apache.http.client.methods.HttpGet;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import org.elasticsearch.client.RestClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import org.apache.http.HttpHost;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

import ElasticSearchOperations.Songs;
import java.util.Iterator;
public class ElasticSearchOperations {

    public static void main(String[] args) {
        String serverUrl = "http://localhost:9200";  // Your Elasticsearch server URL
        String apiKey = "";  // Replace with your generated API key

      
        String filePath = "/home/barkha/gh-dev/elasticsearch_minor/data/LyricData.json";

        try {
            RestClient lowLevelRestClient = RestClient.builder(new HttpHost("localhost", 9200, "http"))
                    .setDefaultHeaders(new org.apache.http.Header[]{
                            new org.apache.http.message.BasicHeader("Authorization", "ApiKey " + apiKey)
                    })
                    .build();

            RestClientTransport transport = new RestClientTransport(lowLevelRestClient, new JacksonJsonpMapper());
            ElasticsearchClient esClient = new ElasticsearchClient(transport);

            indexDocumentsFromJsonFile(esClient, filePath);

            transport.close();
        } catch (Exception e) {
            System.err.println("Error connecting to Elasticsearch: " + e.getMessage());
        }
    }

    // Method to index documents from a JSON file
    public static void indexDocumentsFromJsonFile(ElasticsearchClient client, String filePath) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode rootNode = mapper.readTree(new File(filePath));
            Iterator<JsonNode> elements = rootNode.elements();

            while (elements.hasNext()) {
                JsonNode jsonNode = elements.next();

                // Map JSON object to a Song instance
                Songs song = mapper.treeToValue(jsonNode, Songs.class);

                // Create an index request for each song
                IndexRequest<Songs> request = IndexRequest.of(i -> i
                    .index("songs")
                    .id(String.valueOf(song.getId()))
                    .document(song)
                );

                IndexResponse response = client.index(request);

                System.out.println("Indexed document with ID: " + response.id());
            }

        } catch (IOException e) {
            System.err.println("Error reading JSON file or indexing document: " + e.getMessage());
        }
    }
}
