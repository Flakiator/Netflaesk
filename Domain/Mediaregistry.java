package Domain;

import java.util.*;
public interface Mediaregistry
{
    private List<MediaImpl> initializeAllMedia() {
        return null;
    }

    public List<MediaImpl> search(String SearchText, List<MediaImpl>FilteredList);
    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia);
}
