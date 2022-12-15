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

    List<MediaImpl> medias;

    @BeforeEach
    void setUp() {
        database = new DataAccessImpl();
        mediaReg = new Mediaregistryimpl();
        try {
            medias = mediaReg.initializeAllMedia();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void tearDown() {
        database = null;
        mediaReg = null;
        medias = null;
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
        // ser om der er lige mange medier læst i databasen som der bliver lavet om til objekter
        assertEquals(medias,series + movies);
    }
    @Test
    void initializeAllMediaduplicates() {
        // sætter medias, movies og series til forskellige værdier
        int count = 0;
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
        List<MediaImpl> themedias;
        int count = 0;
        themedias = mediaReg.search(searchtext,medias);
        for (MediaImpl m : themedias)
        {
            if (m.getTitle().contains("Bad"))
            {
                count++;
            }
        }
        // Sammenligner antallet af film med Bad i navnet med antallet af film med Bad i navnet som search returnerer
        assertEquals(2,count);
    }

    @Test
    void addToFavorites() {
        int count = 0;
        // Tager et tilfældigt medie mellem index 0 og 10
        MediaImpl media1 = medias.get((int)(Math.random()*(10+1)));
        media1.setFavorite(false);
        mediaReg.addToFavorites(media1);
        // Tjekker om mediet er skiftet status til true og om den lokale favorit liste har mediet i sig
        try {
            if (media1.isFavorite() && database.loadFavorites().contains(media1.getTitle()))
            {
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1,count);
    }

    @Test
    void removeFromFavorites() {
        int count = 0;
        // Tager et tilfældigt medie mellem index 0 og 10
        MediaImpl media1 = medias.get((int)(Math.random()*(10+1)));
        media1.setFavorite(true);
        mediaReg.removeFromFavorites(media1);
        // Tjekker om mediet er skiftet status til false og om den lokale favorit liste har fjernet mediet
        try {
            if (!media1.isFavorite() && !database.loadFavorites().contains(media1.getTitle()))
            {
                count++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(1,count);
    }
    }