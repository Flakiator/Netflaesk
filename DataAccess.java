import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public interface DataAccess
{
    List<String> loadMedia(String path);
    List<String> loadPic(String path);
    List<String> loadFavorites() throws FileNotFoundException;
    void updateFavorite(List<String> favorites) throws IOException;
}
