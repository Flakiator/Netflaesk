import java.util.*;
public class Mediaregimpl
{
    public List<Media> initialize()
    {
        // Laver et DataAcess objekt
        DataAcessImpl data = new DataAcessImpl();
        // S�tter de n�dv�ndige lister op
        List<String> movie = data.loadMovies();
        List<String> movieP = data.loadMPic();
        List<String> series = data.loadSeries();
        List<String> seriesP = data.loadSPic();

        initializemovie(data.loadMovies(), data.loadMPic());
        return null;
    }

    public List<Media> initializemovie(List<String> load, List<String> picture)
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
            // Laver opdeler genrer i sin egen liste
            String[] genres = elements[2].split(",");
            // tilf�jer dem til ArrayList
            List<String> genre = new ArrayList<>();
            for (int j = 0; j < genres.length; j++)
            {
                genre.add(genres[j].trim());
            }
            // Tager h�jde for om det er en series
            if (elements.length > 3)
            {

            }
            else
            {
                medias.add(new Movie(elements[0], elements[1], picture.get(i), genre, elements[3], false));
            }
        }
        return medias;
    }

}
