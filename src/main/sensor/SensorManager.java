package main.sensor;


import main.filemanagers.FeeManager;
import main.filemanagers.SensorInfoFileManager;

import java.util.*;

/**
 * Manages sensors.
 *
 * Created by manhongren on 6/3/17.
 */
public class SensorManager {

    /**
     * Listener on sensor data changes.
     */
    public interface OnSensorChangeListener{
        void onSensorChange(Set<Sensor> sensors);
    }

    private static SensorManager sensorManager;

    private final Set<OnSensorChangeListener> onSensorChangeListeners;
    private final Set<Sensor> sensors;

    private SensorManager() {
        onSensorChangeListeners = new HashSet<>();
        sensors = new HashSet<>();
        syncFromFile();
    }

    public static SensorManager getInstance(){
        if (sensorManager == null){
            sensorManager = new SensorManager();
        }
        return sensorManager;
    }

    /**
     * Register {@link OnSensorChangeListener}.
     */
    public void registerOnSensorChangeListener(OnSensorChangeListener listener){
        onSensorChangeListeners.add(listener);
    }

    /**
     * Add a sensor, sync to file and notify changes.
     */
    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        syncToFile();
        FeeManager.getFeeManager().syncFromFile();
        notifySensorChange();
    }

    /**
     * Return an unmodifiable copy of all sensors.
     */
    public Set<Sensor> getSensors() {
        return Collections.unmodifiableSet(sensors);
    }

    /**
     * Save current sensors to file.
     */
    public void syncToFile() {
        SensorInfoFileManager.getFileManager().syncSensorsToFile(sensors);
    }

    /**
     * Turn on/off all sensors. Sync to file and notify changes.
     */
    public void setAllSensors(boolean isOn) {
        for (Sensor s : sensors) {
            s.setSensorOn(isOn);
        }

        syncToFile();
        notifySensorChange();
    }

    /**
     * Notify all {@link OnSensorChangeListener} of sensor changes.
     */
    public void notifySensorChange() {
        for (OnSensorChangeListener listener : onSensorChangeListeners){
            listener.onSensorChange(this.sensors);
        }
    }

    /**
     * Read sensors from file.
     */
    private void syncFromFile() {
        sensors.clear();
        sensors.addAll(SensorInfoFileManager.getFileManager().getSensors());
    }
}
