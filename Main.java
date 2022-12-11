import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JFrame frame;
    private static JPanel overview;

    private static JComboBox<String> genreBox;
    private static JComboBox<String> mediaBox;

    private static JTextField searchBar = new JTextField("Type here...");
    private static List<MediaImpl> allMedias;

    private static List<MediaImpl> current;
    private static Mediaregistryimpl mediaReg;
    private static List<MediaImpl> favorites = new ArrayList<>();

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
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har IKKE en standard size, ER redigerbar af client
        frame.setResizable(true);

        //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.
        makePanel();
        makebuttons(current);
        makeMenuBar();
        frame.setSize(800, 800);
        frame.setVisible(true); //frame bliver synlig
    }
    public static void makeMenuBar() {
        //Laver MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        // Tilføjer mylist til menubar
        JButton myList = new JButton("My List");
        myList.addActionListener(e ->
        {
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
            overview.removeAll();
            makebuttons(favorites);
            overview.revalidate();
            overview.repaint();
        });
        menuBar.add(myList);

        // tilføjer mellemrum

        menuBar.add(new JToolBar.Separator(new Dimension(50, 0)));

        //UI valg af Genre
        JLabel genreLabel = new JLabel("Genres: ");
        menuBar.add(genreLabel);

        String[] genres = mediaReg.getAllGenre(allMedias);

        genreBox = new JComboBox<>(genres);
        genreBox.addActionListener(e ->
                makeMenuBoxFunctionality());
        menuBar.add(genreBox);

        //Mellemrum efter genres
        menuBar.add(new JToolBar.Separator(new Dimension(15, 0)));

        //UI valg af Media
        JLabel mediaLabel = new JLabel("Media: ");
        menuBar.add(mediaLabel);

        String[] medias = {"All", "Movies", "Series"};
        mediaBox = new JComboBox<>(medias);
        mediaBox.addActionListener(e ->
                makeMenuBoxFunctionality());
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
    public static void makePanel() {
        // idé til oprettelse af knapper
        //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
        overview = new JPanel();
        //Scrollbar
        JScrollPane scrollPane = new JScrollPane(overview);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frame.add(scrollPane);
        overview.setBackground(Color.lightGray);

    }

    public static void makebuttons(List<MediaImpl> medias) {

        // makes gridlayout for the buttons to be displayed on
        int rows = (medias.size() / 7) + 1;
        int columns = 7;
        int counter = 0;
        overview.setLayout(new GridLayout(rows, columns, 2, 1));
        // makes buttons
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (counter == medias.size())
                {
                    break;
                }
                ImageIcon icon = new ImageIcon(medias.get(counter).getPicture());
                JButton button = new JButton(icon);
                button.setActionCommand(medias.get(counter).getTitle());
                button.setPreferredSize(new Dimension(45, 200));
                button.addActionListener(e ->
                        {
                            System.out.println(button.getActionCommand());
                            mediaReg.addToFavorites(button.getActionCommand(),current);
                            openMedia();
                        });

                overview.add(button);
                counter++;
            }
        }
    }
    public static void openMedia(/*MediaImpl currentMedia*/)
    {
        Media currentMedia = allMedias.get(103); // Denne linje skal slettes, og den skal bare være med som parameter i stedet.
        frame = new JFrame("Netflæsk"); //Laver en frame
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        frame.setResizable(false);
        frame.setSize(300, 500);
        frame.setLocation(MouseInfo.getPointerInfo().getLocation());
        JButton playButton = new JButton(currentMedia.getTitle());

        if(currentMedia.isFavorite()) {
            JButton AddRemoveList = new JButton("Add to my list");
        }
        else {
            JButton AddRemoveList = new JButton("Revome from my list");
        }
        JPanel buttonpanel = new JPanel();
        JPanel imagePanel = new JPanel();
        if(currentMedia instanceof Series)
        {
            // JButton currentMedia
            playButton.addActionListener(e ->
            {
                // Skal erstattes med playfunktionen.
                System.out.println("PlaycurrentMedia");
            });
            //frame.add(playButton);

            ImageIcon icon = new ImageIcon(currentMedia.getPicture());
            J
            imagePanel.add(icon);
            frame.add(new JLabel((new ImageIcon(currentMedia.getPicture()))));
            //frame.add(currentMedia.getPicture());
            //panel.add(currentMedia.getPicture());
            buttonpanel.add(playButton);

            frame.add(buttonpanel);
            //frame.getContentPane(panel);
        }
        else if(currentMedia instanceof Movie)
        {
            // JButton currentMedia
            playButton.addActionListener(e ->
            {
                // Skal erstattes med playfunktionen.
                System.out.println("PlaycurrentMedia");
            });
        }
        frame.setVisible(true); //frame bliver synlig
        /*
        JButton Play = new JButton("My List");
        Play.addActionListener(e ->
        {
            // Skal erstattes med playfunktionen.
            System.out.println("PlaycurrentMedia");
        });
         */
    }
}