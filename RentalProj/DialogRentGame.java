package package1;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

/***********************************************************************
 *Dialog Box that allows for information to be enter to create a new 
 *game if the correct format for each entry is correctly inputed. 
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class DialogRentGame extends JDialog implements ActionListener{

	/** Title of DVD */
	private JTextField titleTF;

	/** Name of Renter */
	private JTextField renterTF;

	/** Date Rented On */
	private JTextField rentedOnTF;

	/** Date Due On */
	private JTextField dueBackTF;

	/** Title Label */
	private JLabel titleL;

	/** Name Label */
	private JLabel renterL;

	/** Date rented On */
	private JLabel rentedOnL;

	/** Date due on */
	private JLabel dueBackL;

	/** Type of Player */
	private JLabel playerL;

	/** If it is ok to close Window */
	private boolean closeStatus;

	/** Current Game */
	private Game unit;  

	/** Main Panel */
	private JPanel mainP;

	/** Result of Dialog Pane */
	private int result;

	/** Combo Box For Player Type Selection */
	private JComboBox playerList;

	/** Result of Combo Box */
	private PlayerType console;


	/*******************************************************************
	Constructor: creates a dialog box that allows a a DVD or game to 
	be created
	@param parent the GUIRenatlStore Frame
	@param d a given game 
	*******************************************************************/
	public DialogRentGame(JFrame parent, Game d) {
		super(parent, true);
		closeStatus = false;

		//Sets up Dialog Pane
		unit = d;
		mainP = new JPanel();
		titleTF = new JTextField(unit.getTitle());
		renterTF = new JTextField(unit.getNameOfRenter());
		rentedOnTF= new JTextField(unit.dateToString(unit.getBought()));
		dueBackTF= new JTextField(unit.dateToString(unit.getDueBack()));

		titleL = new JLabel("Title of Game: ");
		playerL = new JLabel("Type of Game: ");
		renterL = new JLabel("Your Name: ");
		rentedOnL = new JLabel("Date Rented On (mm/dd/yyyy): ");
		dueBackL = new JLabel("Date Due Back On (mm/dd/yyyy): ");

		mainP.add(titleL);
		mainP.add(titleTF);
		mainP.add(renterL);
		mainP.add(renterTF);
		mainP.add(rentedOnL);
		mainP.add(rentedOnTF);
		mainP.add(dueBackL);
		mainP.add(dueBackTF);
		mainP.add(playerL);
		
		//Adding Scroll down bar
		playerList = new JComboBox(new DefaultComboBoxModel(PlayerType.values()));
		playerList.addActionListener(new ComboListener());
		mainP.add(playerList);
		
		//Pairing Label with textfield
		mainP.setLayout(new GridLayout(0,2));

		//Displays Dialog Pane
		result = JOptionPane.showConfirmDialog(parent, mainP, 
				"Please Enter The Following Information", 
				JOptionPane.OK_CANCEL_OPTION);

		//Responds to interaction
		titleTF.addActionListener(this);
		renterTF.addActionListener(this);
		rentedOnTF.addActionListener(this);
		dueBackTF.addActionListener(this);
		readResults();
	}


	/*******************************************************************
	Action Listener for the combo box with the console type selection. 
	Communicates results with the Game class
	*******************************************************************/
	private class ComboListener implements ActionListener{ 
		public void actionPerformed(ActionEvent e) {
			console = (PlayerType) playerList.getSelectedItem();
			switch (console) {
			case Xbox360: unit.setPlayer(PlayerType.Xbox360);
			break;
			case PS3: unit.setPlayer(PlayerType.PS3);
			break;
			case Xbox720: unit.setPlayer(PlayerType.Xbox720);
			break;
			case PS4: unit.setPlayer(PlayerType.PS4);
			break;
			}
		}
	}


	/*******************************************************************
	Helper: Makes sure the info entered is legit. Legit: not empty and 
	correct format. If legit, then a game is created. If not dialog is 
	disposed of. 
	*******************************************************************/
	private void readResults(){
		if(isLegit() == true){
			closeStatus = true;	
		}if(result == 0 && closeStatus ==true){
			try {
			  unit = new Game(rentedOnTF.getText(), dueBackTF.getText(), 
						titleTF.getText(), renterTF.getText(), 
						(PlayerType)playerList.getSelectedItem());
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, "One or more dates "
						+ "entered were in the incorrect format");
				dispose();
				closeStatus = false;
			}
		}else{
			closeStatus = false;
			dispose();
		}
	}


	/*******************************************************************
	Helper: Returns if it is ok to close the dialog pane
	@return closeStatus if it is ok to close the dialog pane
	*******************************************************************/
	public boolean getCloseStatus() {
		return closeStatus;
	}


	/*******************************************************************
	Helper: Returns the current dvd being proposed
	@return unit dvd being proposed
	*******************************************************************/
	public DVD getUnit(){
		return unit;
	}


	/*******************************************************************
	Helper: Determines if the info entered is null or just spaces
	@return true if the game info was a valid entry
	@return false if the game info was not a valid entry
	*******************************************************************/
	public boolean isLegit(){

		//Takes the text from each text box deletes the white space and 
		//checks the length
		if(rentedOnTF.getText().trim().length() > 0 && dueBackTF.
				getText().trim().length() > 0 && titleTF.getText().
				trim().length() > 0 && renterTF.getText().trim().
				length() > 0 && playerList.getSelectedItem() != null){
			return true;
		}
		return false;	
	}

	
	/*******************************************************************
	Helper: Allows actions to be performed
	*******************************************************************/
	public void actionPerformed(ActionEvent e) {
		
	}
}