import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class DataAcessImpl implements DataAcess
{
    private String moviesPath = "Data/film.txt";
    private String seriesPath = "Data/serier.txt";
    private String mPicPath = "Data/filmplakater";
    private String sPicPath = "Data/serieforsider";
    private String favorites = "Data/favorites.txt";

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
         List<String> movies = loadMedia(moviesPath);
         return movies;
    }
    public List<String> loadSeries()
    {
        List<String> series = loadMedia(seriesPath);
        return series;
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
        return mediaPic;
    }
    public List<String> loadMPic()
    {
        List<String> mPic = loadPic(mPicPath);
        return mPic;
    }
    public List<String> loadSPic()
    {
        List<String> sPic = loadPic(sPicPath);
        return sPic;
    }
    public List<String> loadFavorites()
    {
        return null;
    }
    public void saveFavorites(List<String> favorites)
    {

    }
}
