package main.ui;

import main.ui.menuPanels.ActiveTextField;
import main.model.SensorManager;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * Created by manhongren on 5/31/17.
 */
public class KeyPadPanel extends JPanel {

    private final JButton b1;
    private final JButton b2;
    private final JButton b3;
    private final JButton b4;
    private final JButton b5;
    private final JButton b6;
    private final JButton b7;
    private final JButton b8;
    private final JButton b9;
    private final JButton b0;
    private final JButton offButton;
    private final JButton awayButton;
    private final JButton onButton;
    private final JButton menuBtn;
    private final JButton deleteBtn;

    private final ActionHandler actionHandler = new ActionHandler();

    private boolean isBuildingLayoutShown = true;

    private Thread t;
    private Timer timer;
    private long startTime = -1;
    private long duration = 10000;
    private JLabel label;

    private static KeyPadPanel keyPadPanel;

    private KeyPadPanel(){

        //initialize keys
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
        b0 = new JButton("0");

        menuBtn = new JButton("menu");
        deleteBtn = new JButton("delete");

        setLayout(new GridLayout(5,3));

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
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
                    slideIn(DisplayPanel.getDisplayPanel());
                } else {
                   // SensorManager.getInstance().updateButtonState();
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "newBuildingLayoutPanel");
                }
                isBuildingLayoutShown = !isBuildingLayoutShown;
            }
        });

        //delete the last digit from any Textfield
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField currTextField = ActiveTextField.getInstance().getActiveTextField();
                if (currTextField.getText().length() > 0){
                    currTextField.setText(""+currTextField.getText().substring(0, currTextField.getText().length() - 1));
                }
            }
        });

        offButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SensorManager.getInstance().setAllSensors(false);

            }
        });

        onButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SensorManager.getInstance().setAllSensors(true);
            }
        });

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;

                    timer.stop();
                    SensorManager.getInstance().setAllSensors(true);
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                label.setText(df.format(duration - clockTime));
            }


        });


        awayButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }
                label = new JLabel("Sensor will be turned on in 10 seconds");


                JOptionPane.showMessageDialog(getParent(),
                        label,
                        "Warning Message",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }


    public void slideIn(Component parent){
        double y;
        y = parent.getLocation().getY();

        t = new Thread(() -> {
            for(double i = 600 ; i >= 0 ; i-- ){
                parent.setLocation((int)i,(int)y);
                try{
                    t.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        }); t.start();

    }

    public class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                JButton btn = (JButton) source;
                try {
                    int value = Integer.parseInt(btn.getText().trim());
                    Component comp = ActiveTextField.getInstance().getActiveTextField();
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


}
