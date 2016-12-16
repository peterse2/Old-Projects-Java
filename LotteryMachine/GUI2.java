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
public class GUI2 extends JPanel{

	/** Teacher 1 */
	private JButton teacher1;

	/** Teacher 2 */
	private JButton teacher2;

	/** Red */
	private JButton red;

	/** Green */
	private JButton green;

	/** Back to Previous Page */
	private JButton back;

	/** Cancel Everything */
	private JButton cancelB;

	/** Panel For All Buttons */
	private JPanel buttonPanel;

	/** Panel For All Buttons */
	private JPanel buttonPanel2;

	/** Panel For Number of Wins */
	private JPanel labelPanel;

	/** Total Number of Times X Has Won */
	private JLabel teacherName;

	/** Main Frame */
	private JFrame frame;



	/** JLabels */
	private JLabel priceLabel;

	/** results box */
	private JFrame theGUI;

	/** menu items */
	private JMenuBar menus;
	private JMenu fileMenu;
	private JMenu reportsMenu;
	private JMenuItem quitItem;

	/*******************************************************************
	 *Main: creates a JPanel abject of the type tigerCodeRed
	 ******************************************************************/
	public static void main(String arg[]){
		// the tradition five lines of code
		// normally place here are 
		// inserted throughout the construtor
		new GUI2();

	}

	/*******************************************************************
	 *Constructor - instantiates and displays all of the GUI commponents
	 ******************************************************************/
	public GUI2(){

		theGUI = new JFrame("Mega Million Lottery");
		theGUI.setVisible(true);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create the Results Area for the Center area


		//Defining JLabels
		priceLabel = new JLabel("Teacher Name");

		//Defining Buttons
		teacher1 = new JButton("Teacher1");
		teacher2 = new JButton("Teacher2");
		back = new JButton("back");
		red = new JButton("red");
		green = new JButton("green");

		// create the South Panel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		theGUI.add(BorderLayout.SOUTH, southPanel);
		southPanel.add(priceLabel);

		// create the Button Panel  
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		theGUI.add(BorderLayout.CENTER, buttonPanel);
		buttonPanel.add(Box.createVerticalGlue());  
		buttonPanel.add(teacher1);
		buttonPanel.add(teacher2);
		buttonPanel.add(back);
		buttonPanel.add(red);
		buttonPanel.add(green);

		//Action Listener
		ButtonListener listener = new ButtonListener();
		teacher1.addActionListener(listener);
		teacher2.addActionListener(listener);
		back.addActionListener(listener);
		red.addActionListener(listener);
		green.addActionListener(listener);

		// set up File menus
		setupMenus();
		theGUI.pack();
	}


	/*******************************************************************
     *Respond to menu selections and button clicks
     *@param e the button or menu item that was selected
	 ******************************************************************/
	private class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			LotteryTicket t = null;
			String str = new String();
			ArrayList <LotteryTicket> tix = new ArrayList <LotteryTicket> ();
			// menu item - quit
			if (e.getSource() == quitItem){
				System.exit(1);
			}

			//Creates Random Winning numbers 
			if (e.getSource() == teacher1){

			}

			//Allow new number to be selected 
			if (e.getSource() == teacher2){

			}

			//Displays jackpot winner and number of games it took
			if (e.getSource() == back){

			}

			//Displays all the biggest Winners
			if (e.getSource() == red){

			}

			//Displays Oldest Player
			if (e.getSource() == green){

			}
		}
	}


	/*********************************************************************
	 *Set up the menu items
	 *********************************************************************/
	private void setupMenus(){

		// create menu components
		fileMenu = new JMenu("File");
		quitItem = new JMenuItem("Quit");
		reportsMenu = new JMenu("Reports");

		// assign action listeners
		ButtonListener ml = new ButtonListener();
		quitItem.addActionListener(ml);

		// display menu components
		fileMenu.add(quitItem);    
		menus = new JMenuBar();

		menus.add(fileMenu);
		menus.add(reportsMenu);
		theGUI.setJMenuBar(menus);
	} 
}

