package main;

import fileManagers.SensorInfoFileManager;
import sensor.Sensor;
import sensor.SensorManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static sensor.Sensor.Type.BREAKIN;
import static sensor.Sensor.Type.FIRE;

/**
 * Created by manhongren on 6/1/17.
 */
public class BuildingLayoutPanel extends JPanel {
    private BufferedImage image;
    private JButton breakInSensorBtn;
    private JButton breakInSensorBtn2;
    private JButton fireSensorBtn1;
    private JButton fireSensorBtn2;
    private static BuildingLayoutPanel buildingLayoutPanel;
 //   private boolean hasStoredInFile;
    private BuildingLayoutPanel(){

    }
    public static BuildingLayoutPanel getBuildingLayoutPanel(){
        if (buildingLayoutPanel == null){
            buildingLayoutPanel = new BuildingLayoutPanel();
            buildingLayoutPanel.init();
        }
        return buildingLayoutPanel;
    }

    private void init(){
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
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void initializeButtons(){
        breakInSensorBtn = new JButton("BS");
        breakInSensorBtn.setForeground(Color.BLUE);
        breakInSensorBtn2 = new JButton("BS");
        breakInSensorBtn2.setForeground(Color.BLUE);
        fireSensorBtn1 = new JButton("FS");
        fireSensorBtn1.setForeground(Color.RED);
        fireSensorBtn2 = new JButton("FS");
        fireSensorBtn2.setForeground(Color.RED);
        //mapSensorsButtons to sensor object
        SensorManager.getInstance().addToSensorToButtonMap(fireSensorBtn1, new Sensor("FS1", false, FIRE));
        SensorManager.getInstance().addToSensorToButtonMap(fireSensorBtn2, new Sensor("FS2", false, FIRE));
        SensorManager.getInstance().addToSensorToButtonMap(breakInSensorBtn, new Sensor("BS1", false, BREAKIN));
        SensorManager.getInstance().addToSensorToButtonMap(breakInSensorBtn2, new Sensor("BS2", false, BREAKIN));

        //add to sensorInfo file, if the file is empty(which means add only once the sensor info)
        addSensorInfoToFile();


    }

    private void setPositionOfButtons(){
        fireSensorBtn1.setBounds(30, 50, 50, 50);
        fireSensorBtn2.setBounds(130, 20, 50, 50);
        breakInSensorBtn.setBounds(120, 260, 50,50);
        breakInSensorBtn2.setBounds(250, 200, 50,50);
    }

    private void addButtonsToPanel() {
        add(breakInSensorBtn);
        add(breakInSensorBtn2);
        add(fireSensorBtn1);
        add(fireSensorBtn2);
    }
    public JButton getBreakInSensorBtn(){
        return this.breakInSensorBtn;
    }

    public JButton getBreakInSensorBtn2(){
        return this.breakInSensorBtn2;
    }

    public JButton getFireSensorBtn1(){
        return this.fireSensorBtn1;
    }

    public JButton getFireSensorBtn2(){
        return this.fireSensorBtn2;
    }

    private void addSensorInfoToFile(){
        SensorInfoFileManager.Entry entry = new SensorInfoFileManager.Entry("FS1", false, FIRE);
        SensorInfoFileManager.Entry entry2 = new SensorInfoFileManager.Entry("FS2", false, FIRE);
        SensorInfoFileManager.Entry entry3 = new SensorInfoFileManager.Entry("BS1", false, FIRE);
        SensorInfoFileManager.Entry entry4 = new SensorInfoFileManager.Entry("BS2", false, FIRE);
        SensorInfoFileManager.getFileManager().addToFile(entry.toString() + '\n' + entry2.toString() + '\n' +
                entry3.toString() + '\n' + entry4.toString() );
    }

}
