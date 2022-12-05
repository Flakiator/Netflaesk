import javax.print.attribute.standard.Media;
import java.util.List;

public class Movie extends MediaImpl
{
    public Movie(String title, double year, String picture, List<String> genre, String score, boolean favorite) {
        super(title, year, picture, genre, score, favorite);
    }
}
