import java.util.*;
public interface Mediaregistry
{
    private List<Media> initialize(List<String> load, List<String> picture) {
        return null;
    }

    public List<Media> search(String text);
    public List<Media> filter(String genre);
}
