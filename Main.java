import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    private static JFrame frame;
    private static JPanel overview;

    private static JScrollPane scrollPane;

    public static void main(String[] args) {
        makeFrame();
    }

    public static void makeFrame() {

            frame = new JFrame("Netflæsk"); //Laver en frame
            //Gør så vores program rent faktisk lukker.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Den har en standard size, ikke redigerbar af client
            frame.setResizable(false);
            frame.setResizable(true);

            //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.

            makePanel();
            makeMenuBar();
            frame.setSize(800, 800); //x og y
            frame.setVisible(true); //frame bliver synlig
        }

        private static void makeMenuBar() {
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
            JComboBox<String> genreBox = new JComboBox<>(genres);
            menuBar.add(genreBox);

            //Mellemrum efter genres
            menuBar.add(new JToolBar.Separator(new Dimension(15, 0)));

            //UI valg af Media
            JLabel mediaLabel = new JLabel("Media: ");
            menuBar.add(mediaLabel);

            String[] medias = {"All", "Movies", "Series"};
            JComboBox<String> mediaBox = new JComboBox<>(medias);
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

        private static void makePanel () {
            overview = new JPanel();

            //Scrollable, som ikke rigtigt virker
            scrollPane = new JScrollPane(overview);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            overview.setLayout(new GridLayout(3, 4, 2, 1));
        /*int mediaElements = loadmovies().size() + loadseries().size();
        for (int i = 0; i < mediaElements; i++)
        {
        }*/
            //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
            for (int k = 0; k < 3; k++) {
                for (int j = 0; j < 4; j++) {
                    overview.add(new JButton(""));
                }
            }

            frame.add(scrollPane);
            overview.setBackground(Color.lightGray);
            frame.add(overview);
        }

        }