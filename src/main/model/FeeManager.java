package main.fileManagers;

import main.sensor.Sensor;
import main.sensor.SensorManager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class records fee into file and notifies listeners when fee has change.
 *
 * Created by manhongren on 6/7/17.
 */
public class FeeManager {

    /**
     * Listener that will get notified when fee file has changed.
     */
    public interface OnFeeFileChangeListener {
        void onFeeFileChange(int fireTriggered, int breakInTriggered, int fireInstalled, int breakInInstalled);
    }

    private static FeeManager feeManager;

    private final File file;
    private final Set<OnFeeFileChangeListener> onFeeFileChangeListeners;

    private int fireSensorTriggeredCount;
    private int breakInSensorTriggeredCount;
    private int fireSensorInstalledCount;
    private int breakInSensorInstalledCount;

    public static FeeManager getFeeManager() {
        if (feeManager == null){
            feeManager = new FeeManager();
        }
        return feeManager;
    }

    private FeeManager(){
        onFeeFileChangeListeners = new HashSet<>();
        file = new File("feeInfo.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        syncFromFile();
    }

    /**
     * Register {@link OnFeeFileChangeListener}.
     */
    public void registerOnFeeChangeListener(OnFeeFileChangeListener listener){
        onFeeFileChangeListeners.add(listener);
    }

    /**
     * Notify all registered OnFeeFileChangeListeners that fee has changed.
     */
    public void notifyFeeFileChange() {
        for (OnFeeFileChangeListener listener : onFeeFileChangeListeners){
            listener.onFeeFileChange(
                    fireSensorTriggeredCount,
                    breakInSensorTriggeredCount,
                    fireSensorInstalledCount,
                    breakInSensorInstalledCount);
        }
    }

    /**
     * Read fee file and update memory. Also notify listeners with updated fee info.
     */
    public void syncFromFile(){
        syncSensorTriggeredCountFromFile();
        syncInstalledSensorCountFromFile();
        notifyFeeFileChange();
    }

    public void addFeeEntryToFile(String feeEntry){
        try {
            FileWriter fw = new FileWriter(file,true); // false means not to append, to overwrite
            fw.append(feeEntry);
            fw.append('\n');
            fw.close();
            syncFromFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Read from file and update {@link breakInSensorTriggeredCount} and {@link fireSensorTriggeredCount}.
     */
    private void syncSensorTriggeredCountFromFile(){
        fireSensorTriggeredCount = 0;
        breakInSensorTriggeredCount = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] eachLine = line.split(",");
                for (Sensor s : SensorManager.getInstance().getSensors()){
                    if (s.getSensorId().equals(eachLine[0])){
                        if (s.getType() == Sensor.Type.FIRE){
                            fireSensorTriggeredCount++;
                        } else if (s.getType() == Sensor.Type.BREAK_IN){
                            breakInSensorTriggeredCount++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read from file and update {@link fireSensorInstalledCount} and {@link breakInSensorInstalledCount}.
     */
    private void syncInstalledSensorCountFromFile(){
        fireSensorInstalledCount = 0;
        breakInSensorInstalledCount = 0;
        for (Sensor s : SensorManager.getInstance().getSensors()){
            if (s.getType() == Sensor.Type.FIRE){
                fireSensorInstalledCount++;
            } else if (s.getType() == Sensor.Type.BREAK_IN){
                breakInSensorInstalledCount++;
            }
        }
    }

    public int getFireSensorTriggeredCount() {
        return fireSensorTriggeredCount;
    }

    public int getBreakInSensorTriggeredCount() {
        return breakInSensorTriggeredCount;
    }

    public int getFireSensorInstalledCount() {
        return fireSensorInstalledCount;
    }

    public int getBreakInSensorInstalledCount() {
        return breakInSensorInstalledCount;
    }
}
