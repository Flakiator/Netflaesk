import java.util.*;
public class Mediaregistryimpl implements Mediaregistry
{
    //indl�ser database
    DataAcessImpl data = new DataAcessImpl();
    //initialize til film
    public List<Movie> initializeMovie()
    {
        List<Movie> m = new ArrayList<>();

        List<MediaImpl> media = initialize(data.loadMovies(), data.loadMPic());

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

        List<MediaImpl> media = initialize(data.loadSeries(), data.loadSPic());

        for (Media i: media)
        {
            s.add((Series) i);
        }
        return s;
    }
    // Generel initializer bruges ikke i main (derfor private)
    private List<MediaImpl> initialize(List<String> load, List<String> picture)
    {
        List<MediaImpl> medias = new ArrayList<>();
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

                medias.add(new Series(elements[0], elements[1].trim(), picture.get(i), genre, score, false,episodes,season,"Series"));
            }
            else
            {
                // Tilf�jer film objektet til liste over medier
                medias.add(new Movie(elements[0], elements[1].trim(), picture.get(i), genre, score, false,"Movies"));
            }
        }
        return medias;
    }

    public List<MediaImpl> search(String searchText, List<MediaImpl>filteredList)
    {
        List<MediaImpl> SortedBySearch = new ArrayList<>();
        searchText = searchText.toLowerCase();
        System.out.println(searchText);
        for(MediaImpl currentMedia : filteredList) {
            if (currentMedia.getTitle().toLowerCase().contains(searchText)) {
                SortedBySearch.add(currentMedia);
            }
        }
        return SortedBySearch;
    }

    public List<MediaImpl> filter(String genre, String Medietype, List<MediaImpl>AllMedia)
    {
        List<MediaImpl> filteredMedia = new ArrayList<>();
        // Tjekker for hvilken medietype, der skal vises.
        if(Medietype == "All")
        {
            for(MediaImpl currentMedia : AllMedia) {
                if (genre == "All")
                {
                    filteredMedia.add(currentMedia);
                }
                else if (currentMedia.getGenre().contains(genre)) {
                    filteredMedia.add(currentMedia);
                }
            }
        }
        else
        {
            for (MediaImpl currentMedia : AllMedia) {
                if (currentMedia.getMediaType().equals(Medietype)) {
                    if (genre == "All")
                    {
                        filteredMedia.add(currentMedia);
                    }
                    else if (currentMedia.getGenre().contains(genre)) {
                        filteredMedia.add(currentMedia);
                    }
                }
            }
        }
        return filteredMedia;
    }

    public String[] getAllGenre(List<MediaImpl> medias)
    {
        List<String> genresl = new ArrayList<>();
        genresl.add("All");
        for (MediaImpl m : medias)
        {
            List<String> tmp = m.getGenre();
            for (String genre: tmp)
            {
                if (genresl.contains(genre))
                {
                }
                else genresl.add(genre);
            }
        }
        String[] genres = new String[genresl.size()];
        for (int i = 0; i < genres.length; i++)
        {
            genres[i] = genresl.get(i);
        }
        return genres;
    }
}
