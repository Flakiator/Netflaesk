import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static JFrame frame;
    private static JPanel overview;

    private static JScrollPane scrollPane;

    public static void main(String[] args)
    {

        makeFrame();
    }

    public static void makeFrame() {

            frame = new JFrame("Netflæsk"); //Laver en frame
            //Gør så vores program rent faktisk lukker.
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //Den har en standard size, ikke redigerbar af client
            frame.setResizable(true);

            //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.


            makeMenuBar();
            makePanel();
            frame.pack();
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

    public static void makePanel () {
            overview = new JPanel();
            //Scrollable, som ikke rigtigt virker
            scrollPane = new JScrollPane(overview);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            int rows = 0;
            int columns = 0;
            // idé til oprettelse af knapper
            Mediaregistryimpl data = new Mediaregistryimpl();
            List<Movie> movies = data.initializeMovie();
            List<Series> series = data.initializeSeries();
            List<MediaImpl> medias = new ArrayList<>();
            medias.addAll(movies);
            medias.addAll(series);
            //Kører for-loop som adder button for hver row og coloum. Kan vi hente rows, cols værdier?
            makebuttons(medias);
            frame.add(scrollPane);
            overview.setBackground(Color.lightGray);

        }
    public static void makebuttons(List<MediaImpl> medias)
    {
        double size = medias.size() / 7;
        int rows = (int)Math.ceil(size);
        int columns = 7;
        int counter = 0;
        overview.setLayout(new GridLayout(rows, columns, 2, 1));
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                ImageIcon icon = new ImageIcon(medias.get(counter).getPicture());
                JButton button = new JButton(icon);
                button.setPreferredSize(new Dimension(45,200));
                overview.add(button);
                counter++;
            }
        }
    }
        }