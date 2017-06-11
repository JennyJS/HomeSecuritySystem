package main.ui;

import main.ui.menuPanels.*;

import javax.swing.*;
import java.awt.*;

/**
 * Card layout.
 *
 * Created by manhongren on 6/1/17.
 */
public class DisplayPanel extends JPanel {

    private static DisplayPanel displayPanel;

    private final CardLayout cards = new CardLayout();
    private final JPanel menuPanel;
    private final JPanel passwordPanel;
    private final JPanel scheduleTimePanel;
    private final JPanel scheduleSensorPanel;
    private final JPanel newBuildingPanel;
    private final JPanel monthlyFeePanel;
    private final JPanel coverPanel;


    private DisplayPanel(){
        setLayout(cards);

        menuPanel = new MenuPanel();
        coverPanel = new PersonalInfoPanel();
        passwordPanel = new PasswordPanel();
        scheduleTimePanel = new ScheduleTimePanel();
        scheduleSensorPanel = new ScheduleSensorPanel();
        newBuildingPanel = new SensorPanel(true, true);
        monthlyFeePanel = new MonthlyFeePanel();

        addPanels();
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
