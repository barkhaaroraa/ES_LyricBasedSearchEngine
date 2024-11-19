package Ranking;


public class SongSimilarity {
    private String trackName;
    private String artistName;
    private double similarityScore;

    public SongSimilarity(String trackName, String artistName, double similarityScore) {
        this.trackName = trackName;
        this.artistName = artistName;
        this.similarityScore = similarityScore;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtistName() {
        return artistName;
    }

    public double getScore() {
        return similarityScore;
    }

    @Override
    public String toString() {
        return "Track: " + trackName + ", Artist: " + artistName + ", Similarity Score: " + similarityScore;
    }
}
