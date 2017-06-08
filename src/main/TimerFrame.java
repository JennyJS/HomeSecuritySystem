package main;

import sensor.SensorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

/**
 * Created by manhongren on 6/7/17.
 */
public class TimerFrame extends JFrame {
//    public static void main(String[] args){
//        JFrame frame = new JFrame("Testing");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(new TimerFrame());
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//
//    }
    private Timer timer;
    private long startTime = -1;
    private long duration = 10000;

    private JLabel label;

    public TimerFrame() {
        setLayout(new GridBagLayout());
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    timer.stop();
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss:SSS");
                label.setText(df.format(duration - clockTime));
            }
        });
        if (!timer.isRunning()) {
            startTime = -1;
            timer.start();
        }
        timer.setInitialDelay(0);
        label = new JLabel("...");
        add(label);
    }
}


