package main.menuPanels;

import main.filemanagers.FeeManager;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;

/**
 * UI for displaying monthly fee.
 *
 * Created by manhongren on 6/7/17.
 */
public class MonthlyFeePanel extends JPanel implements FeeManager.OnFeeFileChangeListener {

    private final JPanel personalInfoPanel;
    private final JPanel intrusionPanel;
    private final JPanel firePanel;
    private final JPanel totalPanel;


    private final JLabel initialInstallLabelF;
    private final JLabel sensorInstallLabelF;
    private final JLabel serviceLabelF;

    private final JTextField intrusionInitialInstallTextField;
    private final JTextField intrusionSensorInstallTextField;
    private final JTextField intrusionServiceTextField;

    private final JLabel initialInstallLabelB;
    private final JLabel sensorInstallLabelB;
    private final JLabel serviceLabelB;

    private final JTextField fireInitialInstallTextField;
    private final JTextField fireSensorInstallTextField;
    private final JTextField fireServiceTextField;

    private final JTextField totalTextField;
    private final JTextArea textArea;

    private int fireTriggered = FeeManager.getFeeManager().getFireSensorTriggeredCount();
    private int breakInTriggered = FeeManager.getFeeManager().getBreakInSensorTriggeredCount();
    private int fireInstalled = FeeManager.getFeeManager().getFireSensorInstalledCount();
    private int breakInInstalled = FeeManager.getFeeManager().getBreakInSensorInstalledCount();

    private int totalAmount;

    public MonthlyFeePanel() {
        FeeManager.getFeeManager().registerOnFeeChangeListener(this);
        personalInfoPanel = new JPanel();
        personalInfoPanel.setBorder(new TitledBorder(new EtchedBorder(), "Personal Info"));
        textArea = new JTextArea();
        personalInfoPanel.add(textArea);

        initialInstallLabelB = new JLabel("Initial Install Fee");
        sensorInstallLabelB = new JLabel("Sensor Install Fee");
        serviceLabelB = new JLabel("Service Fee");

        intrusionInitialInstallTextField = new JTextField();
        intrusionSensorInstallTextField = new JTextField();
        intrusionServiceTextField = new JTextField();


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

        firePanel = new JPanel();
        firePanel.setBorder(new TitledBorder(new EtchedBorder(), "Fire"));
        firePanel.setLayout(new GridLayout(3, 2));
        firePanel.add(initialInstallLabelF);
        firePanel.add(fireInitialInstallTextField);
        firePanel.add(sensorInstallLabelF);
        firePanel.add(fireSensorInstallTextField);
        firePanel.add(serviceLabelF);
        firePanel.add(fireServiceTextField);

        totalTextField = new JTextField();

        totalPanel = new JPanel();
        totalPanel.setBorder(new TitledBorder(new EtchedBorder(), "Total"));
        totalPanel.add(totalTextField);
        populateView();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(personalInfoPanel);
        add(intrusionPanel);
        add(firePanel);
        add(totalPanel);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        File file = new File("infoSummary.txt");
        if (file.exists()){
            populateTextArea();
        }
    }

    /**
     * Update view when fee information has changed.
     */
    @Override
    public void onFeeFileChange(int fireTriggered, int breakInTriggered, int fireInstalled, int breakInInstalled) {
        this.fireTriggered = fireTriggered;
        this.breakInTriggered = breakInTriggered;
        this.fireInstalled = fireInstalled;
        this.breakInInstalled = breakInInstalled;
        populateView();
    }

    /**
     * Populate view based on info from {@link FeeManager}.
     */
    private void populateView(){
        totalAmount = 0;
        if (breakInInstalled > 0){
            intrusionInitialInstallTextField.setText("$200");
            intrusionSensorInstallTextField.setText("$50 * " + breakInInstalled);
            intrusionServiceTextField.setText("$20 * " + breakInTriggered);
            totalAmount += 200 + (50 * breakInInstalled) + (20 * breakInTriggered);
        }

        int discount = 0;
        if (fireInstalled > 0){
            if (fireInstalled > 0){
                discount = (int)(300 * 0.2);
            }
            fireInitialInstallTextField.setText("$300 - $" + discount);
            fireSensorInstallTextField.setText("$100 * " + fireInstalled);
            fireServiceTextField.setText("$50 * " + fireTriggered);
            totalAmount += (300 - discount) + (100 * fireInstalled) + (50 * fireTriggered);
        }
        totalTextField.setText("$" + totalAmount);
    }

    /**
     * Populate text area from summary text file.
     */
    private void populateTextArea(){
        textArea.setText(null);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("infoSummary.txt"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                textArea.append(line + '\n');
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
