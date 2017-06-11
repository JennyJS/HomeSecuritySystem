package main.model;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Manages writing reading sensor info to and from file.
 *
 * Created by manhongren on 5/27/17. This Class is for managing files
 */
public class SensorInfoFileManager {

    private File file;

    private static SensorInfoFileManager fileManager;

    public static SensorInfoFileManager getFileManager() {
        if (fileManager == null){
            fileManager = new SensorInfoFileManager();
        }
        return fileManager;
    }

    private SensorInfoFileManager() {
        file = new File("sensorInfo.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read file and return all sensors.
     */
    public Set<Sensor> getSensors(){
        Set<Sensor> sensors = new HashSet<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null){
                sensors.add(Sensor.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sensors;
    }

    /**
     * Save sensor to file.
     */
    public void syncSensorsToFile(Set<Sensor> sensors) {
        StringBuilder sb = new StringBuilder();
        for (Sensor s : sensors) {
            sb.append(s).append("\n");
        }

        try {
            FileWriter fw = new FileWriter(file,false); // false means not to append, to overwrite
            fw.append(sb.toString());
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
