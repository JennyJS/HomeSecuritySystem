package main;

import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/5/17.
 */
public class PopupFrame extends JFrame {
    private JLabel imageLabel;
    private JLabel label;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JPanel topImagePanel;
    private JPanel bottomInputPanel;
    public PopupFrame(String fileName){
        //initialize
        initializeComponents(fileName);
        addComponentsToPanel();
        setLayout(new GridLayout(0, 1));
        add(topImagePanel);
        add(bottomInputPanel);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void initializeComponents(String fileName){
        ImageIcon icon = new ImageIcon(fileName);
        imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        label = new JLabel(" Enter password to disarm system: ");
        label.setFont(new Font("Serif", Font.ITALIC, 20));
        passwordField = new JPasswordField();
        enterButton = new JButton("Enter");
        topImagePanel = new JPanel();
        bottomInputPanel = new JPanel();
    }

    private void addComponentsToPanel(){
        topImagePanel.add(imageLabel);
        bottomInputPanel.setLayout(new GridLayout(0, 1, 8, 10));
        bottomInputPanel.add(label);
        bottomInputPanel.add(passwordField);
        bottomInputPanel.add(enterButton);
    }
}
