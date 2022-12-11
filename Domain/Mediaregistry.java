package Domain;

import java.util.*;
public interface Mediaregistry
{
    private List<MediaImpl> initialize(List<String> load, List<String> picture) {
        return null;
    }

    public List<MediaImpl> search(String SearchText, List<MediaImpl>FilteredList);
    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia);
}
