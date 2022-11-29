public interface Media
{
    void play(String message);
    void display();
    void openMedia();
    void addToFavorites();
    boolean isFavorite();
    String getTitle();
    double getScore();
    String getGenre();
}
