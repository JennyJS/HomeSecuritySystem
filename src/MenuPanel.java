import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 6/1/17.
 */
public class MenuPanel extends JPanel {
    private JButton setPhoneNumberBtn;
    private JButton setPasswordBtn;
    private JButton setSensorBtn;
    private JButton scheduleTimeBtn;
    private JButton monthlyFeeBtn;

    public MenuPanel(){
        //initiate all buttons
        initiatedButtons();

        setLayout(new GridLayout(0, 1));

        //add action listeners
        addActionListeners();

        //add buttons to this panel
        addButtonsToPanel();

    }

    private void initiatedButtons(){
        setPhoneNumberBtn = new JButton("Set Phone Number");
        setPasswordBtn = new JButton("Set Password");
        setSensorBtn = new JButton("Schedule Sensor");
        scheduleTimeBtn = new JButton("Schedule Time");
        monthlyFeeBtn = new JButton("Monthly Fee");
    }

    private void addButtonsToPanel(){
        add(setPhoneNumberBtn);
        add(setPasswordBtn);
        add(setSensorBtn);
        add(scheduleTimeBtn);
        add(monthlyFeeBtn);
    }

    private void addActionListeners(){
        setPhoneNumberBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "phoneNumberPanel");
            }
        });

        setPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "passwordPanel");
            }
        });
    }
}
