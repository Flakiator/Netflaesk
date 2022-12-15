package Domain;

import Database.DataAccessImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

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
    void initializeAllMedia() {
        // s�tter medias, movies og series til forskellige v�rdier
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
        // ser om der er lige mange medier l�st i data basen som der bliver lavet om til objekter
        assertEquals(medias,series + movies);
    }

    @Test
    void search() {
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