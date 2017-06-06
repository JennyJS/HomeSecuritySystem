package menuPanels;

import main.DisplayPanel;
import main.NewBuildingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {
    private JLabel scheduleSensorLabel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton doneButton;
    private JPanel newBuildingPanel;

    public ScheduleSensorPanel(){
        setPreferredSize(new Dimension(350, 400));

        labelPanel = new JPanel();
        buttonPanel = new JPanel();
        doneButton = new JButton("Done");
        newBuildingPanel = new NewBuildingPanel();
        scheduleSensorLabel = new JLabel("Check the sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));

        add(labelPanel);
        add(newBuildingPanel);
        add(buttonPanel);
        addButtonsToPanel();
        setLayout(new BoxLayout(this, Y_AXIS));

        //add action listeners
        addActionListeners();

        final ScheduleSensorPanel that = this;
    }

    private void addButtonsToPanel() {
        labelPanel.add(scheduleSensorLabel);
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
