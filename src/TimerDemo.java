
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class CounterDisplay{
   
   private JFrame frame;
   private JButton displayBtn;
   private JTextField displayField;
   private Timer timer;
   Integer count = 0;
 
   public CounterDisplay(){
      
      frame = new JFrame("Timer Demo");
      // get content pane
      Container container = frame.getContentPane();
	  container.setLayout( new FlowLayout() );
	  
	  // create a textfield
	  displayField = new JTextField(10);	 
      // create buttons
      displayBtn = new JButton( "Display Count" );
        
      // add them all to the container
      container.add( displayField );	     
      container.add( displayBtn );	    
    
     
      // add event handlers to the button
      displayBtn.addActionListener( new ActionListener(){	  
    	  public void actionPerformed(ActionEvent e){
    		// start the timer
      		timer.start();
      	  }
        });
           
      ActionListener taskPerformer = new ActionListener (){
    	  public void actionPerformed(ActionEvent e){
    		if (count.intValue() >= 5)  timer.stop();  		
    		  displayField.setText(count.toString());   		 
    		  count = count + 1;
    	  }
      };
      
      // add the task to the timer
      timer = new Timer(1000,taskPerformer);
      
      frame.setSize(200,200);
      frame.setLocationRelativeTo(null);
      frame.setVisible( true );
   }
}
public class TimerDemo {
	public static void main( String [] args ){
	     CounterDisplay application = new CounterDisplay();     
	}
}
