import java.util.*;
public abstract class MediaImpl implements Media{
String title;
String picture;
List<String> genre;
String score;
String year;
boolean favorite;
    public MediaImpl(String title, String year, String picture, List<String> genre, String score, boolean favorite)
    {
        this.title = title;
        this.picture = picture;
        this.genre = genre;
        this.score = score;
        this.favorite = favorite;
        this.year = year;
    }

    public void play(String message)
    {

    }
    public void display()
    {

    }
    public void openMedia()
    {

    }
    public void addToFavorites()
    {

    }
    public boolean isFavorite()
    {
        return false;
    }
    public String getTitle()
    {
        return this.title;
    }
    public String getScore()
    {
        return this.score;
    }
    public List<String> getGenre()
    {
        return this.genre;
    }

    public List<String> getEpisodes() {
        return null;
    }
}
