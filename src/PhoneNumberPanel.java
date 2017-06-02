import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 6/1/17.
 */
public class PhoneNumberPanel extends JPanel {
    private JButton enterBtn;
    private JButton doneBtn;
    private JTextField phoneNumberTextField;
    public PhoneNumberPanel(){
        initiateBtns();
        //TODO:Add action Listeners
        setLayout(new GridLayout(0, 1));
        add(phoneNumberTextField);
        add(enterBtn);
        add(doneBtn);
    }

    private void initiateBtns(){
        enterBtn = new JButton("Enter");
        doneBtn = new JButton("Done");
        phoneNumberTextField = new JTextField();
    }

}

