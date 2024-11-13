package ElasticSearchOperations;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Songs {
    private String track_album_name;
    private String playlist_genre;
    private String playlist_subgenre;
    private String track_artist;
    private String language;
    private int id;
    private String lyrics;
    private String track_name;

    // Annotated constructor for JSON deserialization
    @JsonCreator
    public Songs(
            @JsonProperty("track_album_name") String track_album_name,
            @JsonProperty("playlist_genre") String playlist_genre,
            @JsonProperty("playlist_subgenre") String playlist_subgenre,
            @JsonProperty("track_artist") String track_artist,
            @JsonProperty("language") String language,
            @JsonProperty("id") int id,
            @JsonProperty("lyrics") String lyrics,
            @JsonProperty("track_name") String track_name) {
        this.track_album_name = track_album_name;
        this.playlist_genre = playlist_genre;
        this.playlist_subgenre = playlist_subgenre;
        this.track_artist = track_artist;
        this.language = language;
        this.id = id;
        this.lyrics = lyrics;
        this.track_name = track_name;
    }

    // Getters only
    public String getTrack_album_name() { return track_album_name; }
    public String getPlaylist_genre() { return playlist_genre; }
    public String getPlaylist_subgenre() { return playlist_subgenre; }
    public String getTrack_artist() { return track_artist; }
    public String getLanguage() { return language; }
    public int getId() { return id; }
    public String getLyrics() { return lyrics; }
    public String getTrack_name() { return track_name; }
}


