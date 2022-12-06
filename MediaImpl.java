import java.util.*;
public abstract class MediaImpl implements Media{

import javax.swing.*;

public class MediaImpl implements Media{

String title;
String picture;
List<String> genre;
Double score;
String year;
boolean favorite;
    public MediaImpl(String title, String year, String picture, List<String> genre, Double score, boolean favorite)
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
    //Udkast til hvordan vores playfunktion ca skal laves (Dette er uden data/specificering af media!)
        //Metoden er for nu "bundet" til makePanel() i Main

            JOptionPane.showMessageDialog(null, "Afspiller: " + title,
                    "Afspilningsvindue", JOptionPane.INFORMATION_MESSAGE);
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
    public Double getScore()
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
