package DatatoElastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.bulk.BulkRequest;
import co.elastic.clients.elasticsearch.core.bulk.IndexOperation;
import co.elastic.clients.elasticsearch.core.bulk.BulkResponse;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import co.elastic.jackson.JacksonJsonpMapper;
import org.apache.http.HttpHost;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.elasticsearch.client.RestClient;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DatatoElastic{

    private static ElasticsearchClient createClient() {
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        return new ElasticsearchClient(new RestClientTransport(builder.build(), new JacksonJsonpMapper()));
    }

    public static void main(String[] args) {
        // Create Elasticsearch client
        ElasticsearchClient client = createClient();

        // Read JSON data from a file
        String jsonFilePath = "path/to/your/data.json";
        List<Map<String, Object>> jsonData;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Read the JSON array from the file
            jsonData = List.of(objectMapper.readValue(Files.readAllBytes(Paths.get(jsonFilePath)), Map[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Prepare bulk request
        BulkRequest.Builder bulkRequest = new BulkRequest.Builder();

        for (Map<String, Object> json : jsonData) {
            // Create index operation for each JSON entry
            IndexOperation<Map<String, Object>> indexOp = IndexOperation.of(i -> i
                    .index("tracks") // Specify your index name
                    .document(json)  // The JSON document
            );
            bulkRequest.add(indexOp);
        }

        // Execute bulk request
        try {
            BulkResponse bulkResponse = client.bulk(bulkRequest.build());
            System.out.println("Successfully indexed documents: " + bulkResponse.items().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
