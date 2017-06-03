import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by manhongren on 6/1/17.
 */
public class BuildingLayoutPanel extends JPanel {
    private BufferedImage image;
    private JButton breakInSensorBtn;
    private JButton breakInSensorBtn2;
    private JButton fireSensor1;
    private JButton fireSensor2;
    public BuildingLayoutPanel(){
        setPreferredSize(new Dimension(350, 400));
        try{
            image = ImageIO.read(new File("src/buildingLayout.jpg"));
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
//        ImageIcon icon = new ImageIcon("src/Fire.png");
//        fireSensor1 = new JButton(icon);
//        fireSensor2 = new JButton(icon);
        fireSensor1 = new JButton("FS");
        fireSensor1.setForeground(Color.RED);
        fireSensor2 = new JButton("FS");
        fireSensor2.setForeground(Color.RED);

    }

    private void setPositionOfButtons(){
        fireSensor1.setBounds(30, 50, 50, 50);
        fireSensor2.setBounds(130, 20, 50, 50);
        breakInSensorBtn.setBounds(120, 260, 50,50);
        breakInSensorBtn2.setBounds(250, 200, 50,50);
    }

    private void addButtonsToPanel() {
        add(breakInSensorBtn);
        add(breakInSensorBtn2);
        add(fireSensor1);
        add(fireSensor2);
    }

}
