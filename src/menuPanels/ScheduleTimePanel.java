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
//    private JSpinner fromSpinner;
//    private JSpinner toSpinner;
    private JPanel radioButtonPanel;
    private JPanel timePanel;
    private JPanel buttonPanel;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JLabel titleLabel;
    private JButton enterButton;
    private JButton doneButton;
    private JTextField fromHourTextField;
    private JTextField fromMinuteTextField;
    private JTextField toHourTextField;
    private JTextField toMinuteTextField;
    private JLabel colonLabel;
    private JLabel colonLabel2;
    public ScheduleTimePanel(){
        initializeComponents();

        addComponentsToPanels();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(radioButtonPanel);
        add(timePanel);
        add(buttonPanel);
        addActionListeners();
    }

    private void initializeComponents(){
        onJRadioButton = new JRadioButton("On");
        offJRadioButton = new JRadioButton("Off");
        ButtonGroup group = new ButtonGroup();
        group.add(onJRadioButton);
        group.add(offJRadioButton);

       // setSpinners();
        fromHourTextField = new JTextField();
        fromHourTextField.setUI(new HintTextFieldUI(" hour", true));
        fromMinuteTextField = new JTextField();
        fromMinuteTextField.setUI(new HintTextFieldUI(" minute", true));
        toHourTextField = new JTextField();
        toHourTextField.setUI(new HintTextFieldUI(" hour", true));
        toMinuteTextField = new JTextField();
        toMinuteTextField.setUI(new HintTextFieldUI(" minute", true));

        radioButtonPanel = new JPanel();
        timePanel = new JPanel();
        buttonPanel = new JPanel();
        fromLabel = new JLabel("From: ");
        fromLabel.setFont(new Font("Serif", Font.ITALIC, 17));
        toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Serif", Font.ITALIC, 17));
        colonLabel = new JLabel("         :");
        colonLabel.setFont(new Font("Serif", Font.BOLD, 20));
        colonLabel2 = new JLabel("         :");
        colonLabel2.setFont(new Font("Serif", Font.BOLD, 20));

        enterButton = new JButton("Enter");
        doneButton = new JButton("Done");
        titleLabel = new JLabel("Set sensor mode to : ");
        titleLabel.setFont(new Font("Serif", Font.ITALIC, 17));
    }
    private void addComponentsToPanels(){
        radioButtonPanel.add(titleLabel);
        radioButtonPanel.add(onJRadioButton);
        radioButtonPanel.add(offJRadioButton);
        timePanel.setLayout(new GridLayout(2, 4));
        timePanel.add(fromLabel);
       // timePanel.add(fromSpinner);
        timePanel.add(fromHourTextField);
        timePanel.add(colonLabel);
        timePanel.add(fromMinuteTextField);
        timePanel.add(toLabel);
        timePanel.add(toHourTextField);
        timePanel.add(colonLabel2);
        timePanel.add(toMinuteTextField);
       // timePanel.add(toSpinner);
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
//        fromSpinner = new JSpinner(model);
//        toSpinner = new JSpinner(model2);
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
