package SpeechToText;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeRequest;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;

import java.nio.file.Files;
import java.nio.file.Paths;

public class SpeechToText {

    // Method to perform speech-to-text and return the transcription as a continuous
    // space-separated string
    public String speechtotext(String path) {
        StringBuilder transcription = new StringBuilder(); // Store the transcription result

        try {
            // Set the path to your audio file
            String audioFilePath = path;

            // Read the audio file into a byte array
            byte[] audioBytes = Files.readAllBytes(Paths.get(audioFilePath));

            // Initialize the client
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
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the transcription as a space-separated string
        return transcription.toString().trim(); // Trim to remove any leading or trailing spaces
    }
}
