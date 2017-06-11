package main.ui.menuPanels;

import main.model.PasswordFileManager;
import main.ui.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Display password UI.
 *
 * Created by manhongren on 6/2/17.
 */
public class PasswordPanel extends JPanel {

    private final JButton doneBtn;
    private final JPasswordField passwordTextField;
    private final JPasswordField confirmPasswordTextField;
    private final JLabel passwordLabel;
    private final JLabel confirmPasswordLabel;

    private char[] firstInput;
    private char[] secondInput;

    public PasswordPanel() {

        passwordLabel = new JLabel("Please enter your password: ");
        confirmPasswordLabel = new JLabel("Please confirm your password: ");
        doneBtn = new JButton("Done");
        passwordTextField = new JPasswordField();

        passwordLabel.setFont(new Font("Serif", Font.ITALIC, 20));
        confirmPasswordTextField = new JPasswordField();
        confirmPasswordLabel.setFont(new Font("Serif", Font.ITALIC, 20));

        addActionListeners();
        setLayout(new GridLayout(0, 1, 5, 5));
        addBtnsToPanel();
    }

    private void addBtnsToPanel(){
        add(passwordLabel);
        add(passwordTextField);
        add(confirmPasswordLabel);
        add(confirmPasswordTextField);
        add(doneBtn);
    }

    private void addActionListeners(){
        doneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstInput = passwordTextField.getPassword();
                secondInput = confirmPasswordTextField.getPassword();
                if (firstInput == null || firstInput.length == 0 || secondInput == null || secondInput.length == 0){
                    JOptionPane.showMessageDialog(getParent(),
                            "Please enter two passwords",
                            "No Password",
                            JOptionPane.WARNING_MESSAGE);
                    return;

                }
                for (int i = 0; i < firstInput.length; i++){
                    if (firstInput[i] != secondInput[i]){
                        JOptionPane.showMessageDialog(getParent(),
                                "The two passwords are not matching",
                                "Not Matching",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                PasswordFileManager.getFileManager().savePassword(String.valueOf(secondInput));
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

        passwordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getInstance().setActiveTextField(passwordTextField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        confirmPasswordTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getInstance().setActiveTextField(confirmPasswordTextField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }
}
