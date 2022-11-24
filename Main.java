import javax.swing.*;
//import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame(); //Laver en frame
        frame.setVisible(true); //frame bliver synlig
        frame.setTitle("Netflæsk"); //Navnet på applikation
        //Gør så vores program rent faktisk lukker.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Den har en standard size, ikke redigerbar af client
        frame.setResizable(false);

        //frame.getContentPane().setBackground(Color.white); //Kan også laves med 'new Color'.

        //Laver MenuBar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu myList = new JMenu("My List");
        menuBar.add(myList);
        frame.setSize(800,800); //x og y
    }
}
