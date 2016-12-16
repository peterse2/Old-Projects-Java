package Lab;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
/**
 * Write a description of class CarPaymentCalculator here.
 * 
 * @author Emily Peterson Jasmine   Bailey
 * @version (a version number or a date)
 */
public class CarPaymentCalculator extends JPanel
{
   /** TextFields */ 
    private JTextField principalText, interestText, monthsText;
    
   /** JLabels */ 
    private JLabel numLabel, principalLabel, interestLabel, monthlyLabel, calculateLabel;
    
   /** Calculate Button */
    private JButton calculate;
    
    
   /*****************************************************
    * Creates and displays the temperature converter GUI.
    ****************************************************/
    public static void main (String[] args) {
       JFrame frame = new JFrame ("Car Payment Calculator");
       frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
       
       CarPaymentCalculator panel = new CarPaymentCalculator ();
       
       frame.getContentPane().add(panel);
       frame.pack();
       frame.setVisible(true);
    }

  
   /***************************************************
    * Constructor: Stes up the main GUI components
    **************************************************/
    public CarPaymentCalculator(){
       
       //Displaying the feild that need to be assigned a int
       numLabel = new JLabel ("Number of Months: ");
       principalLabel = new JLabel ("Loan Amount: ");
       interestLabel = new JLabel ("Interest: ");
       monthlyLabel = new JLabel ("Payment: ");
     
       //Creating all the TextFields
       principalText = new JTextField (5);
       principalText.addActionListener (new CalculateListener());
       
       interestText = new JTextField (5);
       interestText.addActionListener (new CalculateListener());
       
       monthsText = new JTextField (5);
       monthsText.addActionListener (new CalculateListener());
       
       //Creating the calcuate button
       calculate = new JButton ("Calculate");
       calculate.addActionListener (new CalculateListener());
       
       
       //Displaying all of what was created
       add (numLabel);
       add (monthsText);
       
       add (principalLabel);
       add (principalText);
       
       add (interestLabel);
       add (interestText);
       
       add (calculate);
       add (monthlyLabel);
       
       //Window looks
       setPreferredSize (new Dimension(220, 150));
       setBackground (new Color(94, 0, 94));
    }
 
 
   /***************************************************
    * Constructor: Stes up the main GUI components
    **************************************************/
    private class CalculateListener implements ActionListener
    {
       /***************************************************
        * Constructor: Stes up the main GUI components
        **************************************************/
        public void actionPerformed (ActionEvent event)
        {
           int i, n;
           double p, m;
           
           String principal = principalText.getText();
           String interest = interestText.getText();
           String months = monthsText.getText();
        
           p = Integer.parseInt (principal);
           i = Integer.parseInt (interest);
           n = Integer.parseInt (months);
           
           Math.pow(1+i,n);
           m = p * (i*Math.pow(1+i,n)) / (Math.pow(1+i,n)-1);
      
           calculateLabel.setText (Double.toString (m));
        }
    }
}