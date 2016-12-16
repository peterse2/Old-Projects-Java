package package1;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.io.*;
/***********************************************************************
 * GUI front end for Lottery Simulation 
 * 
 * @author Emily Peterson
 * @version 3-25-14
 **********************************************************************/
public class GUI extends JPanel{
    
    /**LotteryMaching Object */
    private LotteryMachine lm;
    
    /** JLabels */
    private JLabel priceLabel;
    private JLabel stLabel;
    private JLabel drawTicketLabel;
    private JLabel checkWinnerLabel;
    
    /** text fields */
    private JTextField priceTextField;
    private JTextField stTextField;
    
    /** Buttons */
    private JButton randomNumButton;
    private JButton pickNumButton;
    private JButton multiGamesButton;
    private JButton biggestButton;
    private JButton oldestButton;
    private JButton majorButton;
    
    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenu reportsMenu;
    private JMenuItem quitItem;
    private JMenuItem openItem; 
    private JMenuItem stateItem;
    private JMenuItem reportItem;

    public static void main(String arg[]){
        // the tradition five lines of code
        // normally place here are 
        // inserted throughout the construtor
        new GUI();

    }

    /*********************************************************************
     Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
     public GUI(){
        lm = new LotteryMachine();
        lm.readTickets("TicketInfo.txt");
        
        theGUI = new JFrame("Mega Million Lottery");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create the Results Area for the Center area
        results = new JTextArea(20,20);
        JScrollPane scrollPane = new JScrollPane(results);
        theGUI.add(BorderLayout.CENTER, scrollPane);
        
        //Defining JLabels
        priceLabel = new JLabel("Price $");
        stLabel = new JLabel("St");
        drawTicketLabel = new JLabel("Draw Ticket");
        checkWinnerLabel = new JLabel("Check Winners");
        
        //Defining TextFields
        priceTextField = new JTextField(5);
        stTextField = new JTextField(2);
        
        //Defining Buttons
        randomNumButton = new JButton("Random Number");
        pickNumButton = new JButton("Pick Numbers");
        multiGamesButton = new JButton("Multiple Games");
        biggestButton = new JButton("Biggest Winner");
        oldestButton = new JButton("Oldest Player");
        majorButton = new JButton("All Major Winners");
        
        // create the South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        theGUI.add(BorderLayout.SOUTH, southPanel);
        southPanel.add(priceLabel);
        southPanel.add(priceTextField);
        southPanel.add(stLabel);
        southPanel.add(stTextField);
        
        // create the East Panel  
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));
        theGUI.add(BorderLayout.EAST, eastPanel);
        eastPanel.add(Box.createVerticalGlue());  
        eastPanel.add(drawTicketLabel);
        eastPanel.add(randomNumButton);
        eastPanel.add(pickNumButton);
        eastPanel.add(multiGamesButton);
        eastPanel.add(checkWinnerLabel);
        eastPanel.add(biggestButton);
        eastPanel.add(oldestButton);
        eastPanel.add(majorButton);
        
        //Action Listener
        ButtonListener listener = new ButtonListener();
        randomNumButton.addActionListener(listener);
        pickNumButton.addActionListener(listener);
        multiGamesButton.addActionListener(listener);
        biggestButton.addActionListener(listener);
        oldestButton.addActionListener(listener);
        majorButton.addActionListener(listener);
        priceTextField.addActionListener(listener);
        stTextField.addActionListener(listener);
        
        // set up File menus
        setupMenus();
        theGUI.pack();
     }
     
    
    /*********************************************************************
     Displays the information for each ticket in th provided list
     
     @Param list list of all tickets
     *********************************************************************/
     public void displayTicket(ArrayList <LotteryTicket> list){ 
        for (LotteryTicket t: list) {
            results.append(t.toString() + "\n");
        }
     }
    
    
    /*********************************************************************
     Respond to menu selections and button clicks

     @param e the button or menu item that was selected
     *********************************************************************/
     private class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            LotteryTicket t = null;
            String str = new String();
            ArrayList <LotteryTicket> tix = new ArrayList <LotteryTicket> ();
            // menu item - quit
            if (e.getSource() == quitItem){
                System.exit(1);
            }
            
            //menu item - open: opens the file
            if (e.getSource() == openItem){
                //not correct not a arryay string or ticket so what is it 
                openFile();
                //results.append(openFile());
            }
            
