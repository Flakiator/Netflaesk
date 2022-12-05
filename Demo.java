import java.util.*;

public class Demo
{
    public static void main(String[] args)
    {
            // Laver et DataAcess objekt
            DataAcessImpl data = new DataAcessImpl();
            // Sætter de nødvændige lister op
            //List<String> movie = data.loadMovies();
            List<String> movieP = data.loadMPic();
            List<String> series = data.loadSeries();
            List<String> seriesP = data.loadSPic();
            List<Media> medias = new ArrayList<>();
            for (int i = 0; i < data.loadMovies().size();i++)
            {
                // Opdeler alle film (og deres, årstal genre osv. i en liste "elements")
                String[] elements = data.loadMovies().get(i).split(";");
                //
                String score = elements[3];
                // Laver opdeler genrer i sin egen liste
                String[] genres = elements[2].split(",");
                // tilføjer dem til ArrayList
                List<String> genre = new ArrayList<>();
                for (int j = 0; j < genres.length; j++)
                {
                    genre.add(genres[j].trim());
                }
                medias.add(new Movie(elements[0], elements[1], movieP.get(i),genre, score, false));
            }
            System.out.println("MEDIA:");
            System.out.println(medias.get(0).getTitle());
        }
    }
