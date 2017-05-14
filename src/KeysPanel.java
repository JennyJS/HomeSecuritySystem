import javax.swing.*;
import java.awt.*;

/**
 * Created by manhongren on 5/13/17.
 */
public class KeysPanel extends JPanel {

    public KeysPanel(){
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();

        p.setLayout (new BorderLayout());

    //    p.addActionListener (new DialListener());

        JLabel title = new JLabel ("");

        p.add (title, BorderLayout.NORTH);



        JButton clear = new JButton ("clear");

        p.add (clear, BorderLayout.EAST);





        JPanel keys = new JPanel();



        keys.setLayout (new GridLayout (5,3));



        keys.add (new JButton ("send"));

        keys.add (new JButton (""));

        keys.add (new JButton ("end"));

        keys.add (new JButton ("1"));

        keys.add (new JButton ("2"));

        keys.add (new JButton ("3"));

        keys.add (new JButton ("4"));

        keys.add (new JButton ("5"));

        keys.add (new JButton ("6"));

        keys.add (new JButton ("7"));

        keys.add (new JButton ("8"));

        keys.add (new JButton ("9"));

        keys.add (new JButton ("*"));

        keys.add (new JButton ("0"));

        keys.add (new JButton ("#"));





        p.add (keys, BorderLayout.CENTER);







        frame.add(p);

        frame.pack();

        frame.setVisible(true);
    }

    public static void main(String[] args){
        KeysPanel keys = new KeysPanel();
        //keys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
