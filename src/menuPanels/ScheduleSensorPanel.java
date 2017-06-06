package menuPanels;

import fileManagers.SensorInfoFileManager;
import main.DisplayPanel;
import sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {
    private JLabel scheduleSensorLabel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    //private JButton enterButton;
    private JButton doneButton;

    public ScheduleSensorPanel(){

        setPreferredSize(new Dimension(350, 400));
        initializeComponents();
        addButtonsToPanel();
        setLayout(new BoxLayout(this, Y_AXIS));
        add(labelPanel);
        add(new ImagePanel());
        add(buttonPanel);
        //add action listeners
        addActionListeners();
    }

    private void initializeComponents(){
        labelPanel = new JPanel();
        buttonPanel = new JPanel();
       // enterButton = new JButton("Enter");
        doneButton = new JButton("Done");
        scheduleSensorLabel = new JLabel("Check the sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));
    }

    private void addButtonsToPanel() {
        labelPanel.add(scheduleSensorLabel);
       // buttonPanel.add(enterButton);
        buttonPanel.add(doneButton);
    }

    private void addActionListeners(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

    }


}
