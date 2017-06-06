package menuPanels;

import fileManagers.FileReplace;
import fileManagers.SensorInfoFileManager;
import main.DisplayPanel;
import sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by manhongren on 6/2/17.
 */
public class ScheduleTimePanel extends JPanel {
    private JRadioButton onJRadioButton;
    private JRadioButton offJRadioButton;
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
    private Timer timer;
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


    private void addActionListeners(){
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //read start time and end time from textfield
                StringBuilder sbForStartTimer = new StringBuilder();
                sbForStartTimer.append(fromHourTextField.getText());
                sbForStartTimer.append(":");
                sbForStartTimer.append(fromMinuteTextField.getText());

                StringBuilder sbToStartTimer = new StringBuilder();
                sbToStartTimer.append(toHourTextField.getText());
                sbToStartTimer.append(":");
                sbToStartTimer.append(toMinuteTextField.getText());

                //Start Timer
                startTimer(sbForStartTimer.toString(), sbToStartTimer.toString());

                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
            }
        });
    }

    private void startTimer(String startTime, String endTime){

        final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

        ActionListener timerListener = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Date date = new Date();
                String time = timeFormat.format(date);
                System.out.println("Current time " + time);
                if (time.equals(startTime)){
                    //TODO add based on radio button is selected on/off
                    FileReplace.doIt(SensorInfoFileManager.getFileManager().getFileName(), "false", "true");
                    //update BuildingLayout and checkboxes
                    SensorManager.getInstance().updateButtonState();
                    SensorManager.getInstance().updateCheckBoxState();

                    System.out.println("*********** Sensors On ***********");
                }

                if (time.equals(endTime)){
                    FileReplace.doIt(SensorInfoFileManager.getFileManager().getFileName(), "true", "false");
                    SensorManager.getInstance().updateButtonState();
                    SensorManager.getInstance().updateCheckBoxState();
                    System.out.println("$$$$$$$$$$$ Sensors Off $$$$$$$$$$$");
                    timer.stop();
                }

            }
        };
        timer = new Timer(10000, timerListener);
        timer.start();
        // to make sure it doesn't wait one second at the start
        timer.setInitialDelay(0);

    }
}
