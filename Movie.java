import java.util.List;

public class Movie extends MediaImpl
{
    private String mediatype = "Movies";
    public Movie(String title, String year, String picture, List<String> genre, double score, boolean favorite) {
        super(title, year, picture, genre, score, favorite);
    }

    String getMediaType()
    {
        return this.mediatype;
    }
}
