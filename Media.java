import java.util.List;

public interface Media
{
    void play(String message);
    void display();
    void openMedia();
    void addToFavorites();
    boolean isFavorite();
    String getTitle();
    String getScore();
    List<String> getGenre();
}
