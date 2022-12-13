import Domain.MediaImpl;
import Domain.Mediaregistryimpl;
import Domain.Movie;
import Domain.Series;

import java.io.IOException;
import java.util.*;

public class Demo
{
    public static void main(String[] args) throws IOException {
        Mediaregistryimpl loader = new Mediaregistryimpl();


        List<MediaImpl> medias = loader.initializeAllMedia();
        loader.addToFavorites(medias.get(1));
        loader.removeFromFavorites(medias.get(0));
        System.out.println(medias.get(1).isFavorite());
    }
}
