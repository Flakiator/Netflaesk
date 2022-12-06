import java.util.*;
public class Mediaregimpl
{
    //indl�ser database
    DataAcessImpl data = new DataAcessImpl();
    //initialize til film
    public List<Media> initializemovie()
    {
        return initialize(data.loadMovies(), data.loadMPic());
    }
    //initialize til serier
    public List<Media> initializeseries()
    {
        return initialize(data.loadSeries(), data.loadSPic());
    }
    // Generel initializer
    public List<Media> initialize(List<String> load, List<String> picture)
    {
        List<Media> medias = new ArrayList<>();
        for (int i = 0; i < load.size();i++)
        {
            // Opdeler alle film (og deres, �rstal genre osv. i en liste "elements")
            String[] elements = load.get(i).split(";");
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

}
