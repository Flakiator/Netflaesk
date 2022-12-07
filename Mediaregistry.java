import java.util.*;
public interface Mediaregistry
{
    public List<Media> initialize(List<String> load, List<String> picture);
    public List<MediaImpl> search(String text);
    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia);
}
