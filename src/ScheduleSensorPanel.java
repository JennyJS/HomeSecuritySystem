import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by manhongren on 6/3/17.
 */
public class ScheduleSensorPanel extends JPanel {
    private BufferedImage image;
    private JCheckBox breakInCheckBox;
    private JCheckBox breakInCheckBox2;
    private JCheckBox fireCheckBox1;
    private JCheckBox fireCheckBox2;
    private JLabel scheduleSensorLabel;

    public ScheduleSensorPanel(){

        setPreferredSize(new Dimension(350, 700));
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
        breakInCheckBox = new JCheckBox();
        breakInCheckBox2 = new JCheckBox();
        fireCheckBox1 = new JCheckBox();
        fireCheckBox2 = new JCheckBox();
        scheduleSensorLabel = new JLabel("Check the sensor you want to turn on: ");
        scheduleSensorLabel.setFont(new Font("Serif", Font.BOLD, 16));
//        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
//        scheduleSensorLabel.setBorder(border);
    }

    private void setPositionOfButtons(){
        fireCheckBox1.setBounds(30, 50, 50, 50);
        fireCheckBox2.setBounds(130, 20, 50, 50);
        breakInCheckBox.setBounds(130, 260, 50,50);
        breakInCheckBox2.setBounds(250, 210, 50,50);
        scheduleSensorLabel.setBounds(0, 0, 300, 50);
    }

    private void addButtonsToPanel() {
        add(scheduleSensorLabel);
        add(breakInCheckBox);
        add(breakInCheckBox2);
        add(fireCheckBox1);
        add(fireCheckBox2);
    }
}
