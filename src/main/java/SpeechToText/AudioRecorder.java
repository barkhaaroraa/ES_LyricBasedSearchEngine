package SpeechToText;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import SpeechToText.SpeechToText;

public class AudioRecorder {
    // Specify the audio format for the recording
    private static final AudioFormat AUDIO_FORMAT = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED,
            16000, // Sample rate
            16, // Sample size in bits
            1, // Number of channels (1 = mono)
            2, // Frame size (in bytes)
            16000, // Frame rate (same as sample rate)
            false // Big-endian (false for little-endian)
    );

    // File to save the recorded audio
    private static final File AUDIO_FILE = new File("recorded_audio.wav");

    public static void main(String[] args) {
        // Start recording from the microphone
        try {
            // Set up the microphone (TargetDataLine)
            TargetDataLine line = AudioSystem.getTargetDataLine(AUDIO_FORMAT);
            line.open(AUDIO_FORMAT);
            line.start();

            // Create a thread to write audio data to the file
            Thread recordingThread = new Thread(() -> {
                try {
                    // Create an AudioInputStream from the microphone
                    AudioInputStream audioStream = new AudioInputStream(line);

                    // Create a file to save the audio
                    AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, AUDIO_FILE);

                    System.out.println("Recording saved to: " + AUDIO_FILE.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Start the recording thread
            recordingThread.start();

            System.out.println("Recording... Press ENTER to stop.");
            System.in.read(); // Wait for user to press ENTER to stop recording

            // Stop the recording
            line.stop();
            line.close();
            System.out.println("Recording stopped.");
            SpeechToText speech = new SpeechToText();
            speech.speechtotext("C:\\Users\\diyaa\\gh-repo\\elasticsearch_minor\\recorded_audio.wav");

        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
