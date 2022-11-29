import java.util.*;
public interface DataAcess
{
    List<String> loadMedia(String path);
    List<String> loadMPic();
    List<String> loadSPic();
    List<String> loadFavorites();
    void saveFavorites(List<String> favorites);
}
