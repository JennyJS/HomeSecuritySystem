package main;

import fileManagers.SensorInfoFileManager;
import menuPanels.ActiveTextField;
import sensor.SensorManager;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 5/31/17.
 */
public class KeyPadPanel extends JPanel {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b0;
    private JButton offButton;
    private JButton awayButton;
    private JButton onButton;

    private JButton menuBtn;
    private JButton deleteBtn;
    private JButton enterBtn;
    private JButton panicButton;
    private JButton backBtn;
    private boolean isBuildingLayoutShown = true;
    private ActionHandler actionHandler = new ActionHandler();

    private static KeyPadPanel keyPadPanel;
  //  private main.DisplayPanel displayPanel = new main.DisplayPanel();

    private KeyPadPanel(){
        //initialize keys
        initializeKeys();

        //Create the event handler

        setLayout(new GridLayout(6,3));

        // add action listeners
        addActionListeners();

        //add buttons to this panel
        addBtnToPanel();


    }

    public static KeyPadPanel getKeyPadPanel(){
        if (keyPadPanel == null){
            keyPadPanel = new KeyPadPanel();
        }
        return keyPadPanel;
    }

    private void initializeKeys(){
        offButton = new JButton("Off");
        onButton = new JButton("On");
        awayButton = new JButton("Away");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        menuBtn = new JButton("menu");
        b0 = new JButton("0");
        deleteBtn = new JButton("delete");
        enterBtn = new JButton("Enter");
        panicButton = new JButton("Panic");
        backBtn = new JButton("Back");
    }

    private void addBtnToPanel(){
        add(offButton);
        add(onButton);
        add(awayButton);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(menuBtn);
        add(b0);
        add(deleteBtn);
        add(enterBtn);
        add(panicButton);
        add(backBtn);
    }

    private void addActionListeners(){
        b1.addActionListener(actionHandler);
        b2.addActionListener(actionHandler);
        b3.addActionListener(actionHandler);
        b4.addActionListener(actionHandler);
        b5.addActionListener(actionHandler);
        b6.addActionListener(actionHandler);
        b7.addActionListener(actionHandler);
        b8.addActionListener(actionHandler);
        b9.addActionListener(actionHandler);
        b0.addActionListener(actionHandler);
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch only between buildingLayoutPanel and menuPanel

                if (isBuildingLayoutShown){
                    updateButtonState();
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
                } else {
                    updateButtonState();
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "buildingLayoutPanel");
                }
                isBuildingLayoutShown = !isBuildingLayoutShown;
            }
        });

        //delete the last digit from any Textfield
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField currTextField = ActiveTextField.getActiveTextField().getCurrentTextField();
                currTextField.setText(""+currTextField.getText().substring(0, currTextField.getText().length() - 1));
            }
        });
    }


    public class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                JButton btn = (JButton) source;
                try {
                    int value = Integer.parseInt(btn.getText().trim());
                    Component comp = ActiveTextField.getActiveTextField().getCurrentTextField();
                    if (comp instanceof JTextComponent) {
                        JTextComponent tc = (JTextComponent) comp;
                        tc.setText(tc.getText() + value);
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }

        }
    }

    private void updateButtonState(){
        String fileStr = SensorInfoFileManager.getFileManager().readFromFile();
        String[] strArr = fileStr.split(System.lineSeparator());
        for (String innerStr : strArr){
            String[] innerStrArr = innerStr.split(",");
            String statusStr = innerStrArr[2];
            String sensorId = innerStrArr[0].split(":")[1];
            JButton button = SensorManager.getInstance().getButtonFromSensorId(sensorId);
            if (statusStr.split(":")[1].equals("true")) {
                //set the specific Check box checked
                button.setBackground(Color.GREEN);
                button.setOpaque(true);
            } else {
                button.setBackground(Color.WHITE);
                button.setOpaque(true);
            }
        }
    }

}
