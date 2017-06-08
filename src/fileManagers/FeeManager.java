package fileManagers;

import sensor.Sensor;
import sensor.SensorManager;

import javax.swing.*;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manhongren on 6/7/17.
 */
public class FeeManager {
    private File file;
    public interface OnFeeFileChangeListener{
        void onFeeFileChange(int fireTriggered, int breakInTriggered, int fireInstalled, int breakInInstalled);
    }
    private Set<OnFeeFileChangeListener> onFeeFileChangeListeners;

    private int fireSensorTriggeredCount;
    private int breakInSensorTriggeredCount;
    private int fireSensorInstalledCount;
    private int breakInSensorInstalledCount;

    private static FeeManager feeManager;

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
        populateSensorTriggeredCount();
        countSensorInstalledCount();
    }


    public void addToFile(String str){
        try {
            FileWriter fw = new FileWriter(file,true); // false means not to append, to overwrite
            fw.append(str);
            fw.append('\n');
            System.out.println("Adding to file " + '\n' + str);
            fw.close();
            populateSensorTriggeredCount();
            countSensorInstalledCount();
            notifyFeeFileChange();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void populateSensorTriggeredCount(){
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

    private void countSensorInstalledCount(){
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

    public boolean hasFireSensorDiscount(){
        return breakInSensorInstalledCount > 0;
    }

    public void registerOnFeeChangeListener(OnFeeFileChangeListener listener){
        onFeeFileChangeListeners.add(listener);
    }

    public void removeOnFeeChangeListener(OnFeeFileChangeListener listener){
        onFeeFileChangeListeners.remove(listener);
    }

    public void notifyFeeFileChange() {
        for (OnFeeFileChangeListener listener : onFeeFileChangeListeners){
            listener.onFeeFileChange(
                    fireSensorTriggeredCount,
                    breakInSensorTriggeredCount,
                    fireSensorInstalledCount,
                    breakInSensorInstalledCount);
        }
    }
}
