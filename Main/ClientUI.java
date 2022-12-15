package Main;

import Domain.MediaImpl;
import Domain.Mediaregistryimpl;
import Domain.Series;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ClientUI {
    private static JFrame frame;
    private static JPanel overview;
    private static JFrame popup = new JFrame("Netflæsk"); //Laver en frame
    private static JComboBox<String> genreBox;
    private static JComboBox<String> mediaBox;

    private static JTextField searchBar = new JTextField("Searching for: All movies and series.");
    private static List<MediaImpl> allMedias;

    private static List<MediaImpl> current;
    private static Mediaregistryimpl mediaReg;

    private static List<MediaImpl> favorites = new ArrayList<>();

    private static JComboBox<String> seasonBox ;

    private static String state = "Main";

    public static void main(String[] args) {
        mediaReg = new Mediaregistryimpl();
        // Samler medier i en liste

        try {
            allMedias = mediaReg.initializeAllMedia();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        current = new ArrayList<>(allMedias);
        // tjekker all medie objekter igennem og tilføjer dem hvis de er sat som favoritter på den lokale favoritliste
        for (MediaImpl media: allMedias)
        {
            // If the media has the status favorite and the favorite list does not allready contain it add it
            if (media.isFavorite())
            {
                favorites.add(media);
            }
        }
        makeFrame();
    }

    private static void makeFrame() {

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
        frame.setSize(1250, 800);
        frame.setVisible(true); //frame bliver synlig
    }
    private static void makeMenuBar() {
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

    private static void makeMenuBoxFunctionality()
    {
        // fjerner elementer fra vinduet så der ikke bliver skrevet oven på de gamle knapper
        overview.removeAll();
        // opdaterer current listen til medier der overholder de filtrer burgeren har tilvalgt
        current = mediaReg.filter(genreBox.getSelectedItem().toString(),mediaBox.getSelectedItem().toString(),allMedias);
        // Sætter teksten i søgefeltet så brugeren ved at de søger efter de filtrer
        if (genreBox.getSelectedItem().toString().equals("All") && mediaBox.getSelectedItem().toString().equals("All")) {
            searchBar.setText("Searching for: All movies and series.");
        }
        else {
            searchBar.setText("Searching for: " + genreBox.getSelectedItem().toString() + " " + mediaBox.getSelectedItem().toString());
        }
        // Laver knapperne så de passer med current listen
        makebuttons(current);
        // opdaterer vinduet
        overview.revalidate();
        overview.repaint();
    }

    // Laver panelet som skal indeholde medierne
    private static void makePanel() {
        // Idé til oprettelse af knapper
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
    private static void makebuttons(List<MediaImpl> medias) {
        // definerer hvor mange medier per linje vi vil have
        int columns = 8;
        // makes gridlayout for the buttons to be displayed on
        int rows = (medias.size() / columns) + 1;
        // Holder styr på hvilket medie index vi er på
        int counter = 0;
        overview.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // makes buttons
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
                button.setPreferredSize(new Dimension(140, 209));
                // Gør så der kommer et popup når man trykker på knappen
                button.addActionListener(e ->
                        {
                            // Fjerner gammelt popup (Hvis der er et)
                            popup.dispose();
                            openMedia(button.getMedia());
                        });
                overview.add(button, c);
                counter++;
            }
            //Nulstiller x aksen
            //c.gridx = 0;
            // Rykker én hen ad y aksen
            c.gridy++;
        }
    }

    // Åbner et nyt vindue for det medie man har trykket på
    private static void openMedia(MediaImpl currentMedia)
    {
        // laver ny frame
        popup = new JFrame("Netflæsk"); //Laver en frame
        // Panel til at afspille
        JPanel popuppanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridheight = 8;
        c.gridwidth = 2;
        // Plakat af filmen
        JLabel img = new JLabel();

        // Titel på filmen
        JLabel title = new JLabel(currentMedia.getTitle());
        JLabel year = new JLabel(currentMedia.getYear());
        JLabel score = new JLabel("Rating: " + currentMedia.getScore().toString());
        //Gør så vores popup kan lukkes
        popup.setDefaultCloseOperation(popup.DISPOSE_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        popup.setResizable(false);
        popup.setLocation(MouseInfo.getPointerInfo().getLocation());

        JButton playButton = new JButton("Play");
        // JButton currentMedia
        playButton.addActionListener(e ->
                playVideo(currentMedia));
        // Knap til at fjerne/tilføje film til ens liste
        makeAddRemoveList(c,popuppanel,currentMedia);

        img.setIcon(new ImageIcon(currentMedia.getPicture()));
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = c.LINE_START;
        c.fill = c.HORIZONTAL;
        c.weightx = 1.0;
        popuppanel.add(img,c);
        c.fill = 0;
        c.weighty = 5.0;
        c.anchor = c.CENTER;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 0;
        popuppanel.add(title,c);
        c.gridy = 1;
        popuppanel.add(year,c);
        c.gridy = 2;
        popuppanel.add(score,c);
        c.gridy = 4;
        popuppanel.add(playButton,c);
        c.weighty = 0;

        //frame.getContentPane(panel);
        if(currentMedia.getMediaType().equals("Series"))
        {
            makeSeriesComboboxes(c,popuppanel,currentMedia);
        }
        popup.getContentPane().add(popuppanel);
        popup.setSize(600, 240);
        popup.setVisible(true); //frame bliver synlig
    }

    // Gør episodeBox global (ellers kan den ikke bruges i ActionListener)
    private static JComboBox episodeBox = new JComboBox<>();
    private static void makeSeriesComboboxes(GridBagConstraints c,JPanel popuppanel,MediaImpl currentMedia)
    {
        // season Label og Box
        Series currentSeries = (Series)currentMedia;
        JLabel seasonLabel = new JLabel("Season: ");
        //currentSeries.getSeasons();
        c.anchor = c.LINE_END;
        c.gridx = 1;
        c.gridy = 5;
        popuppanel.add(seasonLabel, c);
        String[] seasons = new String[currentSeries.getSeasons()];
        for(int i = 0; i < seasons.length; i++)
        {
            seasons[i] = Integer.toString(i+1);
        }
        seasonBox = new JComboBox<>(seasons);

        //episode Label og Box
        JLabel episodeLabel = new JLabel("Episode: ");
        c.gridx = 1;
        c.gridy = 6;
        popuppanel.add(episodeLabel, c);
        c.weightx = 0.25;
        c.ipadx = 15;
        String[] episodes = makeEpisodes(currentSeries,seasonBox.getSelectedIndex());
        episodeBox = new JComboBox<>(episodes);
        c.gridx = 2;
        popuppanel.add(episodeBox, c);
        seasonBox.addActionListener(e ->
        {
            popuppanel.remove(episodeBox);
            episodeBox.removeAllItems();
            episodeBox = new JComboBox<>(makeEpisodes(currentSeries,seasonBox.getSelectedIndex()));
            c.gridx = 2;
            c.gridy = 6;
            popuppanel.add(episodeBox,c);
            popuppanel.revalidate();
            popuppanel.repaint();
        });
        c.gridx = 2;
        c.gridy = 5;
        popuppanel.add(seasonBox, c);
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

    private static void makeAddRemoveList(GridBagConstraints c,JPanel popuppanel,MediaImpl currentMedia)
    {
        // sætter constraints til hvor knappen skal være
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = c.CENTER;
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
                favorites.remove(currentMedia);
            }
            else
            {
                AddRemoveList.setText("Remove from my list");
                mediaReg.addToFavorites(currentMedia);
                favorites.add(currentMedia);
            }
            // Opdaterer knapperne hvis brugeren er inde på listen mens de fjerner mediet
            if (state.equals("My List"))
            {
                Makemylist();
            }
            popuppanel.revalidate();
            popuppanel.repaint();
            c.gridx = 1;
            c.gridy = 5;
            c.anchor = c.CENTER;
            popuppanel.add(AddRemoveList,c);
        });
        popuppanel.add(AddRemoveList,c);
    }
    private static void Makemylist() {
        // opdaterer overview
        overview.removeAll();
        makebuttons(favorites);
        overview.revalidate();
        overview.repaint();
    }

    private static void playVideo(MediaImpl currentMedia)
    {
        //Playfunktion Til Series
        if(currentMedia.getMediaType().equals("Series")){
            int getEpisode = episodeBox.getSelectedIndex() + 1;
            int getSeason = seasonBox.getSelectedIndex() + 1;

            JOptionPane.showMessageDialog(null,"Afspiller: " + currentMedia.getTitle() + " Sæson: " + getSeason  + " Episode: " +
                    getEpisode, "Afspilning",JOptionPane.INFORMATION_MESSAGE);

        } else {
            //Playfunktion Til movies
            JOptionPane.showMessageDialog(null,"Afspiller: " + currentMedia.getTitle(),
                    "Afspilning",JOptionPane.INFORMATION_MESSAGE);
        }

        System.out.println("PlaycurrentMedia");
    }
}