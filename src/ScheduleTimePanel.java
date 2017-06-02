import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        titleLabel = new JLabel("Set Sensor Mode to : ");
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
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 24); // 1pm
        SpinnerDateModel dateModel = new SpinnerDateModel(calendar.getTime(), null,
                null, Calendar.HOUR_OF_DAY);

        fromSpinner = new JSpinner(dateModel);
        toSpinner = new JSpinner(dateModel);
    }
}
