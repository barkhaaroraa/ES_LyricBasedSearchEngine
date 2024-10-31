package CSVtoJSON; // Ensure this matches your directory structure

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVtoJSON {

    public static void main(String[] args) {
        String csvFilePath = "C://Users//barkha arora//Desktop//elastic search//data//elastic search.csv"; // Replace with your CSV file path
        String jsonFilePath = "C://Users//barkha arora//Desktop//elastic search//data//LyricData.json"; // Output JSON file path
        convertCSVToJSON(csvFilePath, jsonFilePath);
    }

    public static void convertCSVToJSON(String csvFilePath, String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> jsonList = new ArrayList<>();

        try (CSVParser csvParser = new CSVParser(new FileReader(csvFilePath),
                CSVFormat.Builder.create()
                        .setHeader() // Set header from the first record
                        .setSkipHeaderRecord(true) // Skip the first record as it is header
                        .build())) {

            int index = 0; // Unique index for each row
            for (CSVRecord csvRecord : csvParser) {
                Map<String, Object> recordMap = new HashMap<>();

                // Get the header names
                Iterable<String> headers = csvParser.getHeaderNames();

                // Loop through headers to populate the recordMap
                for (String header : headers) {
                    recordMap.put(header, csvRecord.get(header));
                }
                // Add the unique index to the recordMap
                recordMap.put("id", index++); // Unique index for Elasticsearch

                jsonList.add(recordMap); // Add the recordMap to the list
            }

            // Write the list of JSON objects to the output file
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), jsonList);
            System.out.println("CSV file has been converted to JSON successfully!");

        } catch (IOException e) {
            System.err.println("Error reading or writing files: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}
