import javax.swing.*;
import java.awt.event.ActionListener;
//import java.awt.*;

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
        JMenu myList = new JMenu("My List");
        menuBar.add(myList);

        JTextField searchBar = new JTextField();
        searchBar.setBounds(680,0,50,20);
        menuBar.add(searchBar);

        JButton seachButton = new JButton("Search");
        seachButton.setBounds(750,0,50,20);
        menuBar.add(seachButton);


    }

}
