import Domain.MediaImpl;
import Domain.Mediaregistryimpl;
import Domain.Movie;
import Domain.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JFrame frame;
    private static JPanel overview;
    private static JFrame popup = new JFrame("Netflæsk"); //Laver en frame
    private static JPanel popuppanel;
    private static JComboBox<String> genreBox;
    private static JComboBox<String> mediaBox;

    private static JTextField searchBar = new JTextField("Type here...");
    private static List<MediaImpl> allMedias;

    private static List<MediaImpl> current;
    private static Mediaregistryimpl mediaReg;
    private static List<MediaImpl> favorites = new ArrayList<>();

    private static String state = "Main";

    public static void main(String[] args) {
        mediaReg = new Mediaregistryimpl();
        List<Movie> allMovies = mediaReg.initializeMovie();
        List<Series> allSeries = mediaReg.initializeSeries();
        // Samler medier i en liste
        allMedias = new ArrayList<>(allMovies);
        allMedias.addAll(allSeries);
        current = new ArrayList<>(allMedias);
        makeFrame();
    }

    public static void makeFrame() {

        frame = new JFrame("Netflæsk"); //Laver en frame
        ImageIcon img = new ImageIcon("Data/logo.png");
        frame.setIconImage(img.getImage());
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har IKKE en standard size, ER redigerbar af client
        frame.setResizable(true);

        // Laver først menubar der kan filtrer medier
        makeMenuBar();
        // Laver panelet og knapperne til at vise medier
        makePanel();
        makebuttons(current);
        // Sætter størrelsen på vinduet og gør det synligt
        overview.setBackground(Color.darkGray);
        frame.setSize(850, 800);
        frame.setVisible(true); //frame bliver synlig
    }
    public static void makeMenuBar() {
        //Laver MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        // definerer genrer og medier så de kan blive reset hvis der bliver klikket på home knappen
        String[] genres = mediaReg.getAllGenre(allMedias);
        String[] medias = {"All", "Movie", "Series"};
        // Laver en hjem knap
        JButton homebutton = new JButton("Home");
        homebutton.addActionListener(e ->
        {
            // sætter state til Main
            state = "Main";
            // Nulstiller genrebox og mediabox så det begge står som All
            genreBox.setSelectedItem(genres[0]);
            mediaBox.setSelectedItem(medias[0]);
            // Opdaterer listen af knapper ud fra de valgte filtrer
            makeMenuBoxFunctionality();
        });
        // tilføjer home knappen til menubar
        menuBar.add(homebutton);
        // Tilføjer mylist til menubar
        JButton myList = new JButton("My List");
        myList.addActionListener(e ->
        {
            state = "My List";
            Makemylist();
        });
        menuBar.add(myList);

        // tilføjer mellemrum

        menuBar.add(new JToolBar.Separator(new Dimension(50, 0)));

        //UI valg af Genre
        JLabel genreLabel = new JLabel("Genres: ");
        menuBar.add(genreLabel);

        genreBox = new JComboBox<>(genres);
        genreBox.addActionListener(e ->
                {
                    state = "Main";
                    makeMenuBoxFunctionality();
                });
        menuBar.add(genreBox);

        //Mellemrum efter genres
        menuBar.add(new JToolBar.Separator(new Dimension(15, 0)));

        //UI valg af Media
        JLabel mediaLabel = new JLabel("Media: ");
        menuBar.add(mediaLabel);


        mediaBox = new JComboBox<>(medias);
        mediaBox.addActionListener(e ->
                {
                    state = "Main";
                    makeMenuBoxFunctionality();
                });

        menuBar.add(mediaBox);

        //mellemrum efter media
        menuBar.add(new JToolBar.Separator(new Dimension(30, 0)));

        // laver focus listener på searchbar så "type here" går væk
        searchBar.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                searchBar.setText(null);
            }

            public void focusLost(FocusEvent e) {
            }
        });
        //tilføjer tekstfelt
        menuBar.add(searchBar);

        // laver søgeknap
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e ->
        {
            // Fjerner ting fra panelet så det nye kan skrives
            overview.removeAll();
            state = "Main";
            if (searchBar.getText().equals("Type here..."))
            {
                // udksriver alle medier hvis søgefeltet er tomt
                makebuttons(current);
            }
            else
            {
                if (searchBar.getText().contains("Searching for:"))
                {
                    makebuttons(current);
                }
                else
                {
                    // Returnerer de film der passer til søgetekst
                    makebuttons(mediaReg.search(searchBar.getText(),current));
                }

            }
            // Opdaterer paneltet
            overview.revalidate();
            overview.repaint();
        });
        menuBar.add(searchButton);

    }


    public static void makeMenuBoxFunctionality()
    {
        // fjerner elementer fra vinduet så der ikke bliver skrevet oven på de gamle knapper
        overview.removeAll();
        // opdaterer current listen til medier der overholder de filtrer burgeren har tilvalgt
        current = mediaReg.filter(genreBox.getSelectedItem().toString(),mediaBox.getSelectedItem().toString(),allMedias);
        // Sætter teksten i søgefeltet så brugeren ved at de søger efter de filtrer
        searchBar.setText("Searching for: "  + genreBox.getSelectedItem().toString() + " " + mediaBox.getSelectedItem().toString());
        // Laver knapperne så de passer med current listen
        makebuttons(current);
        // opdaterer vinduet
        overview.revalidate();
        overview.repaint();
    }

    // Laver panelet som skal indeholde medierne
    public static void makePanel() {
        // idé til oprettelse af knapper
        //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
        overview = new JPanel();
        // Laver en Scrollbar
        JScrollPane scrollPane = new JScrollPane(overview);
        // Sætter den til at være vertikal
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // Gør dens hastighed større
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frame.add(scrollPane);
        overview.setBackground(Color.lightGray);
    }

    // Laver de knapper/medier der skal vises og kunne trykkes på
    public static void makebuttons(List<MediaImpl> medias) {
        // definerer hvor mange medier per linje vi vil have
        int columns = 8;
        // makes gridlayout for the buttons to be displayed on
        int rows = (medias.size() / columns) + 1;
        //int columns = 7;
        int counter = 0;
        overview.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // makes buttons
        c.gridx = 0;
        c.gridy = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (counter == medias.size())
                {
                    break;
                }
                // laver billede til knap
                ImageIcon icon = new ImageIcon(medias.get(counter).getPicture());
                MediaButton button = new MediaButton(icon, medias.get(counter));
                // Sætter størrelse på knappen
                button.setPreferredSize(new Dimension(100, 200));
                // Gør så der kommer et popup når man trykker på knappen
                button.addActionListener(e ->
                        {
                            // Fjerner gammelt popup (Hvis der er et)
                            popup.dispose();
                            openMedia(button.getmedia());
                        });
                // Rykker ét felt hen på x aksen
                c.gridx++;
                overview.add(button, c);
                counter++;
            }
            //Nulstiller x aksen
            c.gridx = 0;
            // Rykker én hen ad y aksen
            c.gridy++;
        }
    }

    // Åbner et nyt vindue for det medie man har trykket på
    public static void openMedia(MediaImpl currentMedia)
    {
        // laver ny frame
        popup = new JFrame("Netflæsk"); //Laver en frame
        // Panel til at afspille
        JPanel popuppanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Plakat af filmen
        JLabel img = new JLabel();
        //Gør så vores popup kan lukkes
        popup.setDefaultCloseOperation(popup.DISPOSE_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        popup.setResizable(false);
        popup.setLocation(MouseInfo.getPointerInfo().getLocation());
        JButton playButton = new JButton("Play");
        // JButton currentMedia
        playButton.addActionListener(e ->
        {
            // Skal erstattes med playfunktionen.
            System.out.println("PlaycurrentMedia");
        });
        // Knap til at fjerne/tilføje film til ens liste
        makeAddRemoveList(c,popuppanel,currentMedia);

        img.setIcon(new ImageIcon(currentMedia.getPicture()));
        c.gridx = 0;
        c.gridy = 0;
        popuppanel.add(img,c);
        c.gridy = 1;
        popuppanel.add(playButton,c);

        //frame.getContentPane(panel);
        if(currentMedia.getMediaType().equals("Series"))
        {
            makeSeriesComboboxes(c,popuppanel,currentMedia);
        }
        else if(currentMedia.getMediaType().equals("Movie"))
        {

        }
        popup.getContentPane().add(popuppanel);
        popup.setSize(300, 500);
        popup.setVisible(true); //frame bliver synlig
    }

    private static void Makemylist() {

        // clearer favorites listen så medier der ikke er favorit længere bliver fjernet
        favorites.removeAll(allMedias);
        for (MediaImpl media: allMedias)
        {
            // If the media has the status favorite and the favorite list does not allready contain it add it
            if (media.isFavorite())
            {
                favorites.add(media);
            }
        }
        // opdaterer overview
        overview.removeAll();
        makebuttons(favorites);
        overview.revalidate();
        overview.repaint();
    }

    private static void makeAddRemoveList(GridBagConstraints c,JPanel popuppanel,MediaImpl currentMedia)
    {
        // sætter constraints til hvor knappen skal være
        c.gridx = 0;
        c.gridy = 2;
        // Laver en button
        JButton AddRemoveList = new JButton("");
        if(currentMedia.isFavorite()) {

            AddRemoveList.setText("Remove from my list");
        }
        else {
            AddRemoveList.setText("Add to my list");
        }
        // Tilføjer eller fjerner til favorit liste
        AddRemoveList.addActionListener(e ->
        {
            popuppanel.remove(AddRemoveList);
            if (currentMedia.isFavorite())
            {
                AddRemoveList.setText("Add to my list");
                mediaReg.removeFromFavorites(currentMedia);
            }
            else
            {
                AddRemoveList.setText("Remove from my list");
                mediaReg.addToFavorites(currentMedia);
            }
            // Opdaterer knapperne hvis brugeren er inde på listen mens de fjerner mediet
            if (state.equals("My List"))
            {
                Makemylist();
            }
            popuppanel.revalidate();
            popuppanel.repaint();
            c.gridx = 0;
            c.gridy = 2;
            popuppanel.add(AddRemoveList,c);
        });
        popuppanel.add(AddRemoveList,c);
    }

    // Gør episodeBox global (ellers kan den ikke bruges i ActionListener
    private static JComboBox episodeBox = new JComboBox<>();
    private static void makeSeriesComboboxes(GridBagConstraints c,JPanel popuppanel,MediaImpl currentMedia)
    {
        // season Label og Box
        Series currentSeries = (Series)currentMedia;
        JLabel seasonLabel = new JLabel("Season: ");
        //currentSeries.getSeasons();
        c.gridx = 0;
        c.gridy = 3;
        popuppanel.add(seasonLabel, c);
        String[] seasons = new String[currentSeries.getSeasons()];
        for(int i = 0; i < seasons.length; i++)
        {
            seasons[i] = Integer.toString(i+1);
        }
        JComboBox seasonBox = new JComboBox<>(seasons);

        //episode Label og Box
        JLabel episodeLabel = new JLabel("Episode: ");
        c.gridx = 0;
        c.gridy = 4;
        popuppanel.add(episodeLabel, c);


        String[] episodes = makeEpisodes(currentSeries,seasonBox.getSelectedIndex());
        episodeBox = new JComboBox<>(episodes);
        c.gridx = 1;
        c.gridy = 4;
        popuppanel.add(episodeBox, c);
        seasonBox.addActionListener(e ->
        {
            popuppanel.remove(episodeBox);
            episodeBox.removeAllItems();
            episodeBox = new JComboBox<>(makeEpisodes(currentSeries,seasonBox.getSelectedIndex()));
            c.gridx = 1;
            c.gridy = 4;
            popuppanel.add(episodeBox,c);
            popuppanel.revalidate();
            popuppanel.repaint();
        });
        c.gridx = 1;
        c.gridy = 3;
        popuppanel.add(seasonBox, c);

        c.gridx = 0;
        c.gridy = 3;
        popuppanel.add(seasonLabel, c);
    }

    private static String[] makeEpisodes(Series currentSeries, int season)
    {
        String[] episodeseasons = currentSeries.getEpisodes();
        int episodeammount = Integer.parseInt(episodeseasons[season]);
        String[] episodes =  new String[episodeammount];
        //currentSeries.getEpisodes()
        for(int i = 0; i < episodeammount; i++)
        {
            episodes[i] = i+1 + "";
        }
        return episodes;
    }
}