package Domain;

import java.util.*;
public abstract class MediaImpl implements Media {
    String title;
    String picture;
    List<String> genre;
    Double score;
    String year;
    boolean favorite;
    String mediatype;

    public MediaImpl(String title, String year, String picture, List<String> genre, Double score, boolean favorite, String mediatype) {
        this.title = title;
        this.picture = picture;
        this.genre = genre;
        this.score = score;
        this.favorite = favorite;
        this.year = year;
        this.mediatype = mediatype;
    }

    public boolean isFavorite() {
        return this.favorite;
    }

    public String getTitle() {
        return this.title;
    }

    public Double getScore() {
        return this.score;
    }

    public String getYear(){ return this.year;}

    public List<String> getGenre() {
        return this.genre;
    }

    public String getPicture()
    {
        return this.picture;
    }
    public String getMediaType()
    {
        return this.mediatype;
    }
    public void setFavorite(boolean status)
    {
        this.favorite = status;
    }
}