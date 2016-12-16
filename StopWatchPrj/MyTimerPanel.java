package package1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**********************************************************************
 @author Emily Peterson
 @version 9/09/2014
 *********************************************************************/
@SuppressWarnings("serial")
public class MyTimerPanel extends JPanel {
	
	/** The Main Frame */
	private JFrame guiJF;
	
	/** Start Button */
	private JButton startJB;
	
	/** Stop Button */
	private JButton stopJB;
	
	/** Reset Button */
	private JButton resetJB;
	
	/** Start Label */
	private JLabel labelJL;
	
	/** Panel for the Buttons */
	private JPanel buttonJP;
	
	/** Area to display the time */
	private JTextField timeTF;
	
	/** A StopWatch Object*/
	private StopWatch stopWatchTimer;
	
	/** */
	private Timer javaTimer;
	
	/** */
	private TimmerListener timer;
	
	
	/*******************************************************************
	 Constructor: Sets up the GUI
	*******************************************************************/
	public MyTimerPanel(){
		guiJF = new JFrame("Timer");
        guiJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        timer = new TimmerListener();
		stopWatchTimer = new StopWatch(0,0,0);
		javaTimer = new Timer(1000, timer);
		//javaTimer.start();
		
		//Defining the Label
		labelJL = new JLabel("Push a Button");
		
		//Defining Buttons
		startJB = new JButton("Start");
		stopJB = new JButton("Stop");
		resetJB = new JButton("Reset");
		
		//Creating and Adding the Buttons to it
		buttonJP = new JPanel();
		buttonJP.setPreferredSize(new Dimension (200, 40));
		buttonJP.add(startJB);
		buttonJP.add(stopJB);
		buttonJP.add(resetJB);
		buttonJP.add(labelJL);
		
		//Creating the TextField
		timeTF = new JTextField(stopWatchTimer.toString());
		
		//Adding panels to frame and displaying it
		guiJF.setLayout(new BorderLayout());
		guiJF.add(BorderLayout.NORTH, buttonJP);
		guiJF.add(BorderLayout.CENTER, timeTF);
        guiJF.setVisible(true);
        
		startJB.addActionListener(timer);
		stopJB.addActionListener(timer);
		resetJB.addActionListener(timer);
		}
	
	
	/*******************************************************************
    Respond to menu selections and button clicks

    @param e the button or menu item that was selected or button that 
    was clicked
    *******************************************************************/
    private class TimmerListener implements ActionListener{
       public void actionPerformed(ActionEvent e){
    	   if(e.getSource() == startJB){
    		   javaTimer.start();
    		   stopWatchTimer.add(1);
    		   timeTF = new JTextField(stopWatchTimer.toString());
    	   }
    	   if (e.getSource() == stopJB){
    		   javaTimer.stop();
    	   }
    	   if (e.getSource() == resetJB){
    		   javaTimer = new Timer(1000, timer);
    		   stopWatchTimer = new StopWatch(0,0,0);
    	   }
    	   
       }
    }
       
         public static void main(String args[]) {
           new MyTimerPanel();
         }
//           ActionListener actionListener = new ActionListener() {
//             public void actionPerformed(ActionEvent actionEvent) {
//                 
//             }
//           };
//           Timer timer = new Timer(500, actionListener);
//           timer.start();
//         }
}


