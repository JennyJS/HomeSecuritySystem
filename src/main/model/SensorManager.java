package main.sensor;


import main.fileManagers.FeeManager;
import main.fileManagers.SensorInfoFileManager;

import java.io.IOException;
import java.util.*;

/**
 * Created by manhongren on 6/3/17.
 */
public class SensorManager {

    public interface OnSensorChangeListener{
        void onSensorChange(Set<Sensor> sensors);
    }

    private Set<OnSensorChangeListener> onSensorChangeListeners;

    private static SensorManager sensorManager;

    private Set<Sensor> sensors;

    private SensorManager() throws IOException {
        onSensorChangeListeners = new HashSet<>();
        sensors = new HashSet<>();
        syncFromFile();
    }

    public static SensorManager getInstance(){
        if (sensorManager == null){
            try {
                sensorManager = new SensorManager();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sensorManager;
    }

    public void registerOnSensorChangeListener(OnSensorChangeListener listener){
        onSensorChangeListeners.add(listener);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        syncToFile();
        FeeManager.getFeeManager().syncFromFile();
        notifySensorChange();
    }

    public Set<Sensor> getSensors() {
        return Collections.unmodifiableSet(sensors);
    }

    public void syncToFile() {
        // iterate all sensors
        try {
            SensorInfoFileManager.getFileManager().syncSensorsToFile(sensors);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAllSensors(boolean isOn) {
        for (Sensor s : sensors) {
            s.setSensorOn(isOn);
        }

        syncToFile();

        notifySensorChange();
    }

    public void notifySensorChange() {
        for (OnSensorChangeListener listener : onSensorChangeListeners){
            listener.onSensorChange(this.sensors);
        }
    }

    private void syncFromFile() throws IOException {
        sensors = SensorInfoFileManager.getFileManager().getSensors();
    }
}
