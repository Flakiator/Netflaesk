import java.util.*;
import java.io.*;

public class DataAcessImpl implements DataAcess
{
    //private String favorites = "Data/favorites.txt";
    private String moviesPath = "Data/film.txt";
    private String mPicPath = "Data/filmplakater";
    private String seriesPath = "Data/serier.txt";
    private String sPicPath = "Data/serieforsider";

    private List<String> favorites = new ArrayList<>();

    public List<String> loadMedia(String path)
    {
        // Laver en liste medie navnene kan st� i
        List<String> media = new ArrayList<>();
        try {
            // Giver file navnet p� vejen til filen
            File file = new File(path);
            // Laver et scanner objekt
            Scanner myReader = new Scanner(file);
            // Laver et while loop der bliver ved indtil der ikke er flere linjer i txt filen
            while (myReader.hasNextLine())
            {
                // S�tter en linje ind i strengen "data"
                String data = myReader.nextLine();
                // Tilf�jer data som element i "media"
                media.add(data);
            }
            // Lukker myReader da vi nu har l�st hele txt filen igennem
            myReader.close();
            Collections.sort(media);
            return media;
            // tager h�jde for hvis filen ikke eksisterer
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public List<String> loadMovies()
    {
        List<String> media = loadMedia(moviesPath);
        return media;
    }
    public List<String> loadSeries()
    {
        List<String> media = loadMedia(seriesPath);
        return media;
    }

    public List<String> loadPic(String path)
    {
        // Laver en liste billede navnene kan st� i
        List<String> mediaPic = new ArrayList<>();
        // Giver file navnet p� vejen til filen
        File file = new File(path);
        // bruger gemmer alle billede navnene i en liste "allFiles" med listFiles metoden
        File[] allFiles = file.listFiles();
        // itererer gennem allFiles og gemmer navnene p� billederne i "mediaPic"
        for (int i = 0; i < allFiles.length; i++)
        {
            mediaPic.add(String.valueOf(allFiles[i]));
        }
        Collections.sort(mediaPic);
        return mediaPic;
    }
    public List<String> loadMPic()
    {
        return loadPic(mPicPath);
    }
    public List<String> loadSPic()
    {
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
