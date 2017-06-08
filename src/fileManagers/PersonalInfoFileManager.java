package fileManagers;

import java.io.File;

/**
 * Created by manhongren on 6/7/17.
 */
public class PersonalInfoFileManager {
    private File file;
    private static PersonalInfoFileManager personalInfoFileManager;
    public static PersonalInfoFileManager getPersonalInfoFileManager(){
        if (personalInfoFileManager == null){
            personalInfoFileManager = new PersonalInfoFileManager();
        }
        return personalInfoFileManager;
    }

}
