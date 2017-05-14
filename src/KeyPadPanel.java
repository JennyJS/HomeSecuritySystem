import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by manhongren on 5/13/17.
 */
public class KeyPadPanel extends JFrame implements ActionListener {
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
    private JButton bm;
    private JButton bd;

    private JButton offButton;
    private JButton awayButton;
    private JButton onButton;
    private JButton panicButton;

    private JTextField textField;

    private JButton f1Btn;
    private JButton f2Btn;
    private JButton b1Btn;
    private JButton b2Btn;

    public KeyPadPanel(){
       // Container container = getContentPane();
        JFrame jFrame = new JFrame();
        JPanel keyPadPanel = new JPanel();
        keyPadPanel.setLayout(new BorderLayout());

        JPanel textPanel = new JPanel();
        textField = new JTextField();
        textField.setColumns(20);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 100));
        textPanel.add(textField);
        keyPadPanel.add(textPanel, BorderLayout.NORTH);

        JPanel keys = new JPanel();
        keys.setLayout(new GridLayout(5,3));
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bm = new JButton("menu");
        b0 = new JButton("0");
        bd = new JButton("delete");
        offButton = new JButton("Off");
        awayButton = new JButton("Away");
        onButton = new JButton("On");
        panicButton = new JButton("Panic");
        keys.add(offButton);
        keys.add(onButton);
        keys.add(awayButton);
        keys.add(b1);
        keys.add(b2);
        keys.add(b3);
        keys.add(b4);
        keys.add(b5);
        keys.add(b6);
        keys.add(b7);
        keys.add(b8);
        keys.add(b9);
        keys.add(bm);
        keys.add(b0);
        keys.add(bd);
        keyPadPanel.add(keys, BorderLayout.CENTER);

        JPanel panicPanel = new JPanel();
        panicPanel.add(panicButton);
        keyPadPanel.add(panicPanel, BorderLayout.SOUTH);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        bm.addActionListener(this);
        b0.addActionListener(this);
        bd.addActionListener(this);
        offButton.addActionListener(this);
        onButton.addActionListener(this);
        panicButton.addActionListener(this);


        JPanel sensorPanel = new JPanel();
        f1Btn = new JButton("Fire Sensor 1");
        f2Btn = new JButton("Fire Sensor 2");
        b1Btn = new JButton("Break-in Sensor 1");
        b2Btn = new JButton("Break-in Sensor 2");
        sensorPanel.setLayout(new GridLayout(2,2));
        sensorPanel.add(f1Btn);
        sensorPanel.add(f2Btn);
        sensorPanel.add(b1Btn);
        sensorPanel.add(b2Btn);
        jFrame.add(sensorPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);

        keyPadPanel.add(sensorPanel, BorderLayout.EAST);


        jFrame.add(keyPadPanel);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args){
        KeyPadPanel keyPadPanel = new KeyPadPanel();
        keyPadPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
