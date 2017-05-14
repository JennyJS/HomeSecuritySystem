import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 5/13/17.
 */

public class ControlPanel extends JFrame implements ActionListener {
    private JButton offButton;
    private JButton awayButton;
    private JButton onButton;
    private JButton panicButton;
    private JButton settingButton;

    public ControlPanel(){
        super("Control Panel");
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
        offButton = new JButton("Off");
        container.add(offButton);
        offButton.addActionListener(this);
        offButton.setActionCommand("Turn off the alarm");

        awayButton = new JButton("Away");
        container.add(awayButton);
        awayButton.addActionListener(this);
        awayButton.setActionCommand("You're leaving the building");

        onButton = new JButton("On");
        container.add(onButton);
        onButton.addActionListener(this);
        onButton.setActionCommand("Turn on the alarm");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null,  e.getActionCommand());
    }

    public static void main(String[] args){
        ControlPanel controlPanel = new ControlPanel();
        controlPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
