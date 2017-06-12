package main.menuPanels;

import main.filemanagers.PasswordFileManager;
import main.CheckIdentityFrame;
import main.DisplayPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu panel UI.
 *
 * Created by manhongren on 6/1/17.
 */
public class MenuPanel extends JPanel {

    private final JButton personalInfoBtn;
    private final JButton setPasswordBtn;
    private final JButton setSensorBtn;
    private final JButton scheduleTimeBtn;
    private final JButton monthlyFeeBtn;

    public MenuPanel(){

        personalInfoBtn = new JButton("Set Personal Information");
        setPasswordBtn = new JButton("Set Password");
        setSensorBtn = new JButton("Schedule Sensor");
        scheduleTimeBtn = new JButton("Schedule Time");
        monthlyFeeBtn = new JButton("Monthly Fee");

        setLayout(new GridLayout(0, 1));

        addActionListeners();
        addButtonsToPanel();
    }

    private void addButtonsToPanel(){
        add(personalInfoBtn);
        add(setPasswordBtn);
        add(setSensorBtn);
        add(scheduleTimeBtn);
        add(monthlyFeeBtn);
    }

    private void addActionListeners(){
        personalInfoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enter password first to modify
                if (PasswordFileManager.getFileManager().isPasswordSet()){
                    CheckIdentityFrame checkIdentityFrame = new CheckIdentityFrame("coverPanel");
                    checkIdentityFrame.setVisible(true);
                } else {
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "coverPanel");
                }

            }
        });

        setPasswordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enter password first to modify
                if (PasswordFileManager.getFileManager().isPasswordSet()){
                    CheckIdentityFrame checkIdentityFrame = new CheckIdentityFrame("passwordPanel");
                    checkIdentityFrame.setVisible(true);
                } else{
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "passwordPanel");
                }
            }
        });

        setSensorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enter password first to modify
                if (PasswordFileManager.getFileManager().isPasswordSet()){
                    CheckIdentityFrame checkIdentityFrame = new CheckIdentityFrame("scheduleSensorPanel");
                    checkIdentityFrame.setVisible(true);
                } else {
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "scheduleSensorPanel");
                }
            }
        });

        scheduleTimeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enter password first to modify
                if (PasswordFileManager.getFileManager().isPasswordSet()){
                    CheckIdentityFrame checkIdentityFrame = new CheckIdentityFrame("scheduleTimePanel");
                    checkIdentityFrame.setVisible(true);
                } else {
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "scheduleTimePanel");
                }
            }
        });

        monthlyFeeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //enter password first to modify
                if (PasswordFileManager.getFileManager().isPasswordSet()){
                    CheckIdentityFrame checkIdentityFrame = new CheckIdentityFrame("monthlyFeePanel");
                    checkIdentityFrame.setVisible(true);
                } else {
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "monthlyFeePanel");
                }
            }
        });
    }
}
