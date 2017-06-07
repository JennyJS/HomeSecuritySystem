package fileManagers;

import sensor.Sensor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by manhongren on 6/7/17.
 */
public class FeeManager {
    private File file;

    private static FeeManager feeManager;

    public static FeeManager getFeeManager() throws IOException {
        if (feeManager == null){
            feeManager = new FeeManager();
        }
        return feeManager;
    }

    private FeeManager() throws IOException {
        file = new File("feeInfo.txt");
        file.createNewFile();
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
}
