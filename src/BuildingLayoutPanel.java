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
    public BuildingLayoutPanel(){
        setPreferredSize(new Dimension(350, 350));
        try{
            image = ImageIO.read(new File("src/buildingLayout.jpg"));
        } catch (IOException e) {
            System.out.println("Can't open the image");
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
