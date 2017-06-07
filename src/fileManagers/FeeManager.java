package fileManagers;

import sensor.Sensor;
import sensor.SensorManager;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manhongren on 6/7/17.
 */
public class FeeManager {
    private File file;


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
        file = new File("feeInfo.txt");
        populateSensorTriggeredCount();
        countSensorInstalledCount();

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addToFile(String str){
        try {
            FileWriter fw = new FileWriter(file,true); // false means not to append, to overwrite
            fw.append(str);
            fw.append('\n');
            System.out.println("Adding to file " + '\n' + str);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void populateSensorTriggeredCount(){
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
        for (Sensor s : SensorManager.getInstance().getSensors()){
            if (s.getType() == Sensor.Type.FIRE){
                fireSensorInstalledCount++;
            } else if (s.getType() == Sensor.Type.BREAK_IN){
                breakInSensorInstalledCount++;
            }
        }
    }

    //read from the entire file
    public Set<Sensor> readFromFile(){
        Set<Sensor> sensors = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                sensors.add(Sensor.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sensors;
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
}
