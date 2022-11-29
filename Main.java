import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Main {
    private static JFrame frame;
    private static JPanel overview;

    public static void main(String[] args)
    {
        makeFrame();
    }

    private static void makeFrame() {
        frame = new JFrame("Netflæsk"); //Laver en frame
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        frame.setResizable(false);
        //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.

        makeMenuBar();
        makePanel();
        openMediaUI();
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

    private static void makePanel() {
        overview = new JPanel();
        overview.setLayout(new GridLayout(5, 7, 4, 4));
        /*int mediaElements = loadmovies().size() + loadseries().size();
        for (int i = 0; i < mediaElements; i++)
        {
        }*/
        overview.setBackground(Color.lightGray);

        frame.add(overview);
    }

    public static void openMediaUI(){
        JPanel openMedia = new JPanel();
        //Til når play(), når man har klikket ind på billederne
        MediaImpl m = new MediaImpl("","",new ArrayList<>(),0,false);
        JButton playButton = new JButton("play");
        playButton.addActionListener(e -> m.play("Afspiller: " + m.title));
        
        //openMedia skal tilgås når et element i overview (grid) klikkes
    }
}