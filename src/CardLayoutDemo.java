// Java core packages
import java.awt.*;
import java.awt.event.*;
// Java extension packages
import javax.swing.*;
import javax.swing.border.*;

/* CardLayout class helps you manage two or more components (JPanel instances, for example) 
 * that share the same display space.
 *  Conceptually, each component a CardLayout manages is like a playing card or 
 *  trading card in a stack, where only the top card is visible at any time. 
 *  You can choose the card that's showing in any of the following ways: 
 *   By asking for either the first or last card, in the order they were added to the 
 *   container. 
 *   By flipping through the deck backwards or forwards. 
 *   By specifying a card with a specific name.
 * 
 */
class CardLayoutExample {
    JFrame guiFrame;
    CardLayout cards;
    JPanel buttonPanel;
    JPanel cardPanel;
    JPanel firstCard;
    JPanel secondCard;
    
    public CardLayoutExample()
    { 
        guiFrame = new JFrame();
        
        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        guiFrame.setTitle("CardLayout Example");
        guiFrame.setSize(400,300);
      
        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);
        guiFrame.setLayout(new BorderLayout());
        
        //creating a border to highlight the JPanel areas
        Border outline = BorderFactory.createLineBorder(Color.black);
        
        
        // This panel will contain one button to enable you
        // to switch thro' the cards
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(outline);
        JButton switchCardsBtn = new JButton("Switch Card");
        switchCardsBtn.setActionCommand("Switch Card");
        switchCardsBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
            	//navigate to the next component
                cards.next(cardPanel);
            }
        });
        buttonPanel.add(switchCardsBtn);       
        guiFrame.add(buttonPanel,BorderLayout.NORTH);
       
        
        // Create Panels for switching
        cards = new CardLayout();
        cardPanel = new JPanel();
        // set the card layout
        cardPanel.setLayout(cards);
        cards.show(cardPanel, "Cities");
        
        JPanel firstCard = new JPanel();
        firstCard.setBackground(Color.GREEN);
        addButton(firstCard, "London");
        addButton(firstCard, "New York");
        addButton(firstCard, "San Fransisco");
        
        JPanel secondCard = new JPanel();
        secondCard.setBackground(Color.BLUE);
        addButton(secondCard, "N.America");
        addButton(secondCard, "Asia");
        addButton(secondCard, "Europe");
        
        cardPanel.add(firstCard, "Cities");
        cardPanel.add(secondCard, "Continents");
        
        guiFrame.add(buttonPanel,BorderLayout.NORTH);
        guiFrame.add(cardPanel,BorderLayout.CENTER);
        guiFrame.setVisible(true);
    }
    
    //All the buttons are following the same pattern
    //so create them all in one place.
    private void addButton(Container parent, String name)
    {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        parent.add(but);
    }
}

public class CardLayoutDemo {
	public static void main(String[] args) {    
                new CardLayoutExample();                   
   }
}
