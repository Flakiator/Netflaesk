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
        List<String> media = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                media.add(data);
            }
            myReader.close();
            return media;
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
        List<String> mediaPic = new ArrayList<>();
        File file = new File(path);
        File[] allFiles = file.listFiles();
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
