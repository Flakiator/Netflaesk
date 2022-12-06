import java.util.List;

public class Movie extends MediaImpl
{
    public Movie(String title, String year, String picture, List<String> genre, double score, boolean favorite) {
        super(title, year, picture, genre, score, favorite);
    }
    public List<String> getEpisodes()
    {
        return null;
    }

    public int getSeasons()
    {
        return 0;
    }
}
