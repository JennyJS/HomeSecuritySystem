package main.menuPanels;

import main.DisplayPanel;
import main.SensorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

/**
 * UI to schedule sensor on/off.
 *
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {

    private final JLabel scheduleSensorLabel;
    private final JPanel labelPanel;
    private final JPanel buttonPanel;
    private final JButton doneButton;
    private final SensorPanel sensorPanel;

    public ScheduleSensorPanel(){

        setPreferredSize(new Dimension(350, 400));

        labelPanel = new JPanel();
        buttonPanel = new JPanel();
        doneButton = new JButton("Done");
        sensorPanel = new SensorPanel(false, false);
        scheduleSensorLabel = new JLabel("Check the main.sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));

        add(labelPanel);
        add(sensorPanel);
        add(buttonPanel);
        labelPanel.add(scheduleSensorLabel);
        buttonPanel.add(doneButton);

        setLayout(new BoxLayout(this, Y_AXIS));

        //add action listeners
        addActionListeners();
    }

    private void addActionListeners(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sensorPanel.updateSensorsFromCheckBoxes();
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });

    }
}
