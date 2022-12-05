import java.util.*;
public class Demo
{
    public static void main(String[] args)
    {
        DataAcessImpl dinmor = new DataAcessImpl();
        List<String>  movies = dinmor.loadMovies();

        for (String lort : movies)
        {
            System.out.println(lort);
        }
    }
}
