import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import static javax.swing.BoxLayout.Y_AXIS;

/**
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {
    private JCheckBox breakInCheckBox;
    private JCheckBox breakInCheckBox2;
    private JCheckBox fireCheckBox1;
    private JCheckBox fireCheckBox2;
    private JLabel scheduleSensorLabel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton enterButton;
    private JButton doneButton;

    public ScheduleSensorPanel(){

        setPreferredSize(new Dimension(350, 400));
        initializeComponents();
        setPositionOfButtons();
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
        enterButton = new JButton("Enter");
        doneButton = new JButton("Done");
        breakInCheckBox = new JCheckBox();
        breakInCheckBox2 = new JCheckBox();
        fireCheckBox1 = new JCheckBox();
        fireCheckBox2 = new JCheckBox();
        scheduleSensorLabel = new JLabel("Check the sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));
    }

    private void setPositionOfButtons(){
        fireCheckBox1.setBounds(30, 50, 50, 50);
        fireCheckBox2.setBounds(130, 20, 50, 50);
        breakInCheckBox.setBounds(130, 260, 50,50);
        breakInCheckBox2.setBounds(250, 210, 50,50);
    }

    private void addButtonsToPanel() {
        labelPanel.add(scheduleSensorLabel);
        buttonPanel.add(enterButton);
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
