import java.util.List;

public class Series extends MediaImpl {
    private List<String> episodes;
    private int seasons;
    public Series(String title, String year, String picture, List<String> genre, double score, boolean favorite, List<String> episodes, int seasons) {
        super(title, year, picture, genre, score, favorite);
        this.episodes = episodes;
        this.seasons = seasons;
    }

    public List<String> getEpisodes() {
        return this.episodes;
    }

    public int getSeasons() {
        return this.seasons;
    }
}