            //menu item - Reports: Displays general report
            if (e.getSource() == reportsMenu){
                str = lm.createReport();
                results.append(str.toString());
            }
            
            //menu item - by State: Displays state report
            if (e.getSource() == stateItem){
                results.setText("State Report: ");
                String text = priceTextField.getText();
               if (text.length() == 0){
                    JOptionPane.showMessageDialog(null, "Enter in State");
               }else{
                    str = lm.createReport(text);
                    results.append(str.toString());
               }
            }
            
            //Creates Random Winning numbers 
            if (e.getSource() == randomNumButton){
                results.setText("Random Numbers");
                lm.drawTicket();
                results.append(t.toString());
            }
            
            //Allow new number to be selected 
            if (e.getSource() == pickNumButton){
                results.setText("New Numbers Selected");
                pickNumbers();
                results.append(t.toString());
                //results.setText("");
            }
            
            //Displays jackpot winner and number of games it took
            if (e.getSource() == multiGamesButton){
                results.setText("Multiple Games");
                str = lm.multipleGames();
                results.append(str.toString());
            }
            
            //Displays all the biggest Winners
            if (e.getSource() == biggestButton){
                results.setText("Biggest Winner: ");
                String text = stTextField.getText();
               if (text.length() == 0){
                    JOptionPane.showMessageDialog(null, "Enter in Prize");
               }else{
                    double price = Double.parseDouble(text);
                    tix = lm.getMajorWinners(price);
                    displayTicket(tix);
               }
            }
            
            //Displays Oldest Player
            if (e.getSource() == oldestButton){
                results.setText("Oldest Player: ");
                t = lm.getOldestPlayer();
                tix.add(t);
                displayTicket(tix);
                tix.remove(t);
                results.setText("");
            }
            
            //Displays all Major Winners
            if (e.getSource() == majorButton){
                results.setText("Major Winners");
                String text = stTextField.getText();
               if (text.length() == 0){
                    JOptionPane.showMessageDialog(null, "Enter in Prize Amount");
               }else{
                    double price = Double.parseDouble(text);
                    tix = lm.getMajorWinners(price);
                    displayTicket(tix);
               }
            }
            }
    }
        
    
    /*********************************************************************
     Set up the menu items
     *********************************************************************/
     private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        openItem = new JMenuItem("Open...");
        reportsMenu = new JMenu("Reports");
        stateItem = new JMenuItem("by State");
        reportItem = new JMenuItem("All Tickets");

        // assign action listeners
        ButtonListener ml = new ButtonListener();
        quitItem.addActionListener(ml);
        openItem.addActionListener(ml);
        stateItem.addActionListener(ml);
        reportItem.addActionListener(ml);

        // display menu components
        fileMenu.add(openItem);
        fileMenu.add(quitItem);
        reportsMenu.add(reportItem);
        reportsMenu.add(stateItem);    
        menus = new JMenuBar();

        menus.add(fileMenu);
        menus.add(reportsMenu);
        theGUI.setJMenuBar(menus);
     } 
        
     
    /*********************************************************************
     In response to the menu selection - open a data file
     *********************************************************************/
     private void openFile(){
         JFileChooser fc = new JFileChooser(new File(System.getProperty("user.dir")));
         int returnVal = fc.showOpenDialog(theGUI);

         // did the user select a file?
         if (returnVal == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            lm.readTickets(filename);          
        }
     }
     
     
    /*********************************************************************
     Propts user to enter in all the winning numbers
     *********************************************************************/
     private void pickNumbers(){ 
        String num = JOptionPane.showInputDialog("Ball One: ");
        int b1 = Integer.parseInt(num);
        num = JOptionPane.showInputDialog("Ball Two: ");
        int b2 = Integer.parseInt(num);
        num = JOptionPane.showInputDialog("Ball Three: ");
        int b3 = Integer.parseInt(num);
        num = JOptionPane.showInputDialog("Ball Four: ");
        int b4 = Integer.parseInt(num);
        num = JOptionPane.showInputDialog("Ball Five: ");
        int b5 = Integer.parseInt(num);
        num = JOptionPane.showInputDialog("Mega Ball: ");
        int m = Integer.parseInt(num);
        lm.drawTicket(b1,b2,b3,b4,b5,m);
     }
}
    