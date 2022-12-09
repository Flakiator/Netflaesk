import org.w3c.dom.events.Event;

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

    private static JTextField searchBar = new JTextField("Type here...");
    private static List<Movie> allMovies;
    private static List<Series> allSeries;
    private static List<MediaImpl> allMedias;

    private static List<MediaImpl> current;
    private static Mediaregistryimpl mediaReg;

    public static void main(String[] args) {
        mediaReg = new Mediaregistryimpl();
        allMovies = mediaReg.initializeMovie();
        allSeries = mediaReg.initializeSeries();
        allMedias = new ArrayList<>(allMovies);
        allMedias.addAll(allSeries);
        current = new ArrayList<>(allMedias);
        makeFrame();
    }

    public static void makeFrame() {

        frame = new JFrame("Netfl�sk"); //Laver en frame
        //G�r s� vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        frame.setResizable(true);

        //frame.getContentPane().setBackground(Color.white); //Kan ogs� laves med 'new Color'.
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
        // Tilf�jer mylist til menubar
        JButton myList = new JButton("My List");
        myList.addActionListener(e ->
        {
            System.out.println("MY LIST!!!");
            // Kald ny JFrame evt. gennem en ny metode under makeMenuBar()
        });
        menuBar.add(myList);

        // tilf�jer mellemrum

        menuBar.add(new JToolBar.Separator(new Dimension(50, 0)));

        //UI valg af Genre
        JLabel genreLabel = new JLabel("Genres: ");
        menuBar.add(genreLabel);

        String[] genres = {"All", "Action"};

        // Husk at tilf�j <String>
        genreBox = new JComboBox<>(genres);
        genreBox.addActionListener(e ->
        {
            makeMenuBoxFunctionality();
        });
        menuBar.add(genreBox);

        //Mellemrum efter genres
        menuBar.add(new JToolBar.Separator(new Dimension(15, 0)));

        //UI valg af Media
        JLabel mediaLabel = new JLabel("Media: ");
        menuBar.add(mediaLabel);

        String[] medias = {"All", "Movies", "Series"};
        mediaBox = new JComboBox<>(medias);
        mediaBox.addActionListener(e ->
        {
            makeMenuBoxFunctionality();
        });
        menuBar.add(mediaBox);

        //mellemrum efter media
        menuBar.add(new JToolBar.Separator(new Dimension(30, 0)));


        // laver focus listener p� searchbar s� "type here" g�r v�k
        searchBar.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent e) {
                searchBar.setText(null);
            }

            public void focusLost(FocusEvent e) {
            }
        });
        //tilf�jer tekstfelt
        menuBar.add(searchBar);

        // laver s�geknap
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e ->
        {
            // Fjerner ting fra panelet s� det nye kan skrives
            overview.removeAll();
            if (searchBar.getText().equals("Type here..."))
            {
                // udksriver alle medier hvis s�gefeltet er tomt
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
                    // Returnerer de film der passer til s�getekst
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
        overview.removeAll();
        current = mediaReg.filter(genreBox.getSelectedItem().toString(),mediaBox.getSelectedItem().toString(),allMedias);
        searchBar.setText("Searching for: "  + genreBox.getSelectedItem().toString() + " " + mediaBox.getSelectedItem().toString());
        makebuttons(current);
        overview.revalidate();
        overview.repaint();
    }
    public static void makePanel() {
        // id� til oprettelse af knapper
        //K�rer for-loop som adder button for hver row og coloum. Kan vi hente rows, cols v�rdier?
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