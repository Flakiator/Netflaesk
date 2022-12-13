package Domain;

import java.util.*;
public interface Mediaregistry
{
    public List<MediaImpl> initializeAllMedia();
    public List<MediaImpl> search(String SearchText, List<MediaImpl>FilteredList);
    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia);
    public String[] getAllGenre(List<MediaImpl> medias);
    public void addToFavorites(MediaImpl media);
    public void removeFromFavorites(MediaImpl media);
}
