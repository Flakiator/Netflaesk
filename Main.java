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
    private static List<MediaImpl> allmedias;

    private static List<MediaImpl> current;
    private static Mediaregistryimpl SingletonMediaregisty;

    public static void main(String[] args) {
        SingletonMediaregisty = new Mediaregistryimpl();
        AllMovies = SingletonMediaregisty.initializeMovie();
        AllSeries = SingletonMediaregisty.initializeSeries();
        allmedias = new ArrayList<>(AllMovies);
        allmedias.addAll(AllSeries);
        current = new ArrayList<>(allmedias);
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
                if (searchBar.getText().equals(""))
                {
                    searchBar.setText("Type here...");
                }
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
                makebuttons(allmedias);
            }
            else
            {
                // Returnerer de film der passer til søgetekst
                makebuttons(SingletonMediaregisty.search(searchBar.getText(),current));
            }
            // Opdaterer paneltet
            overview.revalidate();
            overview.repaint();
        });
        menuBar.add(searchButton);

    }

    public static void makePanel() {
        // idé til oprettelse af knapper
        //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
        overview = new JPanel();
        //Scrollbar
        scrollPane = new JScrollPane(overview);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        frame.add(scrollPane);
        overview.setBackground(Color.lightGray);

    }

    public static void makebuttons(List<MediaImpl> medias) {

        // makes gridlayout for the buttons to be displayed on
        int rows = (int) (medias.size() / 7) + 1;
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
                });
                overview.add(button);
                counter++;
            }
        }
    }
}