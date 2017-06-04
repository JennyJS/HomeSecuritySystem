import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 5/31/17.
 */
public class KeyPadPanel extends JPanel {
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton b0;
    private JButton offButton;
    private JButton awayButton;
    private JButton onButton;

    private JButton menuBtn;
    private JButton deleteBtn;
    private JButton enterBtn;
    private JButton panicButton;
    private JButton backBtn;
    private boolean isBuildingLayoutShown = true;

    private static KeyPadPanel keyPadPanel;
  //  private DisplayPanel displayPanel = new DisplayPanel();

    private KeyPadPanel(){
        //initialize keys
        initializeKeys();

        //Create the event handler

        setLayout(new GridLayout(6,3));

        // add action listeners
        addActionListeners();

        //add buttons to this panel
        addBtnToPanel();


    }

    public static KeyPadPanel getKeyPadPanel(){
        if (keyPadPanel == null){
            keyPadPanel = new KeyPadPanel();
        }
        return keyPadPanel;
    }

    private void initializeKeys(){
        offButton = new JButton("Off");
        onButton = new JButton("On");
        awayButton = new JButton("Away");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        menuBtn = new JButton("menu");
        b0 = new JButton("0");
        deleteBtn = new JButton("delete");
        enterBtn = new JButton("Enter");
        panicButton = new JButton("Panic");
        backBtn = new JButton("Back");
    }

    private void addBtnToPanel(){
        add(offButton);
        add(onButton);
        add(awayButton);
        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(menuBtn);
        add(b0);
        add(deleteBtn);
        add(enterBtn);
        add(panicButton);
        add(backBtn);
    }

    private void addActionListeners(){
        menuBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //switch only between buildingLayoutPanel and menuPanel

                if (isBuildingLayoutShown){
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "menuPanel");
                } else {
                    DisplayPanel.getDisplayPanel().getCards().show(DisplayPanel.getDisplayPanel(), "buildingLayoutPanel");
                }
                isBuildingLayoutShown = !isBuildingLayoutShown;
            }
        });
    }

}
