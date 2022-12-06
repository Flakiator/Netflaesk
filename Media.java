import java.util.List;

public interface Media
{
    boolean isFavorite();
    String getTitle();
    Double getScore();
    List<String> getGenre();

    // Disse to funktioner er kun seriesspecifikke, og det giver derfor ikke mening at have dem med i MediaInterfacet.
    List<String> getEpisodes();

    int getSeasons();
}
// Flere af disse skal over i main. Det her var lavet efter det gamle UML-diagram.
/*
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
    List<String> getEpisodes();

    int getSeasons();
}
*/