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
        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/resources/officeLayout.png"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                Collection<Sensor> sensors = SensorManager.getInstance().getSensors();
                for (Sensor s : sensors) {
                    JButton button = s.generateButton();
                    JCheckBox checkBox = s.generateCheckBox();
                    add(button);
                    add(checkBox);
                    buttons.add(button);
                    checkBoxes.add(checkBox);
                }
            }

            @Override
            public void componentHidden(ComponentEvent e) {}
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Sensor sensor = new Sensor(true, getSensorType(), e.getX(), e.getY());

                JButton button = new JButton("archer is awesome!");
                button.setBounds(e.getX(), e.getY(), 50, 50);
                add(button);

                JCheckBox checkBox = new JCheckBox();
                checkBox.setVisible(false);
                checkBox.setBounds(e.getX(), e.getY(), 50, 50);
                add(checkBox);

                SensorManager.getInstance().addSensor(sensor);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        initializeButtons();
        setLayout(null);
        setPositionOfButtons();
        //add action listeners
        registerActionListeners();

        addButtonsToPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void initializeButtons(){
        fireLabel = new JLabel("Fire Sensor");
        breakInLabel = new JLabel("BreakIn Sensor");
        fireRadioButton = new JRadioButton();
        breakInRadioButton = new JRadioButton();
        ButtonGroup group = new ButtonGroup();
        group.add(fireRadioButton);
        group.add(breakInRadioButton);
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
       // setLayout(new GridLayout(2,2));
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
