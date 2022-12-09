import java.util.*;
public class Mediaregistryimpl implements Mediaregistry
{
    //indl�ser database
    DataAcessImpl data = new DataAcessImpl();
    //initialize til film
    public List<Movie> initializeMovie()
    {
        List<Movie> m = new ArrayList<>();

        List<Media> media = initialize(data.loadMovies(), data.loadMPic());

        for (Media i: media)
        {
           m.add((Movie)i);
        }
        return m;
    }
    //initialize til serier
    public List<Series> initializeSeries()
    {
        List<Series> s = new ArrayList<>();

        List<Media> media = initialize(data.loadSeries(), data.loadSPic());

        for (Media i: media)
        {
            s.add((Series) i);
        }
        return s;
    }
    // Generel initializer bruges ikke i main (derfor private)
    private List<Media> initialize(List<String> load, List<String> picture)
    {
        List<Media> medias = new ArrayList<>();
        for (int i = 0; i < load.size();i++)
        {
            // Opdeler alle film (og deres, �rstal genre osv. i en liste "elements")
            String[] elements = load.get(i).trim().split(";");
            //elements[0] = title
            //elements[1] = �rstal
            //elements[2] = genre
            //elements[3] = score
            //elements[4] = s�son-episoder

            // Laver opdeler genrer i sin egen liste
            String[] genres = elements[2].split(",");
            // tilf�jer dem til ArrayList
            List<String> genre = new ArrayList<>();
            for (String g : genres) {
                genre.add(g.trim());
            }
            double score = Double.parseDouble(elements[3].trim().replace(',','.'));
            // Tager h�jde for om det er en series
            if (elements.length > 4)
            {
                // splitter s�son-episode op
                String[] series = elements[4].split(",");
                // s�tter s�son til at v�re lig med l�ngden af series
                int season = series.length;
                List<String> episodes = new ArrayList<>();
                for (String s: series)
                {
                    // fjerner s�son nummer der st�r foran episoderne ved at bruge lastindexof med parameteren "-"
                    episodes.add(s.substring(s.lastIndexOf("-") + 1).trim());
                }
                // Tilf�jer serie objektet til liste over medier

                medias.add(new Series(elements[0], elements[1].trim(), picture.get(i), genre, score, false,episodes,season));
            }
            else
            {
                // Tilf�jer film objektet til liste over medier
                medias.add(new Movie(elements[0], elements[1].trim(), picture.get(i), genre, score, false));
            }
        }
        return medias;
    }

    public List<MediaImpl> search(String SearchText, List<MediaImpl>FilteredeList)
    {
        List<MediaImpl> SortedBySearch = new ArrayList<>();
        SearchText = SearchText.toLowerCase();
        System.out.println(SearchText);
        for(MediaImpl currentMedia : FilteredeList) {
            if (currentMedia.getTitle().toLowerCase().contains(SearchText)) {
                SortedBySearch.add(currentMedia);
            }
        }
        return SortedBySearch;
    }

    public List<MediaImpl> filter(String Genre, String Medietype, List<MediaImpl>AllMedia)
    {

        List<MediaImpl> FilteredMedia = new ArrayList<>();
        //String Genre = (String) genreBox.getSelectedItem();

        // Tjekker for hvilken medietype, der skal vises.
        if(Medietype == "All")
        {
            for(MediaImpl currentMedia : AllMedia) {
                if (Genre == "All")
                {
                    FilteredMedia.add(currentMedia);
                }
                else if (currentMedia.getGenre().contains(Genre)) {
                    FilteredMedia.add(currentMedia);
                }
            }
        }
        else
        {
            for (MediaImpl currentMedia : AllMedia) {
                if (currentMedia.getMediaType().equals(Medietype)) {
                    if (Genre == "All")
                    {
                        FilteredMedia.add(currentMedia);
                    }
                    else if (currentMedia.getGenre().contains(Genre)) {
                        FilteredMedia.add(currentMedia);
                    }
                }
            }
        }
        return FilteredMedia;
    }
}
