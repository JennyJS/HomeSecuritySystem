package main;

import menuPanels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/1/17.
 */
public class DisplayPanel extends JPanel {
    private CardLayout cards = new CardLayout();
    private static DisplayPanel displayPanel;
//    private JPanel menuPanel = new MenuPanel();
//    private JPanel phoneNumberPanel = new PhoneNumberPanel();
//    private JPanel passwordPanel = new PasswordPanel();
//    private JPanel scheduleTimePanel = new ScheduleTimePanel();
//    private JPanel scheduleSensorPanel = new ScheduleSensorPanel();
//    private JPanel checkIdentityPanel = new CheckIdentityFrame();
    private JPanel menuPanel;
    private JPanel phoneNumberPanel;
    private JPanel passwordPanel;
    private JPanel scheduleTimePanel;
    private JPanel scheduleSensorPanel;
   // private JPanel checkIdentityPanel;

    private DisplayPanel(){
        //cards = new CardLayout();
        setLayout(cards);
        setBorder(BorderFactory.createEmptyBorder(8,8,12,8));
        initiatePanels();
        addPanels();
    }

    private void initiatePanels(){
        menuPanel = new MenuPanel();
        phoneNumberPanel = new PhoneNumberPanel();
        passwordPanel = new PasswordPanel();
        scheduleTimePanel = new ScheduleTimePanel();
        scheduleSensorPanel = new ScheduleSensorPanel();
      //  checkIdentityPanel = new CheckIdentityFrame();
    }

    private void addPanels(){
        add(BuildingLayoutPanel.getBuildingLayoutPanel(), "buildingLayoutPanel");
        add(menuPanel, "menuPanel");
        add(phoneNumberPanel, "phoneNumberPanel");
        add(passwordPanel, "passwordPanel");
        add(scheduleTimePanel, "scheduleTimePanel");
        add(scheduleSensorPanel, "scheduleSensorPanel");
      //  add(checkIdentityPanel, "checkIdentityPanel");
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
