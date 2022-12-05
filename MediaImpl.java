import java.util.*;
public class MediaImpl implements Media{
String title;
String picture;
List<String> genre;
String score;
double year;
boolean favorite;
    public MediaImpl(String title, double year, String picture, List<String> genre, String score, boolean favorite)
    {
        this.title = title;
        this.picture = picture;
        this.genre = genre;
        this.score = score;
        this.favorite = favorite;
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
    public double getScore()
    {
        return 0;
    }
    public String getGenre()
    {
        return null;
    }
}
