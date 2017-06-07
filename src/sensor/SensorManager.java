package sensor;

import com.apple.eio.FileManager;
import fileManagers.SensorInfoFileManager;

import javax.swing.*;
import java.awt.*;
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

    public void removeOnSensorChangeListener(OnSensorChangeListener listener){
        onSensorChangeListeners.remove(listener);
    }

    public void addSensor(Sensor sensor) {
        sensors.add(sensor);
        try {
            syncToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        notifySensorChange();
    }

    public Set<Sensor> getSensors() {
        return Collections.unmodifiableSet(sensors);
    }

    public void syncToFile() throws IOException {
        // iterate all sensors
        SensorInfoFileManager.getFileManager().updateSensors(sensors);
    }

    public void setAllSensors(boolean isOn) {
        for (Sensor s : sensors) {
            s.setSensorOn(isOn);
        }

        try {
            syncToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        notifySensorChange();
    }

    public void notifySensorChange() {
        for (OnSensorChangeListener listener : onSensorChangeListeners){
            listener.onSensorChange(this.sensors);
        }
    }

    private void syncFromFile() throws IOException {
        sensors = SensorInfoFileManager.getFileManager().readFromFile();
    }
}
