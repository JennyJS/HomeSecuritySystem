package menuPanels;

import fileManagers.FeeManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by manhongren on 6/7/17.
 */
public class MonthlyFeePanel extends JPanel{
    private JPanel intrusionPanel;
    private JPanel firePanel;
    private JPanel totalPanel;


    private JLabel initialInstallLabelF;
    private JLabel sensorInstallLabelF;
    private JLabel serviceLabelF;

    private JTextField intrusionInitialInstallTextField;
    private JTextField intrusionSensorInstallTextField;
    private JTextField intrusionServiceTextField;

    private JLabel initialInstallLabelB;
    private JLabel sensorInstallLabelB;
    private JLabel serviceLabelB;

    private JTextField fireInitialInstallTextField;
    private JTextField fireSensorInstallTextField;
    private JTextField fireServiceTextField;

    private JTextField totalTextField;

    private int totalAmount;

    public MonthlyFeePanel(){

        initialInstallLabelB = new JLabel("Initial Install Fee");
        sensorInstallLabelB = new JLabel("Sensor Install Fee");
        serviceLabelB = new JLabel("Service Fee");

        intrusionInitialInstallTextField = new JTextField();
        intrusionSensorInstallTextField = new JTextField();
        intrusionServiceTextField = new JTextField();
        if (FeeManager.getFeeManager().getBreakInSensorInstalledCount() > 0){
            intrusionInitialInstallTextField.setText("$200");
            intrusionSensorInstallTextField.setText("$50 * " + FeeManager.getFeeManager().getBreakInSensorInstalledCount());
            intrusionServiceTextField.setText("$20 * " + FeeManager.getFeeManager().getBreakInSensorTriggeredCount());
            totalAmount = totalAmount +  200 + (50 * FeeManager.getFeeManager().getBreakInSensorInstalledCount())
                    + (20 * FeeManager.getFeeManager().getBreakInSensorTriggeredCount());

        }

        intrusionPanel = new JPanel();
        intrusionPanel.setBorder(new TitledBorder(new EtchedBorder(), "Intrusion"));
        intrusionPanel.setLayout(new GridLayout(3, 2));
        intrusionPanel.add(initialInstallLabelB);
        intrusionPanel.add(intrusionInitialInstallTextField);
        intrusionPanel.add(sensorInstallLabelB);
        intrusionPanel.add(intrusionSensorInstallTextField);
        intrusionPanel.add(serviceLabelB);
        intrusionPanel.add(intrusionServiceTextField);

        initialInstallLabelF = new JLabel("Initial Install Fee");
        sensorInstallLabelF = new JLabel("Sensor Install Fee");
        serviceLabelF = new JLabel("Service Fee");

        fireInitialInstallTextField = new JTextField();
        fireSensorInstallTextField = new JTextField();
        fireServiceTextField = new JTextField();

        if (FeeManager.getFeeManager().getFireSensorInstalledCount() > 0){
            int disount = 0;
            if (FeeManager.getFeeManager().getBreakInSensorInstalledCount() > 0){
                disount = (int)(300 * 0.2);
            }
            fireInitialInstallTextField.setText("$300 - $" + disount);
            fireSensorInstallTextField.setText("$100 * " + FeeManager.getFeeManager().getFireSensorInstalledCount());
            fireServiceTextField.setText("$50 * " + FeeManager.getFeeManager().getFireSensorTriggeredCount());
            totalAmount = totalAmount + (300 - disount) + (100 * FeeManager.getFeeManager().getFireSensorInstalledCount())
                    + (50 * FeeManager.getFeeManager().getFireSensorTriggeredCount());
        }

        firePanel = new JPanel();
        firePanel.setBorder(new TitledBorder(new EtchedBorder(), "Fire"));
        firePanel.setLayout(new GridLayout(3, 2));
        firePanel.add(initialInstallLabelF);
        firePanel.add(fireInitialInstallTextField);
        firePanel.add(sensorInstallLabelF);
        firePanel.add(fireSensorInstallTextField);
        firePanel.add(serviceLabelF);
        firePanel.add(fireServiceTextField);

        totalTextField = new JTextField("$" + totalAmount);
        totalPanel = new JPanel();
        totalPanel.setBorder(new TitledBorder(new EtchedBorder(), "Total"));
        totalPanel.add(totalTextField);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(intrusionPanel);
        add(firePanel);
        add(totalPanel);
    }
}
