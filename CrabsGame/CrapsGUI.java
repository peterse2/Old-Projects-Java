package Projects.P3;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class GUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
 public class CrapsGUI extends JPanel{
    
   /** Maintian 2 die */
    private  GVdie guiDie1;
    private GVdie guiDie2;
   
   /** Craps object */
    private Craps game;
   
   /** Jlabel for game message, current point, and total*/
    private JLabel gameMesLabel, pointLabel, totalLabel; 
   
   /** Jbutton to come out and roll */
    JButton comeOutButton, rollButton;
  
    
   /***************************************************
    Constructor: Sets up the GUI
    ***************************************************/
    public CrapsGUI() {
        
        //Setting layout 
        setLayout (new BorderLayout());
        
        //Initialize the GUI components
        game = new Craps();
        
        gameMesLabel = new JLabel (game.getMessage());
        pointLabel = new JLabel ("Point: " + game.getPoint());
        totalLabel = new JLabel ("Credit: " + game.getCredits());
       
        comeOutButton = new JButton("Come Out");
        rollButton = new JButton("Roll");
        
        //message.setTest(game.getMessage());
        guiDie1 = game.getDie(1);
        guiDie2 = game.getDie(2);
        
        //Register the action listener
        ButtonListener listener = new ButtonListener();
        rollButton.addActionListener(listener);
        comeOutButton.addActionListener(listener); 
        
        //Create panel for message
        JPanel  messagePanel = new JPanel();
        messagePanel.add(gameMesLabel);
                
        //Creating panell for the dice
        JPanel dicePanel = new JPanel();
        dicePanel.add(guiDie1);
        dicePanel.add(guiDie2);
        
        //Create panel for Credits and button
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(pointLabel);
        bottomPanel.add(totalLabel);
        bottomPanel.add(comeOutButton);
        bottomPanel.add(rollButton);
        
        //Adding all the panels to the GUI and set location
        add(messagePanel, BorderLayout.NORTH);
        add(dicePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    
   /***************************************************
    Action Listener: Button Listener
    ***************************************************/
    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            
            //Action for if one of the buttons are pushed
            if (event.getSource() == comeOutButton) {
                game.comeOut();
            }else{
                game.roll();
            }
            
            //Enable and disable buttons
            if(game.okToRoll()){
                comeOutButton.setEnabled(false);
                rollButton.setEnabled(true);
            }else{
                comeOutButton.setEnabled(true);
                rollButton.setEnabled(false);
            }
            
            pointLabel.setText("Point: " + game.getPoint());
            totalLabel.setText("Credit: " + game.getCredits());
            gameMesLabel.setText(game.getMessage());
        }
    }
    
    
   /***************************************************
    Main: Main Method
    ***************************************************/
    public static void main (String[] args){
        JFrame frame = new JFrame ("Craps Game");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new CrapsGUI());
        
        frame.pack();
        frame.setVisible(true);
    }
}
