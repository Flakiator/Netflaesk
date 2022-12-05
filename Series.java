import java.util.List;

public class Series extends MediaImpl
{
    List<Integer> series;
    int seasons;
    public Series(String title, String year, String picture, List<String> genre, String score, boolean favorite,List<Integer> series, int seasons) {
        super(title, year, picture, genre, score, favorite);
        this.series = series;
        this.seasons = seasons;
    }
}
