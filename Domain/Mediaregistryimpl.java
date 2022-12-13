package Domain;

import Database.DataAccessImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class Mediaregistryimpl implements Mediaregistry
{
    //indlæser database
    private DataAccessImpl data = new DataAccessImpl();
    private List<String> favorites;
    // sætter favorites til den lokale favorit txt fil
    {
        try {
            favorites = data.loadFavorites();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //initialize til alle medier
    public List<MediaImpl> initializeAllMedia() throws FileNotFoundException
    {
        List<MediaImpl> series = initialize(data.loadSeries(), data.loadSPic());
        List<MediaImpl> movies = initialize(data.loadMovies(), data.loadMPic());
        List<MediaImpl> allmedia = new ArrayList<>(series);
        allmedia.addAll(movies);
        return allmedia;

    }
    // Generel initializer bruges ikke i main (derfor private)
    private List<MediaImpl> initialize(List<String> load, List<String> picture)
    {

        List<MediaImpl> medias = new ArrayList<>();
        for (int i = 0; i < load.size();i++)
        {
            // Opdeler alle film (og deres, årstal genre osv. i en liste "elements")
            String[] elements = load.get(i).trim().split(";");
            //elements[0] = title
            //elements[1] = årstal
            //elements[2] = genre
            //elements[3] = score
            //elements[4] = sæson-episoder

            // sætter favorite til false hvis de ikke er på den lokale favorit liste
            boolean favorite = favorites.contains(elements[0]);
            // Tjekker om mediet er på den lokale favorit liste
            // hvis det er skal objektet intialiseres som favorit
            // Laver opdeler genrer i sin egen liste
            String[] genres = elements[2].split(",");
            // tilføjer dem til ArrayList
            List<String> genre = new ArrayList<>();
            for (String g : genres) {
                genre.add(g.trim());
            }
            double score = Double.parseDouble(elements[3].trim().replace(',','.'));
            // Tager højde for om det er en series
            if (elements.length > 4)
            {
                // splitter sæson-episode op
                String[] series = elements[4].split(",");
                // sætter sæson til at være lig med længden af series
                int season = series.length;
                List<String> episodes = new ArrayList<>();
                for (String s: series)
                {
                    // fjerner sæson nummer der står foran episoderne ved at bruge lastindexof med parameteren "-"
                    episodes.add(s.substring(s.lastIndexOf("-") + 1).trim());
                }
                // Tilføjer serie objektet til liste over medier

                medias.add(new Series(elements[0], elements[1].trim(), picture.get(i), genre, score, favorite,episodes,season,"Series"));
            }
            else
            {
                // Tilføjer film objektet til liste over medier
                medias.add(new Movie(elements[0], elements[1].trim(), picture.get(i), genre, score, favorite,"Movie"));
            }
        }
        return medias;
    }

    public List<MediaImpl> search(String searchText, List<MediaImpl>filteredList)
    {
        List<MediaImpl> SortedBySearch = new ArrayList<>();
        searchText = searchText.toLowerCase();
        System.out.println(searchText);
        for(MediaImpl currentMedia : filteredList) {
            if (currentMedia.getTitle().toLowerCase().contains(searchText)) {
                SortedBySearch.add(currentMedia);
            }
        }
        return SortedBySearch;
    }

    public List<MediaImpl> filter(String genre, String Mediatype, List<MediaImpl>AllMedia)
    {
        List<MediaImpl> filteredMedia = new ArrayList<>();
            // Tjekker for hvilken medietype, der skal vises.
            for(MediaImpl currentMedia : AllMedia) {
                if (Mediatype.equals("All")){
                    if (genre.equals("All")) {
                        filteredMedia.add(currentMedia);
                    } else if (currentMedia.getGenre().contains(genre)) {
                        filteredMedia.add(currentMedia);
                    }
                }
                else {
                    if (currentMedia.getMediaType().equals(Mediatype)) {
                        if (genre.equals("All")) {
                            filteredMedia.add(currentMedia);
                        }
                        else if (currentMedia.getGenre().contains(genre)) {
                            filteredMedia.add(currentMedia);
                        }
                    }
                }
            }
        return filteredMedia;
    }

    public String[] getAllGenre(List<MediaImpl> medias)
    {
        List<String> genresl = new ArrayList<>();
        genresl.add("All");
        for (MediaImpl m : medias)
        {
            List<String> tmp = m.getGenre();
            for (String genre: tmp)
            {
                if (genresl.contains(genre));

                else genresl.add(genre);
            }
        }
        return genresl.toArray(new String[0]);
    }
    public void addToFavorites(MediaImpl media) {
        String title = media.getTitle();
        // tilføjer titlen til favoritlisten jo mindre den allerede er der
        if (!favorites.contains(title))
        {
            favorites.add(title);
        }
            // Sætter favorit status til true på objektet
            media.setFavorite(true);
        try {
            data.updateFavorite(favorites);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeFromFavorites(MediaImpl media) {
        String title = media.getTitle();
        // fjerner titlen fra favorit listen i mediaregistry
        favorites.remove(title);
        // sætter mediet til false på objektet
        media.setFavorite(false);
        // opdaterer den lokale favorit liste
        try {
            data.updateFavorite(favorites);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}