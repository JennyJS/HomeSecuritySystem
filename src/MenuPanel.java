import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

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

//        setBorder(
//                BorderFactory.createTitledBorder(
//                        BorderFactory.createEtchedBorder(
//                                EtchedBorder.RAISED, Color.GRAY
//                                , Color.DARK_GRAY)));
        setBorder(BorderFactory.createEmptyBorder(4,4,8,4));
      //  setBorder(BorderFactory.createLineBorder(Color.black));

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
}
