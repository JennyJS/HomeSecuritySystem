package main;

import sensor.Sensor;
import sensor.SensorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by manhongren on 6/6/17.
 */
public class SensorPanel extends JPanel implements SensorManager.OnSensorChangeListener {

    private final boolean showRadioButton;
    private final boolean showButton;

    private BufferedImage image;
    private JLabel fireLabel;
    private JLabel breakInLabel;
    private JRadioButton fireRadioButton;
    private JRadioButton breakInRadioButton;
    private final Map<JCheckBox, Sensor> sensorByCheckbox = new HashMap<>();

    private final Set<JButton> buttons;
    private final Set<JCheckBox> checkBoxes;

    public SensorPanel(boolean showRadioButton, boolean showButton){
        this.showRadioButton = showRadioButton;
        this.showButton = showButton;

        SensorManager.getInstance().registerOnSensorChangeListener(this);

        buttons = new HashSet<>();
        checkBoxes = new HashSet<>();

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

        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/resources/officeLayout.png"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        setLayout(null);
        registerActionListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("paint component");
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);

        if (showRadioButton) {
            addRadioButtonsToPanel();
        }
    }

    private void layoutRadioButtons(){
        fireLabel.setBounds(100, 300, 100, 50);
        fireLabel.setForeground(Color.RED);
        fireRadioButton.setBounds(120, 330, 100, 50);
        breakInLabel.setBounds(250, 300, 100, 50);
        breakInRadioButton.setBounds(270, 330, 100,50);
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
        for (Sensor s : sensors) {
            JButton button = s.generateButton();
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

    public void updateSensorsFromCheckBoxes() throws IOException {
        for (Map.Entry<JCheckBox, Sensor> entry : sensorByCheckbox.entrySet()){
            entry.getValue().setSensorOn(entry.getKey().isSelected());
        }
        SensorManager.getInstance().syncToFile();
        SensorManager.getInstance().notifySensorChange();
    }
}
