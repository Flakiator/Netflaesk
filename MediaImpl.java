import java.util.*;
import javax.swing.*;

public class MediaImpl implements Media{
String title;
String picture;
List<String> genre;
double score;
boolean favorite;
    public MediaImpl(String title, String picture, List<String> genre, double score, boolean favorite)
    {
        this.title = title;
        this.picture = picture;
        this.genre = genre;
        this.score = score;
        this.favorite = favorite;
    }

    public void play(String message)
    {
    //Udkast til hvordan vores playfunktion ca skal laves (Dette er uden data/specificering af media!)
        //Metoden skal være bundet til en knap "Play", som er en del af UI - ikke lavet endnu

        JOptionPane.showMessageDialog(null,"Afspiller: " + title,
                "Afspilningsvindue",JOptionPane.INFORMATION_MESSAGE);

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
