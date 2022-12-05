import java.util.*;

public class Demo
{
    public static void main(String[] args)
    {
            // Laver et DataAcess objekt
            DataAcessImpl data = new DataAcessImpl();
            // Sætter de nødvændige lister op
            List<String> movie = data.loadMovies();
            List<String> movieP = data.loadMPic();
            List<String> series = data.loadSeries();
            List<String> seriesP = data.loadSPic();

            for (int i = 0; i < movie.size();i++)
            {
                // Opdeler alle film (og deres, årstal genre osv. i en liste "elements")
                String[] elements = movie.get(i).split(";");
                // Test print
                //System.out.println(elements[0].trim());
                // Laver opdeler genrer i sin egen liste
                String[] genres = elements[2].split(",");
                // tilføjer dem til ArrayList
                List<String> genre = new ArrayList<>();
                for (int j = 0; j < genres.length; j++)
                {
                    genre.add(genres[j].trim());
                }
                System.out.println("genre til: " + elements[0]);
                for (String g: genre)
                {
                    System.out.println(g);
                }

                //for ()
                //new Media(elements[0],movieP.get(i),);
            }
        }
    }
