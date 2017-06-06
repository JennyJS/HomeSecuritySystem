package fileManagers;

import sensor.Sensor;

import java.io.*;

/**
 * Created by manhongren on 5/27/17. This Class is for managing files
 */
public class SensorInfoFileManager {
    private File file;
    /**
     * each line in a file is an entry
     */
    public static class Entry{
        String sensorId;
        Sensor.Type type;
        boolean isOn;
        public Entry(String sensorId,boolean isOn, Sensor.Type type){
            this.sensorId = sensorId;
            this.type = type;
            this.isOn = isOn;
        }
        @Override
        public String toString(){
            return "Sensor:" + sensorId + ", type of:" + type + ", isOn:" + isOn;
        }
    }

    private static SensorInfoFileManager fileManager;

    public static SensorInfoFileManager getFileManager(){
        if (fileManager == null){
            fileManager = new SensorInfoFileManager();
            fileManager.init();
        }
        return fileManager;
    }

    private void init() {
        this.file = new File("sensorInfo.txt");
    }

    public void addToFile(String str){
        try {
            FileWriter fw = new FileWriter(file,false); // false means not to append, to overwrite
            fw.append(str);
            System.out.println("Adding to file " + '\n' + str);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    //read from the entire file
    public String readFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null){
                if (line.length() != 0){
                    sb.append(line);
                    sb.append(System.lineSeparator());
                }
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getFileName(){
        return this.file.getName();
    }
}
