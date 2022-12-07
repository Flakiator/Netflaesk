import java.util.*;
public abstract class MediaImpl implements Media {
    String title;
    String picture;
    List<String> genre;
    Double score;
    String year;
    boolean favorite;

    String mediatype = "All";

    public MediaImpl(String title, String year, String picture, List<String> genre, Double score, boolean favorite) {
        this.title = title;
        this.picture = picture;
        this.genre = genre;
        this.score = score;
        this.favorite = favorite;
        this.year = year;
    }

    public void addToFavorites() {

    }

    public boolean isFavorite() {
        return false;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getScore() {
        return this.score;
    }

    public List<String> getGenre() {
        return this.genre;
    }

    public String getPicture()
    {
        return this.picture;
    }

    String getMediaType()
    {
        return this.mediatype;
    }
}
