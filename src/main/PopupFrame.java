package main;

import fileManagers.PasswordFileManager;
import menuPanels.ActiveTextField;
import sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by manhongren on 6/5/17.
 */
public class PopupFrame extends JFrame {
    private static final Font LABEL_FONT = new Font("GungSeo", Font.PLAIN, 20);
    private JLabel imageLabel;
    private JLabel label;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JPanel topImagePanel;
    private JPanel bottomInputPanel;
    private JLabel callingPhoneNumberLabel;
    private String phoneNumber;

    public PopupFrame(String fileName){
        phoneNumber = readFromPhoneNUmberFile();
        //initialize
        initializeComponents(fileName);
        addComponentsToPanel();
        setLayout(new GridLayout(0, 1));
        //register action listeners
        registerActionListeners();
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
        callingPhoneNumberLabel = new JLabel();
        callingPhoneNumberLabel.setText("Calling Phone Number: " + phoneNumber + "...");
        callingPhoneNumberLabel.setFont(LABEL_FONT);
        label = new JLabel(" Enter password to disarm system: ");
        label.setFont(LABEL_FONT);
        passwordField = new JPasswordField();
        enterButton = new JButton("Disarm");
        topImagePanel = new JPanel();
        bottomInputPanel = new JPanel();
    }

    private void addComponentsToPanel(){
        topImagePanel.setLayout(new GridLayout(0, 1));
        topImagePanel.setPreferredSize(new Dimension(300, 350));
        topImagePanel.add(imageLabel);
        topImagePanel.add(callingPhoneNumberLabel);
        bottomInputPanel.setLayout(new GridLayout(0, 1, 8, 10));
        bottomInputPanel.add(label);
        bottomInputPanel.add(passwordField);
        bottomInputPanel.add(enterButton);
    }

    private void registerActionListeners(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actualPassword = PasswordFileManager.getFileManager().readFromFile();
                String userEnterPassword = String.valueOf(passwordField.getPassword());
                if (actualPassword.equals(userEnterPassword)){
                    dispose(); // close the pop up frame
                    SensorManager.getInstance().setAllSensors(false);
                } else {
                    JOptionPane.showMessageDialog(getParent(),
                            "The password is not correct",
                            "Wrong Password",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
        });

        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(passwordField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

    //read the last line of the file
    public String readFromPhoneNUmberFile() {
        String lastLine = "";
        String sCurrentLine;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("phoneNumber.txt"));
            while ((sCurrentLine = br.readLine()) != null) {
                lastLine = sCurrentLine;
            }
        } catch (IOException e) {
            System.out.println("phone number is empty");
        }
        return lastLine;
    }
}
