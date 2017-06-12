package main.filemanagers;

import java.io.*;

/**
 * This class provides api to save and read password. Note that no encryption is used.
 *
 * Created by manhongren on 6/5/17.
 */
public class PasswordFileManager {

    private static PasswordFileManager sharedInstance;

    private final File file;

    public static PasswordFileManager getFileManager() {
        if (sharedInstance == null) {
            sharedInstance = new PasswordFileManager();
        }
        return sharedInstance;
    }

    private PasswordFileManager() {
        file = new File("password.txt");
    }

    /**
     * Save password to file. Note that no encryption is used.
     */
    public void savePassword(String password) {
        try {
            FileWriter fw = new FileWriter(file, true); // true is to append, false is to overwrite
            fw.append(password + '\n');
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Return saved password.
     */
    public String getPassword() {
        String lastLine = "";
        String sCurrentLine;

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(this.file.getName()));
            while ((sCurrentLine = br.readLine()) != null) {
                lastLine = sCurrentLine;
            }
        } catch (IOException e) {
            System.out.println("Password hasn't been set");
        }
        return lastLine;
    }

    public boolean isPasswordSet(){
        return file.exists();
    }
}