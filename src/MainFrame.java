import oracle.jvm.hotspot.jfr.JFR;

import javax.swing.*;
import java.awt.*;

/**
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
//        setLayout(new BorderLayout());
//        add(BorderLayout.SOUTH, KeyPadPanel.getKeyPadPanel());
       //   add(BorderLayout.CENTER, new BuildingLayoutPanel());
        setLayout(new GridLayout(0, 1));
      //  add(new BuildingLayoutPanel());
       // add(new MenuPanel());
        add(DisplayPanel.getDisplayPanel());
        add(KeyPadPanel.getKeyPadPanel());
    }
}
