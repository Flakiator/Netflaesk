import java.util.*;
public interface Mediaregistry
{
    private List<Media> initialize(List<String> load, List<String> picture) {
        return null;
    }

    public List<MediaImpl> search(String text);
    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia);
}
