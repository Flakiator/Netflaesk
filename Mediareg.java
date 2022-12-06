import java.util.*;
public interface Mediareg
{
    public List<Media> initialize(List<String> load, List<String> picture);
    public List<Media> search(String text);
    public List<Media> filter(String genre);
}
