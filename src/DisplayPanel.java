import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/1/17.
 */
public class DisplayPanel extends JPanel {
    private CardLayout cards = new CardLayout();
    private static DisplayPanel displayPanel;
    private DisplayPanel(){
        //cards = new CardLayout();
        setLayout(cards);
        JPanel buildingPanel = new BuildingLayoutPanel();
        JPanel menuPanel = new MenuPanel();
        add(buildingPanel);
        add(menuPanel);
    }

    public static DisplayPanel getDisplayPanel(){
        if (displayPanel == null){
            displayPanel = new DisplayPanel();
        }
        return displayPanel;
    }

    public CardLayout getCards(){
        return cards;
    }
}
