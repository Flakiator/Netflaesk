import java.util.List;

public interface Media
{
    boolean isFavorite();
    String getTitle();
    Double getScore();
    List<String> getGenre();
    String getPicture();
    void setFavorite(boolean status);
}