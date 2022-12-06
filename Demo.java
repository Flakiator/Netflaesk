import java.util.*;

public class Demo
{
    public static void main(String[] args)
    {
        Mediaregistryimpl loader = new Mediaregistryimpl();

        List<Movie> movies = loader.initializeMovie();
        List<Series> series = loader.initializeSeries();
        List<Media> medias = new ArrayList<>();
        medias.addAll(movies);
        medias.addAll(series);
        System.out.println(movies.get(0).getTitle());
    }
}
