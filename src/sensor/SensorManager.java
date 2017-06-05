package sensor;

import fileManagers.SensorInfoFileManager;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by manhongren on 6/3/17.
 */
public class SensorManager {
    private Map<JButton, Sensor> buttonToSensorMap;
    private Map<String, JButton> sensorIdToButtonMap;
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
        sensorIdToButtonMap = new HashMap<>();
    }

    public void addToSensorButtonMap(JButton button, Sensor sensor){
        this.buttonToSensorMap.put(button, sensor);
        this.sensorIdToButtonMap.put(sensor.getSensorId(), button);
    }

    public Sensor getSensorFromButton(JButton button){
        return buttonToSensorMap.get(button);
    }

    public JButton getButtonFromSensorId(String sensorId){
        return sensorIdToButtonMap.get(sensorId);
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

    public void updateButtonState(){
        String fileStr = SensorInfoFileManager.getFileManager().readFromFile();
        String[] strArr = fileStr.split(System.lineSeparator());
        for (String innerStr : strArr){
            String[] innerStrArr = innerStr.split(",");
            String statusStr = innerStrArr[2];
            String sensorId = innerStrArr[0].split(":")[1];
            JButton button = SensorManager.getInstance().getButtonFromSensorId(sensorId);
            if (statusStr.split(":")[1].equals("true")) {
                //set the specific Check box checked
                button.setBackground(Color.GREEN);
                button.setOpaque(true);
            } else {
                button.setBackground(Color.WHITE);
                button.setOpaque(true);
            }
        }
    }
}
