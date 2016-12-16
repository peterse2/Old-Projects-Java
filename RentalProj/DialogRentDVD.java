package package1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/***********************************************************************
 *Dialog Box that allows for information to be enter to create a new 
 *DVD if the correct format for each entry is correctly inputed. 
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class DialogRentDVD extends JDialog implements ActionListener{

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

	/** If it is ok to close Window */
	private boolean closeStatus;

	/** Current DVD */
	private DVD unit;  

	/** Main Panel */
	private JPanel mainP;

	/** Result of Dialog Box */
	private int result;


	/*******************************************************************
	Constructor: creates a dialog box that allows a a DVD or game to 
	be created
	@param parent the GUIRenatlStore Frame
	@param d a given dvd 
	*******************************************************************/
	public DialogRentDVD(JFrame parent, DVD d) {
		super(parent, true);
		closeStatus = false;

		//Sets up the Dialog Pane
		unit = d;
		mainP = new JPanel();
		titleTF = new JTextField(unit.getTitle());
	    renterTF = new JTextField(unit.getNameOfRenter());
	    rentedOnTF= new JTextField(unit.dateToString(unit.getBought()));
	    dueBackTF= new JTextField(unit.dateToString(unit.getDueBack()));

		titleL = new JLabel("Title of DVD: ");
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

		//Pairing Label with textfield
		mainP.setLayout(new GridLayout(0,2));

		//Displays Dialog Pane
		result = JOptionPane.showConfirmDialog(parent, mainP, 
				"Please Enter The Following Information", 
				JOptionPane.OK_CANCEL_OPTION);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		//Response to interaction
		titleTF.addActionListener(this);
		renterTF.addActionListener(this);
		rentedOnTF.addActionListener(this);
		dueBackTF.addActionListener(this);
		readResult();
	}


	/*******************************************************************
	Helper: Makes sure the info entered is legit. Legit: not empty and 
	correct format. If legit, then a game is created. If not dialog is 
	disposed of. 
	*******************************************************************/
	public void readResult(){
		if(isLegit() == true){
			closeStatus = true;
		}if(result == 0 && closeStatus ==true){

			try {
				unit = new DVD(rentedOnTF.getText(), dueBackTF.getText()
						        ,titleTF.getText(), renterTF.getText());
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
	@return true if the DVD info was a valid entry
	@return false if the DVD info was not a valid entry
	*******************************************************************/
	public boolean isLegit(){

		//Takes the text from each text box deletes the white space and 
		//checks the length
		if(rentedOnTF.getText().trim().length() > 0 && dueBackTF.
				getText().trim().length() > 0 && titleTF.getText().
				trim().length() > 0 && renterTF.getText().trim().
				length() > 0){
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