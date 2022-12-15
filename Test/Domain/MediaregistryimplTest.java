package Domain;

import Database.DataAccessImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MediaregistryimplTest {

    DataAccessImpl database;
    Mediaregistryimpl mediaReg;

    @BeforeEach
    void setUp() {
        database = new DataAccessImpl();
        mediaReg = new Mediaregistryimpl();
    }

    @AfterEach
    void tearDown() {
        database = null;
        mediaReg = null;
    }

    @Test
    void initializeAllMediaammounts() {
        // sætter medias, movies og series til forskellige værdier
        int medias = 0;
        int movies = 1;
        int series = 2;
        try {
            medias = mediaReg.initializeAllMedia().size();
            movies = database.loadMovies().size();
            series = database.loadSeries().size();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        // ser om der er lige mange medier læst i data basen som der bliver lavet om til objekter
        assertEquals(medias,series + movies);
    }
    @Test
    void initializeAllMediaduplicates() {
        // sætter medias, movies og series til forskellige værdier
        List<MediaImpl> medias = new ArrayList<>();
        int count = 0;
        try {
            medias = mediaReg.initializeAllMedia();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        List<String> titles = new ArrayList<>();
        for (MediaImpl m : medias)
        {
            titles.add(m.getTitle());
        }
        Set<String> unique_titles = new HashSet<>(titles);
        // Plus 1 fordi der både er en serie og film der hedder "fargo"
        if (unique_titles.size() + 1 < titles.size())
        {
            count++;
        }
        // Ser om der bliver lavet flere af de samme film
        assertEquals(0,count);
    }

    @Test
    void search()
    {
        String searchtext = "Bad";
        List<MediaImpl> medias;
        List<MediaImpl> themedias;
        int count = 0;
        try {
            medias = mediaReg.initializeAllMedia();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        themedias = mediaReg.search(searchtext,medias);
        for (MediaImpl m : themedias)
        {
            if (m.getTitle().contains("Bad"))
            {
                System.out.println(m.getTitle());
                count++;
            }
        }
        // Sammenligner antallet af film med Bad i navnet med antallet af film med Bad i navnet som search returnerer
        assertEquals(2,themedias.size());
    }

    @Test
    void filter() {

    }

    @Test
    void getAllGenre() {
    }

    @Test
    void addToFavorites() {
    }

    @Test
    void removeFromFavorites() {
    }
}