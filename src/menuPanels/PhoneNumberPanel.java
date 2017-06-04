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
    private JTextField phoneNumberTextField1;
    private JTextField phoneNumberTextField2;
    private JLabel phoneNumberLabel;
    public PhoneNumberPanel(){
        initiateBtns();
        addActionListeners();
        setLayout(new GridLayout(0, 1, 5, 5));
        addBtnsToPanel();
    }

    private void initiateBtns(){
        phoneNumberLabel = new JLabel("Please enter two different phone numbers: ");
        enterBtn = new JButton("Enter");
        doneBtn = new JButton("Done");
        phoneNumberTextField1 = new JTextField();
        phoneNumberTextField1.setUI(new HintTextFieldUI(" 1st Contact Number", true));
        phoneNumberTextField2 = new JTextField();
        phoneNumberTextField2.setUI(new HintTextFieldUI(" 2nd Contact Number", true));
        phoneNumberLabel.setFont(new Font("Serif", Font.ITALIC, 20));
    }
    private void addBtnsToPanel(){
        add(phoneNumberLabel);
        add(phoneNumberTextField1);
        add(phoneNumberTextField2);
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

        phoneNumberTextField1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(phoneNumberTextField1);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        phoneNumberTextField2.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                ActiveTextField.getActiveTextField().setCurrentTextField(phoneNumberTextField2);
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


    }

}

