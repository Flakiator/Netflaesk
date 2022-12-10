import java.util.*;
public interface DataAccess
{
    List<String> loadMedia(String path);
    List<String> loadPic(String path);
    List<String> loadFavorites();
    void saveFavorite(String title);
}
