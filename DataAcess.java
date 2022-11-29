import java.util.*;
public interface DataAcess
{
    //List<String> loadMovies();
    List<String> loadSeries();
    List<String> loadMPic();
    List<String> loadSPic();
    List<String> loadFavorites();
    void saveFavorites(List<String> favorites);
}
