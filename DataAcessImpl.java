import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Scanner;

public class DataAcessImpl implements DataAcess{
    private static String moviesPath = "Data/film.txt";
    private String seriesPath = "Data/serier.txt";
    private String mPicPath = "Data/filmplakater";
    private String sPicPath = "Data/serieforsider";
    private String favorites = "Data/favorites.txt";

    public List<String> loadMovies()
    {
        List<String> movies = new ArrayList<>();
        try {
            File file = new File(moviesPath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                movies.add(data);
            }
            myReader.close();
            return movies;
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }
    public List<String> loadSeries()
    {
        List<String> series = new ArrayList<>();
        try {
            File file = new File(seriesPath);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                series.add(data);
            }
            myReader.close();
            return series;
        } catch (FileNotFoundException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public List<String> loadMPic()
    {
        return null;
    }
    public List<String> loadSPic()
    {
        return null;
    }
    public List<String> loadFavorites()
    {
        return null;
    }
    public void saveFavorites(List<String> favorites)
    {

    }
}
