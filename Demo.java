import java.io.IOException;
import java.util.*;

public class Demo
{
    public static void main(String[] args) throws IOException {
        Mediaregistryimpl loader = new Mediaregistryimpl();

        List<Movie> movies = loader.initializeMovie();
        List<Series> series = loader.initializeSeries();
        List<MediaImpl> medias = new ArrayList<>();
        medias.addAll(movies);
        medias.addAll(series);
        loader.addToFavorites(medias.get(1));
        loader.removeFromFavorites(medias.get(0));
        System.out.println(medias.get(1).isFavorite());
    }
}
