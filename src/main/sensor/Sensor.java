package main.sensor;


import main.filemanagers.FeeManager;
import main.PopupFrame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * Sensor Data.
 *
 * Created by manhongren on 6/3/17.
 */
public class Sensor {

    private static final String SEPARATOR = ",";
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int CHECKBOX_WIDTH = 25;
    private static final int CHECKBOX_HEIGHT = 20;
    private static final Font BUTTON_FONT = new Font("#GungSeo", Font.PLAIN, 25);

    private final String id;
    private final Type type;
    private final int x;
    private final int y;

    private boolean isOn;

    /**
     * Type of {@link Sensor}.
     */
    public enum Type{
        FIRE,
        BREAK_IN;

        public static Type fromString(String str) {
            for (Type t : values()) {
                if (t.name().equals(str)) {
                    return t;
                }
            }
            return null;
        }
    }

    public Sensor(boolean isOn, Type type, int x, int y){
        this(UUID.randomUUID().toString(), isOn, type, x, y);
    }

    private Sensor(String id, boolean isOn, Type type, int x, int y) {
        this.id = id;
        this.isOn = isOn;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public void setSensorOn(boolean isOn){
        this.isOn = isOn;
    }

    public String getSensorId(){
        return this.id;
    }

    public Type getType(){
        return this.type;
    }

    public boolean isSensorOn(){
        return isOn;
    }

    /**
     * Create a {@link JButton} from sensor.
     */
    public JButton generateButton() {
        JButton button = new JButton();
        button.setBounds(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        button.setBackground(isSensorOn() ? Color.GREEN : Color.WHITE);
        button.setText(type.name());
        button.setFont(BUTTON_FONT);
        button.setOpaque(true);
        if (type == Type.FIRE) {
            button.addActionListener(new FireButtonActionHandler());
        } else if (type == Type.BREAK_IN) {
            button.addActionListener(new BreakInButtonActionHandler());
        }
        return button;
    }

    /**
     * Create a {@link JCheckBox} from sensor.
     */
    public JCheckBox generateCheckBox() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(x, y, CHECKBOX_WIDTH, CHECKBOX_HEIGHT);
        checkBox.setSelected(isOn);
        checkBox.setEnabled(true);
        return checkBox;
    }

    @Override
    public String toString(){
        return id + SEPARATOR + type + SEPARATOR + isOn + SEPARATOR + x + SEPARATOR + y;
    }

    /**
     * Create sensor from string.
     */
    public static Sensor fromString(String str) {
        String[] elements = str.split(SEPARATOR);
        String id = elements[0];
        String type = elements[1];
        String status = elements[2];
        int x = Integer.parseInt(elements[3]);
        int y = Integer.parseInt(elements[4]);

        return new Sensor(id, status.equals("true"), Sensor.Type.fromString(type), x, y);
    }

    /**
     * ActionListener for sensor of type {@link Type#FIRE}.
     */
    private class FireButtonActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                if (isSensorOn()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(getSensorId()).append(",").append(new SimpleDateFormat("MMddyyyy_HH:mm:ss").format(Calendar.getInstance().getTime()));
                    FeeManager.getFeeManager().addFeeEntryToFile(sb.toString());
                    new PopupFrame("sprinkler.jpg");
                }
            }
        }
    }

    /**
     * ActionListener for sensor of type {@link Type#BREAK_IN}.
     */
    public class BreakInButtonActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source instanceof JButton) {
                if (isSensorOn()){
                    StringBuilder sb = new StringBuilder();
                    sb.append(getSensorId()).append(",").append(new SimpleDateFormat("MMddyyyy_HH:mm:ss").format(Calendar.getInstance().getTime()));
                    FeeManager.getFeeManager().addFeeEntryToFile(sb.toString());
                    new PopupFrame("burglar.jpeg");
                }
            }
        }
    }
}
