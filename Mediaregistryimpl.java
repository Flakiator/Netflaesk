import java.util.*;
public class Mediaregistryimpl implements Mediaregistry
{
    //indlæser database
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
            // Opdeler alle film (og deres, årstal genre osv. i en liste "elements")
            String[] elements = load.get(i).trim().split(";");
            //elements[0] = title
            //elements[1] = årstal
            //elements[2] = genre
            //elements[3] = score
            //elements[4] = sæson-episoder

            // Laver opdeler genrer i sin egen liste
            String[] genres = elements[2].split(",");
            // tilføjer dem til ArrayList
            List<String> genre = new ArrayList<>();
            for (String g : genres) {
                genre.add(g.trim());
            }
            double score = Double.parseDouble(elements[3].trim().replace(',','.'));
            // Tager højde for om det er en series
            if (elements.length > 4)
            {
                // splitter sæson-episode op
                String[] series = elements[4].split(",");
                // sætter sæson til at være lig med længden af series
                int season = series.length;
                List<String> episodes = new ArrayList<>();
                for (String s: series)
                {
                    // fjerner sæson nummer der står foran episoderne ved at bruge lastindexof med parameteren "-"
                    episodes.add(s.substring(s.lastIndexOf("-") + 1).trim());
                }
                // Tilføjer serie objektet til liste over medier

                medias.add(new Series(elements[0], elements[1].trim(), picture.get(i), genre, score, false,episodes,season));
            }
            else
            {
                // Tilføjer film objektet til liste over medier
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
