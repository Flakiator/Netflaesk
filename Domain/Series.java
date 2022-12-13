package Domain;

import java.util.List;

public class Series extends MediaImpl
{
    private List<String> episodes;
    private int seasons;

    public Series(String title, String year, String picture, List<String> genre, double score, boolean favorite,List<String> episodes, int seasons,String mediatype) {
        super(title, year, picture, genre, score, favorite, mediatype);
        this.episodes = episodes;
        this.seasons = seasons;
    }

    public String[] getEpisodes() {
        return this.episodes.toArray(new String[0]);
    }
    public int getSeasons()
    {
        return this.seasons;
    }
}