import javax.swing.*;
import java.awt.*;

public class Main
{
    private static JFrame frame;
    public static void main(String[] args)
    {
        makeFrame();
    }
    private static void makeFrame()
    {
        frame = new JFrame("Netflæsk"); //Laver en frame
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        frame.setResizable(false);
        //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.
        frame.setLayout(null);

        makeMenuBar();
        frame.setSize(800,800); //x og y
        frame.setVisible(true); //frame bliver synlig
    }

    private static void makeMenuBar()
    {
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
        menuBar.add(new JToolBar.Separator(new Dimension( 50,0)));

        //UI valg af Genre
        JLabel genreLabel = new JLabel();
        genreLabel.setText("Genres: ");
        menuBar.add(genreLabel);

        String[] genres = {"All","Kategorierne..."};
        JComboBox genreBox = new JComboBox(genres);
        menuBar.add(genreBox);

        //Metode til at lave mellemrum mellem componenter
        JMenuItem menu = new JMenuItem();
        menuBar.add(menu);
        menu.setMargin(new Insets(10,10,10,10));

        //UI valg af Media
        JLabel mediaLabel = new JLabel();
        mediaLabel.setText("Media: ");
        menuBar.add(mediaLabel);

        String[] medias = {"All","Movies","Series"};
        JComboBox mediaBox = new JComboBox(medias);
        menuBar.add(mediaBox);

        //Metode til at lave mellemrum mellem componenter
        JMenuItem menu1 = new JMenuItem();
        menuBar.add(menu1);
        menu1.setMargin(new Insets(10, 10, 10, 10));
        JMenuItem menu2 = new JMenuItem();
        menuBar.add(menu2);
        menu2.setMargin(new Insets(10, 10, 10, 10));

        // laver tekstfelt
        JTextField searchBar = new JTextField();
        //tilføjer tekstfelt
        menuBar.add(searchBar);
        searchBar.setText("Type here...");

        // laver søgeknap
        JButton searchButton = new JButton("Search");
        menuBar.add(searchButton);

    }

}
