import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JFrame frame;
    private static JPanel overview;

    private static JScrollPane scrollPane;

    private static JComboBox<String> genreBox;
    private static JComboBox<String> mediaBox;

    private static List<Movie> AllMovies;
    private static List<Series> AllSeries;

    public static void main(String[] args) {
        Mediaregistryimpl SingletonMediaregisty = new Mediaregistryimpl();
        AllMovies = SingletonMediaregisty.initializeMovie();
        AllSeries = SingletonMediaregisty.initializeSeries();
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
            System.out.println("MY LIST!!!");
            // Kald ny JFrame evt. gennem en ny metode under makeMenuBar()
        });
        menuBar.add(myList);

        // tilføjer mellemrum

        menuBar.add(new JToolBar.Separator(new Dimension(50, 0)));


        //UI valg af Genre
        JLabel genreLabel = new JLabel("Genres: ");
        menuBar.add(genreLabel);


        String[] genres = {"All", "Kategorierne..."};

        // Husk at tilføj <String>
        genreBox = new JComboBox<>(genres);
        menuBar.add(genreBox);

        //Mellemrum efter genres
        menuBar.add(new JToolBar.Separator(new Dimension(15, 0)));

        //UI valg af Media
        JLabel mediaLabel = new JLabel("Media: ");
        menuBar.add(mediaLabel);

        String[] medias = {"All", "Movies", "Series"};
        mediaBox = new JComboBox<>(medias);
        menuBar.add(mediaBox);

        //mellemrum efter media
        menuBar.add(new JToolBar.Separator(new Dimension(30, 0)));

        // laver tekstfelt
        JTextField searchBar = new JTextField("Type here...");
        // laver focus listener så "type here" går væk
        searchBar.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                searchBar.setText(null);
            }

            public void focusLost(FocusEvent e) {
                searchBar.setText("Type here...");
            }
        });
        //tilføjer tekstfelt
        menuBar.add(searchBar);

        // laver søgeknap
        JButton searchButton = new JButton("Search");
        menuBar.add(searchButton);

    }

    public static void makePanel() {
        int rows = 0;
        int columns = 0;
        // idé til oprettelse af knapper
        List<MediaImpl> medias = new ArrayList<>();
        medias.addAll(AllMovies);
        medias.addAll(AllSeries);
        //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
        makebuttons(medias);
        frame.add(scrollPane);
        overview.setBackground(Color.lightGray);

    }

    public static void makebuttons(List<MediaImpl> medias) {
        overview = new JPanel();
        List<JButton> buttons = new ArrayList<>();
        //Scrollbar
        scrollPane = new JScrollPane(overview);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        // makes gridlayout for the buttons to be displayed on
        double size = medias.size() / 7;
        int rows = (int) Math.ceil(size);
        int columns = 7;
        int counter = 0;
        overview.setLayout(new GridLayout(rows, columns, 2, 1));
        // makes buttons
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ImageIcon icon = new ImageIcon(medias.get(counter).getPicture());
                JButton button = new JButton(icon);
                button.setActionCommand(medias.get(counter).getTitle());
                button.setPreferredSize(new Dimension(45, 200));
                button.addActionListener(e ->
                {
                    System.out.println(button.getActionCommand());
                });
                buttons.add(button);
                overview.add(button);
                counter++;
            }
        }
    }
}