package main;

import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by manhongren on 6/6/17.
 */
public class NewBuildingLayout extends JPanel {
    private BufferedImage image;
    private JButton fireSensorButton;
    private JButton breakInSensorButton;

    private NewBuildingLayout(){}
    private static NewBuildingLayout newBuildingLayout;
    public static NewBuildingLayout getNewBuildingLayout(){
        if (newBuildingLayout == null){
            newBuildingLayout = new NewBuildingLayout();
            newBuildingLayout.init();
        }
        return newBuildingLayout;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    private void init(){
        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/resources/officeLayout.png"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }

        initializeButtons();
        setLayout(null);
        setPositionOfButtons();
        //add action listeners
        registerActionListeners();

        addButtonsToPanel();
    }

    private void initializeButtons(){
        fireSensorButton = new JButton("FS");
        breakInSensorButton = new JButton("BS");
    }

    private void setPositionOfButtons(){
        fireSensorButton.setBounds(150, 300, 50,50);
        fireSensorButton.setForeground(Color.RED);
        breakInSensorButton.setBounds(250, 300, 50,50);
        breakInSensorButton.setForeground(Color.BLUE);
    }

    private void registerActionListeners(){}

    private void addButtonsToPanel(){
        add(fireSensorButton);
        add(breakInSensorButton);
    }


}
