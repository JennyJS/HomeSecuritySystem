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
    private JPanel menuPanel;
    private JPanel passwordPanel;
    private JPanel scheduleTimePanel;
    private JPanel scheduleSensorPanel;
    private JPanel newBuildingPanel;
    private JPanel monthlyFeePanel;
    private JPanel coverPanel;


    private DisplayPanel(){
        setLayout(cards);
        initiatePanels();
        addPanels();
    }

    private void initiatePanels(){
        menuPanel = new MenuPanel();
        coverPanel = new PersonalInfoPanel();
        passwordPanel = new PasswordPanel();
        scheduleTimePanel = new ScheduleTimePanel();
        scheduleSensorPanel = new ScheduleSensorPanel();
        newBuildingPanel = new SensorPanel(true, true);
        monthlyFeePanel = new MonthlyFeePanel();
    }

    private void addPanels(){
        add(newBuildingPanel, "newBuildingLayoutPanel");
        add(menuPanel, "menuPanel");
        add(coverPanel, "coverPanel");
        add(passwordPanel, "passwordPanel");
        add(scheduleTimePanel, "scheduleTimePanel");
        add(scheduleSensorPanel, "scheduleSensorPanel");
        add(monthlyFeePanel, "monthlyFeePanel");
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
