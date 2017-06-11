package main.menuPanels;

import javax.swing.*;

/**
 * Provides current active {@link JTextField}.
 *
 * Created by manhongren on 6/3/17.
 */
public class ActiveTextField {

    private static ActiveTextField activeTextField;

    private JTextField currentTextField;

    private ActiveTextField(){}

    public static ActiveTextField getInstance(){
        if (activeTextField == null){
            activeTextField = new ActiveTextField();
        }
        return activeTextField;
    }

    /**
     * Set active {@link JTextField}.
     */
    public void setActiveTextField(JTextField currentTextField){
         this.currentTextField = currentTextField;
    }

    /**
     * Returns active {@link JTextField}.
     */
    public JTextField getActiveTextField(){
        return this.currentTextField;
    }
}
