import java.util.*;

public class Demo
{
    public static void main(String[] args)
    {
        Mediaregimpl loader = new Mediaregimpl();

        List<Media> movies = loader.initializemovie();
        List<Media> series = loader.initializeseries();

        System.out.println(movies.get(0).getScore());
        System.out.println(series.get(0).getSeasons());
    }
}
