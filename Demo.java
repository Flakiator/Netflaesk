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
            List<Media> medias = new ArrayList<>();
            for (int i = 0; i < movie.size();i++)
            {
                // Opdeler alle film (og deres, årstal genre osv. i en liste "elements")
                String[] elements = movie.get(i).split(";");
                //
                String score = elements[3];
                double year = Double.parseDouble(elements[1]);
                // Test print
                System.out.println(elements[1].trim());
                // Laver opdeler genrer i sin egen liste
                String[] genres = elements[2].split(",");
                // tilføjer dem til ArrayList
                List<String> genre = new ArrayList<>();
                for (int j = 0; j < genres.length; j++)
                {
                    genre.add(genres[j].trim());
                }

                for (String g: genres)
                {
                    System.out.println(g);
                }
                medias.add(new Movie(elements[0], year, movieP.get(i),genre, score, false));
            }
            System.out.println("MEDIA:");
            System.out.println(medias.get(0).getTitle());
        }
    }
