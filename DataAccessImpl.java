import java.util.*;
import java.io.*;

public class DataAccessImpl implements DataAccess
{

    private List<String> favorites = new ArrayList<>();

    public List<String> loadMedia(String path)
    {
        // Laver en liste medie navnene kan stå i
        List<String> media = new ArrayList<>();
        try {
            // Giver file navnet på vejen til filen
            File file = new File(path);
            // Laver et scanner objekt
            Scanner myReader = new Scanner(file);
            // Laver et while loop der bliver ved indtil der ikke er flere linjer i txt filen
            while (myReader.hasNextLine())
            {
                // Sætter en linje ind i strengen "data"
                String data = myReader.nextLine();
                // Tilføjer data som element i "media"
                media.add(data);
            }
            // Lukker myReader da vi nu har læst hele txt filen igennem
            myReader.close();
            Collections.sort(media);
            return media;
            // tager højde for hvis filen ikke eksisterer
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public List<String> loadMovies()
    {
        //private String favorites = "Data/favorites.txt";
        String moviesPath = "Data/film.txt";
        return loadMedia(moviesPath);
    }
    public List<String> loadSeries()
    {
        String seriesPath = "Data/serier.txt";
        return loadMedia(seriesPath);
    }

    public List<String> loadPic(String path)
    {
        // Laver en liste billede navnene kan stå i
        List<String> mediaPic = new ArrayList<>();
        // Giver file navnet på vejen til filen
        File filepath = new File(path);
        // bruger gemmer alle billede navnene i en liste "allFiles" med listFiles metoden
        File[] allPictures = filepath.listFiles();
        // itererer gennem allFiles og gemmer navnene på billederne i "mediaPic"
        for (File picture : allPictures) {
            mediaPic.add(String.valueOf(picture));
        }
        Collections.sort(mediaPic);
        return mediaPic;
    }
    public List<String> loadMPic()
    {
        String mPicPath = "Data/filmplakater";
        return loadPic(mPicPath);
    }
    public List<String> loadSPic()
    {
        String sPicPath = "Data/serieforsider";
        return loadPic(sPicPath);
    }
    public List<String> loadFavorites()
    {
        return null;
    }
    public void saveFavorite(String title)
    {
    }
}
