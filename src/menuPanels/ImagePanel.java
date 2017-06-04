package menuPanels;

import fileManagers.SensorInfoFileManager;
import fileManagers.UpdateFile;
import main.BuildingLayoutPanel;
import sensor.Sensor;
import sensor.SensorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by manhongren on 6/3/17.
 */
public class ImagePanel extends JPanel {
    private BufferedImage image;
    private JCheckBox breakInCheckBox;
    private JCheckBox breakInCheckBox2;
    private JCheckBox fireCheckBox1;
    private JCheckBox fireCheckBox2;

    public ImagePanel(){

        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/resources/buildingLayout.jpg"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        initializeButtons();
        setLayout(null);
        setPositionOfButtons();
        addButtonsToPanel();
        registerActionListeners();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void initializeButtons(){
        breakInCheckBox = new JCheckBox();
        breakInCheckBox2 = new JCheckBox();
        fireCheckBox1 = new JCheckBox();
        fireCheckBox2 = new JCheckBox();
        SensorManager.getInstance().addToCheckBoxToButtonMap(breakInCheckBox, BuildingLayoutPanel.getBuildingLayoutPanel().getBreakInSensorBtn());
        SensorManager.getInstance().addToCheckBoxToButtonMap(breakInCheckBox2, BuildingLayoutPanel.getBuildingLayoutPanel().getBreakInSensorBtn2());
        SensorManager.getInstance().addToCheckBoxToButtonMap(fireCheckBox1, BuildingLayoutPanel.getBuildingLayoutPanel().getFireSensorBtn1());
        SensorManager.getInstance().addToCheckBoxToButtonMap(fireCheckBox2, BuildingLayoutPanel.getBuildingLayoutPanel().getFireSensorBtn2());
    }

    private void setPositionOfButtons(){
        fireCheckBox1.setBounds(30, 50, 50, 50);
        fireCheckBox2.setBounds(130, 20, 50, 50);
        breakInCheckBox.setBounds(130, 260, 50,50);
        breakInCheckBox2.setBounds(250, 210, 50,50);
    }

    private void addButtonsToPanel() {
        add(breakInCheckBox);
        add(breakInCheckBox2);
        add(fireCheckBox1);
        add(fireCheckBox2);
    }

    private void registerActionListeners(){
        breakInCheckBox.addActionListener(new ActionListener() {
            JButton tmpButton = SensorManager.getInstance().getButtonFromCheckBox(breakInCheckBox);
            Sensor tmpSensor = SensorManager.getInstance().getSensorFromButton(tmpButton);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (breakInCheckBox.isSelected()){
                    tmpSensor.setSensorOn(true);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),true, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("breakInCheckBox is checked");
                } else {
                    tmpSensor.setSensorOn(false);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),false, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("breakInCheckBox is unChecked");
                }
            }
        });

        breakInCheckBox2.addActionListener(new ActionListener() {
            JButton tmpButton = SensorManager.getInstance().getButtonFromCheckBox(breakInCheckBox2);
            Sensor tmpSensor = SensorManager.getInstance().getSensorFromButton(tmpButton);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (breakInCheckBox2.isSelected()){
                    tmpSensor.setSensorOn(true);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),true, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("breakInCheckBox2 is checked");
                } else {
                    tmpSensor.setSensorOn(false);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),false, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("breakInCheckBox2 is unChecked");
                }
            }
        });

        fireCheckBox1.addActionListener(new ActionListener() {
            JButton tmpButton = SensorManager.getInstance().getButtonFromCheckBox(fireCheckBox1);
            Sensor tmpSensor = SensorManager.getInstance().getSensorFromButton(tmpButton);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fireCheckBox1.isSelected()){
                    tmpSensor.setSensorOn(true);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),true, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("fireCheckBox1 is checked");
                } else {
                    tmpSensor.setSensorOn(false);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),false, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("fireCheckBox1 is unChecked");
                }
            }
        });

        fireCheckBox2.addActionListener(new ActionListener() {
            JButton tmpButton = SensorManager.getInstance().getButtonFromCheckBox(fireCheckBox2);
            Sensor tmpSensor = SensorManager.getInstance().getSensorFromButton(tmpButton);
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fireCheckBox2.isSelected()){
                    tmpSensor.setSensorOn(true);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),true, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("fireCheckBox2 is checked");
                } else {
                    tmpSensor.setSensorOn(false);
                    //update file
                    String fileName = SensorInfoFileManager.getFileManager().getFileName();
                    SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry(tmpSensor.getSensorId(),false, tmpSensor.getType());
                    UpdateFile.updateFiles(fileName, tmpSensor.getSensorId(), entry.toString());
                    System.out.println("fireCheckBox2 is unChecked");
                }
            }
        });

    }
}
