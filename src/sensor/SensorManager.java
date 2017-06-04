package sensor;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manhongren on 6/3/17.
 */
public class SensorManager {
    private Map<JButton, Sensor> buttonToSensorMap;
    private Map<JCheckBox, JButton> checkBoxToButtonMap;
    private Map<String, JCheckBox> sensorIdToCheckBox;
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
        buttonToSensorMap =  new HashMap<>();
        checkBoxToButtonMap = new HashMap<>();
        sensorIdToCheckBox = new HashMap<>();
    }

    public void addToSensorToButtonMap(JButton button, Sensor sensor){
        this.buttonToSensorMap.put(button, sensor);
    }

    public Sensor getSensorFromButton(JButton button){
        return buttonToSensorMap.get(button);
    }

    public void addToCheckBoxToButtonMap(JCheckBox checkBox, JButton button){
        checkBoxToButtonMap.put(checkBox, button);
    }

    public JButton getButtonFromCheckBox(JCheckBox checkBox){
        return checkBoxToButtonMap.get(checkBox);
    }

    public void addToSensorIdToCheckBox(String sensorId, JCheckBox jCheckBox){
        sensorIdToCheckBox.put(sensorId, jCheckBox);
    }

    public JCheckBox getCheckBoxFromSensorId(String sensorId){
        return sensorIdToCheckBox.get(sensorId);
    }
}
