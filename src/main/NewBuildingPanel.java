package main;

import sensor.Sensor;
import sensor.SensorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manhongren on 6/6/17.
 */
public class NewBuildingPanel extends JPanel {
    private BufferedImage image;
    private JLabel fireLabel;
    private JLabel breakInLabel;
    private JRadioButton fireRadioButton;
    private JRadioButton breakInRadioButton;

    private final Set<JButton> buttons;
    private final Set<JCheckBox> checkBoxes;

    public NewBuildingPanel(){
        buttons = new HashSet<>();
        checkBoxes = new HashSet<>();
        fireLabel = new JLabel("Fire Sensor");
        breakInLabel = new JLabel("BreakIn Sensor");
        fireRadioButton = new JRadioButton();
        breakInRadioButton = new JRadioButton();
        ButtonGroup group = new ButtonGroup();
        group.add(fireRadioButton);
        group.add(breakInRadioButton);
        fireRadioButton.setSelected(true);
        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/resources/officeLayout.png"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                Sensor sensor = new Sensor(true, getSensorType(), e.getX(), e.getY());
                SensorManager.getInstance().addSensor(sensor);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        setLayout(null);
        setPositionOfButtons();
        registerActionListeners();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();
        g.drawImage(image, 0, 0, this);
        Collection<Sensor> sensors = SensorManager.getInstance().getSensors();
        for (Sensor s : sensors) {
            JButton button = s.generateButton();
            JCheckBox checkBox = s.generateCheckBox();
            add(button);
            buttons.add(button);
            checkBoxes.add(checkBox);
        }
        addButtonsToPanel();
    }

    private void setPositionOfButtons(){
        fireLabel.setBounds(100, 300, 100, 50);
        fireLabel.setForeground(Color.RED);
        fireRadioButton.setBounds(120, 330, 100, 50);
        breakInLabel.setBounds(250, 300, 100, 50);
        breakInRadioButton.setBounds(270, 330, 100,50);
        breakInLabel.setForeground(Color.BLUE);
    }

    private void registerActionListeners(){}

    private void addButtonsToPanel(){
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

}
