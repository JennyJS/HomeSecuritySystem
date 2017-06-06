package main;

import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.scene.control.RadioButton;

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
    private JLabel fireLabel;
    private JLabel breakInLabel;
    private JRadioButton fireRadioButton;
    private JRadioButton breakInRadioButton;

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


}
