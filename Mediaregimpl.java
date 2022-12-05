import java.util.*;
public class Mediaregimpl
{
    public List<Media> initialize()
    {
        // Laver et DataAcess objekt
        DataAcessImpl data = new DataAcessImpl();
        // Sætter de nødvændige lister op
        List<String> movie = data.loadMovies();
        List<String> movieP = data.loadMPic();
        List<String> series = data.loadSeries();
        List<String> seriesP = data.loadSPic();

        for (int i = 0; i < movie.size(); i++);
        {
            String[] elements = movie.get(0).split(";");
            for (String e: elements)
            {
                System.out.println(e);
            }
        }
        return null;
    }
}
