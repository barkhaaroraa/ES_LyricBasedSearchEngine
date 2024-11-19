package SpeechToText;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeRequest;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import Main.Main;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class SpeechToText {

    // Method to perform speech-to-text and return the transcription as a continuous
    // space-separated string
    public String speechtotext(String path) {
        StringBuilder transcription = new StringBuilder(); // Store the transcription result

        try {
            // Get the credentials path from the environment variable
            String credentialsPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");

            if (credentialsPath == null || credentialsPath.isEmpty()) {
                throw new IllegalStateException("GOOGLE_APPLICATION_CREDENTIALS environment variable is not set.");
            }

            // Set the path to your audio file
            String audioFilePath = path;

            // Read the audio file into a byte array
            byte[] audioBytes = Files.readAllBytes(Paths.get(audioFilePath));

            // Initialize the client (credentials will automatically be picked up from the environment variable)
            try (SpeechClient speechClient = SpeechClient.create()) {
                // Configure recognition settings
                RecognitionConfig config = RecognitionConfig.newBuilder()
                        .setEncoding(AudioEncoding.LINEAR16) // Adjust encoding based on your audio file
                        .setSampleRateHertz(16000) // Match your audio file's sample rate
                        .setLanguageCode("en-US") // Set language code
                        .build();

                // Create RecognitionAudio from byte array
                RecognitionAudio audio = RecognitionAudio.newBuilder()
                        .setContent(com.google.protobuf.ByteString.copyFrom(audioBytes))
                        .build();

                // Build the request
                RecognizeRequest request = RecognizeRequest.newBuilder()
                        .setConfig(config)
                        .setAudio(audio)
                        .build();

                // Perform recognition
                RecognizeResponse response = speechClient.recognize(request);

                // Iterate over the results and append them with a space (no newline)
                response.getResultsList().forEach(result -> {
                    String transcript = result.getAlternatives(0).getTranscript();
                    if (transcription.length() > 0) {
                        transcription.append(" "); // Add space between words
                    }
                    transcription.append(transcript);
                });
            }
        } catch (IOException e) {
            System.out.println("Error reading the audio file: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the transcription as a space-separated string
        return transcription.toString().trim(); // Trim to remove any leading or trailing spaces
    }

    public static void main(String[] args) {
        // System.err.println("enter");
        SpeechToText stt = new SpeechToText();

        // Replace this with the path to your audio file
        String audioFilePath = "/home/barkha/gh-dev/elasticsearch_minor/recorded_audio.wav";

        String result = stt.speechtotext(audioFilePath);
        System.out.println(result);
        Main searching=new Main();
        searching.main(result);
    }
}
