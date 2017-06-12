package main;

import main.sensor.Sensor;
import main.sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Panel UI to add and display sensors.
 *
 * Created by manhongren on 6/6/17.
 */
public class SensorPanel extends JPanel implements SensorManager.OnSensorChangeListener  {

    private static final Font LABEL_FONT = new Font("GungSeo", Font.PLAIN, 30);

    private final boolean showRadioButton;
    private final boolean showButton;

    private final BufferedImage image;
    private JLabel fireLabel;
    private JLabel breakInLabel;
    private JRadioButton fireRadioButton;
    private JRadioButton breakInRadioButton;

    private final Map<JCheckBox, Sensor> sensorByCheckbox = new HashMap<>();
    private final Map<JButton, Sensor> sensorByButton = new HashMap<>();

    public SensorPanel(boolean showRadioButton, boolean showButton){
        this.showRadioButton = showRadioButton;
        this.showButton = showButton;

        SensorManager.getInstance().registerOnSensorChangeListener(this);


        if (showButton) {
            addButtons(SensorManager.getInstance().getSensors());
        } else {
            addCheckBoxes(SensorManager.getInstance().getSensors());
        }

        if (showRadioButton) {
            fireLabel = new JLabel("Fire Sensor");
            breakInLabel = new JLabel("BreakIn Sensor");
            fireRadioButton = new JRadioButton();
            breakInRadioButton = new JRadioButton();
            ButtonGroup group = new ButtonGroup();
            group.add(fireRadioButton);
            group.add(breakInRadioButton);
            fireRadioButton.setSelected(true);
            layoutRadioButtons();

            this.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {}

                @Override
                public void mousePressed(MouseEvent e) {
                    Sensor sensor = new Sensor(true, getSensorType(), e.getX(), e.getY());
                    SensorManager.getInstance().addSensor(sensor);
                }

                @Override
                public void mouseReleased(MouseEvent e) {}

                @Override
                public void mouseEntered(MouseEvent e) {}

                @Override
                public void mouseExited(MouseEvent e) {}
            });
        }

        setPreferredSize(new Dimension(650, 350));
        image = FileUtil.getImageWithName("clear_building.png");

        setLayout(null);
        registerActionListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

        if (showRadioButton) {
            addRadioButtonsToPanel();
        }
    }

    public void updateSensorsFromCheckBoxes() {
        for (Map.Entry<JCheckBox, Sensor> entry : sensorByCheckbox.entrySet()){
            entry.getValue().setSensorOn(entry.getKey().isSelected());
        }
        SensorManager.getInstance().syncToFile();
        SensorManager.getInstance().notifySensorChange();
    }

    private void layoutRadioButtons(){
        fireLabel.setBounds(120, 350, 200, 30);
        fireLabel.setForeground(Color.RED);
        fireLabel.setFont(LABEL_FONT);
        fireRadioButton.setBounds(150, 380, 50, 20);

        breakInLabel.setBounds(300, 350, 200, 30);
        breakInLabel.setFont(LABEL_FONT);
        breakInRadioButton.setBounds(300, 380, 50,20);
        breakInLabel.setForeground(Color.BLUE);
    }

    private void registerActionListeners(){}

    private void addRadioButtonsToPanel(){
        add(fireLabel);
        add(breakInLabel);
        add(fireRadioButton);
        add(breakInRadioButton);
    }


    private Sensor.Type getSensorType() {
       if (fireRadioButton.isSelected()) {
           return Sensor.Type.FIRE;
       } else if (breakInRadioButton.isSelected()) {
           return Sensor.Type.BREAK_IN;
       } else {
           return null;
       }
    }

    /**
     * Upon sensor change. Remove all component and add buttons or check boxes.
     */
    @Override
    public void onSensorChange(Set<Sensor> sensors) {
        removeAll();
        if (showButton) {
            addButtons(sensors);
        } else {
            addCheckBoxes(sensors);
        }
        repaint();
    }

    private void addButtons(Set<Sensor> sensors) {
        sensorByButton.clear();
        for (Sensor s : sensors) {
            JButton button = s.generateButton();
            sensorByButton.put(button, s);
            add(button);
        }
    }

    private void addCheckBoxes(Set<Sensor> sensors) {
        sensorByCheckbox.clear();
        for (Sensor s : sensors) {
            JCheckBox checkBox = s.generateCheckBox();
            sensorByCheckbox.put(checkBox, s);
            add(checkBox);
        }
    }
}
