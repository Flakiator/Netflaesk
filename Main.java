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
        //Den har en standard size, ikke redigerbar af client
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
            for (MediaImpl media: allMedias)
            {
                // If the media has the status favorite and the favorite list does not allready contain it add it
                if (media.isFavorite() && !favorites.contains(media))
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
                            mediaReg.addToFavorites(button.getActionCommand(),allMedias);
                        });

                overview.add(button);
                counter++;
            }
        }
    }
}