package menuPanels;

import main.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 6/2/17.
 */
public class ScheduleTimePanel extends JPanel {
    private JRadioButton onJRadioButton;
    private JRadioButton offJRadioButton;
    private JSpinner fromSpinner;
    private JSpinner toSpinner;
    private JPanel radioButtonPanel;
    private JPanel spinnerPanel;
    private JPanel buttonPanel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel titleLabel;
    private JButton enterButton;
    private JButton doneButton;
    public ScheduleTimePanel(){
        initializeComponents();

        addComponentsToPanels();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(radioButtonPanel);
        add(spinnerPanel);
        add(buttonPanel);
        addActionListeners();
    }

    private void initializeComponents(){
        onJRadioButton = new JRadioButton("On");
        offJRadioButton = new JRadioButton("Off");
        ButtonGroup group = new ButtonGroup();
        group.add(onJRadioButton);
        group.add(offJRadioButton);

        setSpinners();
        radioButtonPanel = new JPanel();
        spinnerPanel = new JPanel();
        buttonPanel = new JPanel();
        fromLabel = new JLabel("From: ");
        toLabel = new JLabel("To:");

        enterButton = new JButton("Enter");
        doneButton = new JButton("Done");
        titleLabel = new JLabel("Set sensor.sensor Mode to : ");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 17));
    }
    private void addComponentsToPanels(){
        radioButtonPanel.add(titleLabel);
        radioButtonPanel.add(onJRadioButton);
        radioButtonPanel.add(offJRadioButton);
        spinnerPanel.setLayout(new GridLayout(0, 1));
        spinnerPanel.add(fromLabel);
        spinnerPanel.add(fromSpinner);
        spinnerPanel.add(toLabel);
        spinnerPanel.add(toSpinner);
        buttonPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.add(enterButton);
        buttonPanel.add(doneButton);
    }

    private void setSpinners(){

        Integer current = new Integer(12);
        Integer min = new Integer(1);
        Integer max = new Integer(24);
        Integer step = new Integer(1);

        Integer current2 = new Integer(12);
        Integer min2 = new Integer(1);
        Integer max2= new Integer(24);
        Integer step2 = new Integer(1);
        SpinnerNumberModel model = new SpinnerNumberModel(current, min, max, step);
        SpinnerNumberModel model2 = new SpinnerNumberModel(current2, min2, max2, step2);
        fromSpinner = new JSpinner(model);
        toSpinner = new JSpinner(model2);
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
