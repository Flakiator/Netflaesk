import java.util.*;
public interface Mediareg
{
    public List<Media> initialize();
    public List<Media> search(String text);
    public List<Media> filter(String genre);
}
