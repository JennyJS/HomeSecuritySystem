package sensor;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Created by manhongren on 6/3/17.
 */
public class Sensor {

    private static String SEPARATOR = ",";
    private static int BUTTON_SIZE = 100;

    private final String id;
    private final Type type;
    private final int x;
    private final int y;

    private boolean isOn;

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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public JButton generateButton() {
        JButton button = new JButton();
        button.setBounds(x, y, BUTTON_SIZE, BUTTON_SIZE);
        button.setBackground(isSensorOn() ? Color.GREEN : Color.WHITE);
        button.setText(type.name());
        button.setOpaque(true);
        return button;
    }

    public JCheckBox generateCheckBox() {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(x, y, 50, 50);
        return checkBox;
    }

    @Override
    public String toString(){
        return id + SEPARATOR + type + SEPARATOR + isOn + SEPARATOR + x + SEPARATOR + y;
    }

    public static Sensor fromString(String str) {
        String[] elements = str.split(SEPARATOR);
        String id = elements[0];
        String type = elements[1];
        String status = elements[2];
        int x = Integer.parseInt(elements[3]);
        int y = Integer.parseInt(elements[4]);

        return new Sensor(id, status.equals("true"), Sensor.Type.fromString(type), x, y);
    }
}
