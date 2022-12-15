package Domain;

import java.util.List;

public interface Media
{
    boolean isFavorite();
    String getTitle();
    Double getScore();
    String getYear();
    List<String> getGenre();
    String getPicture();
    void setFavorite(boolean status);
}