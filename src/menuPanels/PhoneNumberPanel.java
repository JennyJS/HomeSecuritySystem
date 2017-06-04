package menuPanels;

import main.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by manhongren on 6/1/17.
 */
public class PhoneNumberPanel extends JPanel {
    private JButton enterBtn;
    private JButton doneBtn;
    private JTextField phoneNumberTextField;
    private JLabel phoneNumberLabel;
    public PhoneNumberPanel(){
        initiateBtns();
        addActionListeners();
        setLayout(new GridLayout(0, 1, 5, 5));
        addBtnsToPanel();
    }

    private void initiateBtns(){
        phoneNumberLabel = new JLabel("Please enter your phone number: ");
        enterBtn = new JButton("Enter");
        doneBtn = new JButton("Done");
        phoneNumberTextField = new JTextField();
        phoneNumberLabel.setFont(new Font("Serif", Font.ITALIC, 20));
    }
    private void addBtnsToPanel(){
        add(phoneNumberLabel);
        add(phoneNumberTextField);
        add(enterBtn);
        add(doneBtn);
    }

    private void addActionListeners(){
        doneBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

        phoneNumberTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(phoneNumberTextField);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
    }

}

