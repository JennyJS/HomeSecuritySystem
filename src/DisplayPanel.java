import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/1/17.
 */
public class DisplayPanel extends JPanel {
    CardLayout cards;
    public DisplayPanel(){
        cards = new CardLayout();
        setLayout(cards);
        JPanel buildingPanel = new BuildingLayoutPanel();
        JPanel menuPanel = new MenuPanel();
        add(buildingPanel);
        add(menuPanel);
    }
}
