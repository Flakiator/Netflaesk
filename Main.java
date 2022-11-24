import javax.swing.*;
import java.awt.*;
/*import java.awt.event.ActionListener;
import java.awt.*;*/

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
        menuBar.add(myList);

        // tilføjer mellemrum
        menuBar.add(new JToolBar.Separator(new Dimension( 520,0)));

        // laver tekstfelt
        JTextField searchBar = new JTextField();
        //tilføjer tekstfelt
        menuBar.add(searchBar);

        // laver søgeknap
        JButton searchButton = new JButton("Search");
        menuBar.add(searchButton);


    }

}
