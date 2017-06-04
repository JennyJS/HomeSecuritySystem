package sensor;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manhongren on 6/3/17.
 */
public class SensorManager {
    private Map<JButton, Sensor> sensorToButtonMap;
    private Map<JCheckBox, JButton> checkBoxToButtonMap;
    private static SensorManager sensorManager;
    private SensorManager(){}
    public static SensorManager getInstance(){
        if (sensorManager == null){
            sensorManager = new SensorManager();
            sensorManager.init();
        }
        return sensorManager;
    }
    private void init() {
        sensorToButtonMap =  new HashMap<>();
        checkBoxToButtonMap = new HashMap<>();
    }

    public void addToSensorToButtonMap(JButton button, Sensor sensor){
        this.sensorToButtonMap.put(button, sensor);
    }

    public Sensor getSensorFromButton(JButton button){
        return sensorToButtonMap.get(button);
    }

    public void addToCheckBoxToButtonMap(JCheckBox checkBox, JButton button){
        checkBoxToButtonMap.put(checkBox, button);
    }

    public JButton getButtonFromCheckBox(JCheckBox checkBox){
        return checkBoxToButtonMap.get(checkBox);
    }
}
