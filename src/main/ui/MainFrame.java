package main.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Main frame.
 *
 * Created by manhongren on 5/31/17.
 */
public class MainFrame extends JFrame{

    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle("SoSafe");
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }
    public MainFrame(){
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, DisplayPanel.getDisplayPanel());
        add(BorderLayout.SOUTH, KeyPadPanel.getKeyPadPanel());
    }
}
