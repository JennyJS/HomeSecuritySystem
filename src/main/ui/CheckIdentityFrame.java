package main.ui;

import main.model.PasswordFileManager;
import main.ui.menuPanels.ActiveTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Frame to verify password.
 *
 * Created by manhongren on 6/5/17.
 */
public class CheckIdentityFrame extends JFrame {

    private final JLabel label;
    private final JPasswordField passwordField;
    private final JButton enterButton;
    private final String nextPanelToShow;

    public CheckIdentityFrame(String nextPanelToShow){
        this.nextPanelToShow = nextPanelToShow;
        label = new JLabel("    Enter Password:     ");
        passwordField = new JPasswordField();
        enterButton = new JButton("Enter");
        setLayout(new GridLayout(0,1));
        registerActionListeners();
        add(label);
        add(passwordField);
        add(enterButton);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    private void registerActionListeners(){
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String actualPassword = PasswordFileManager.getFileManager().getPassword();
                String userEnterPassword = String.valueOf(passwordField.getPassword());
                if (actualPassword.length() == 0 || actualPassword.equals(userEnterPassword)){
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), nextPanelToShow);
                    dispose(); // close the pop up frame
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
                ActiveTextField.getInstance().setActiveTextField(passwordField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
}
