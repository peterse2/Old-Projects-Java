package package1;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;


/***********************************************************************
 *GUI that simulates a RedBox where you can rent and buy games and dvds
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class GUIRentalStore extends JFrame implements ActionListener {

	/** The main frame */
	private JFrame mainF;

	/** Menu Bar for file and action */
	private JMenuBar menuMB;

	/** File menu drop down*/
	private JMenu fileM;

	/** Action menu drop down */
	private JMenu actionM;

	/** Open Serializable in file Menu */
	private JMenuItem openSerMI;

	/** Save Serializable in file Menu */
	private JMenuItem saveSerMI;

	/** Open Text in file menu */
	private JMenuItem openTxtMI;

	/** Save Text in file menu */
	private JMenuItem saveTxtMI;

	/** Renting a DVD in action menu */
	private JMenuItem rentDVDMI;

	/** Renting a Game in action menu */
	private JMenuItem rentGameMI;

	/** Return and Item in action menu */
	private JMenuItem returnMI;

	/** Search for a title in action menu */
	private JMenuItem searchTitleMI;

	/** Search for a date in action menu */
	private JMenuItem searchDateMI;

	/** List of DVDs and Games*/
	private ListEngine list;

	/** Display */
	private JList listModel;


	/*******************************************************************
	 *Main: Displays or instantiates the initial or main "screen" 
	 *@param args 
	 ******************************************************************/
	public static void main (String[] args){
		new GUIRentalStore();
	}


	/*******************************************************************
	 *Constructor: Creates the initial or main "screen" 
	 ******************************************************************/
	public GUIRentalStore(){

		//Setting up the JList that lists ListEngine's ArrayList
		list = new ListEngine();
		listModel = new JList(list);
		listModel.setSelectionMode(ListSelectionModel
				.SINGLE_INTERVAL_SELECTION);
		listModel.setLayoutOrientation(JList.VERTICAL_WRAP);
		listModel.setVisibleRowCount(list.getSize());
		JScrollPane listScroller = new JScrollPane(listModel);
		listScroller.setPreferredSize(new Dimension(250, 80));

		//Setting up the main frame
		mainF = new JFrame("RedBox");
		mainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainF.setPreferredSize(new Dimension(800,200));

		//Setting up MenuBar
		menuMB = new JMenuBar();

		fileM = new JMenu("File");
		openSerMI = new JMenuItem("Open Serializabel");
		saveSerMI = new JMenuItem("Save Serializabel");
		openTxtMI = new JMenuItem("Open Text");
		saveTxtMI = new JMenuItem("Save Text");

		actionM = new JMenu("Action");
		rentDVDMI = new JMenuItem("Rent DVD");
		rentGameMI = new JMenuItem("Rent Game");
		returnMI = new JMenuItem("Return");
		searchTitleMI = new JMenuItem("Search By Title");
		searchDateMI = new JMenuItem("Search By Date");

		//Adding actionListener to menu items
		openSerMI.addActionListener(this);
		saveSerMI.addActionListener(this);
		openTxtMI.addActionListener(this);
		saveTxtMI.addActionListener(this);

		rentDVDMI.addActionListener(this);
		rentGameMI.addActionListener(this);
		returnMI.addActionListener(this);
		searchTitleMI.addActionListener(this);
		searchDateMI.addActionListener(this);

		//Setting up the menu bar
		menuMB.add(fileM);
		menuMB.add(actionM);

		//Setting up File Menu Item
		fileM.add(openSerMI);
		fileM.add(saveSerMI);
		fileM.add(openTxtMI);
		fileM.add(saveTxtMI);

		//Setting up Action Menu Item
		actionM.add(rentDVDMI);
		actionM.add(rentGameMI);
		actionM.add(returnMI);
		actionM.add(searchTitleMI);
		actionM.add(searchDateMI);

		//Adding everything to the main frame
		mainF.add(listModel);
		mainF.setJMenuBar(menuMB);
		mainF.pack();
		mainF.setVisible(true);
	}


	/*******************************************************************
	 *Helper: Searches for a title entered
	 ******************************************************************/
	public void searchTitle(){
		JTextArea results = new JTextArea(5,10);
		JPanel searchP = new JPanel();

		JScrollPane scrollPane = new JScrollPane(results); 
		results.setEditable(false);

		searchP.add(scrollPane);
		searchP.add(results);

		String searchStr = JOptionPane.showInputDialog(this, 
				"Search Enteries");

		for(int i= 0; i < list.getSize(); i++){
			DVD dvd = list.getElementAt(i);
			String str = dvd.getTitle();
			if(str.contains(searchStr)){
				results.append(dvd.toString()+"\n");
				results.setCaretPosition(results.getDocument().
						getLength());
			}
		}

		if(searchStr != null){
			JOptionPane.showMessageDialog(this, searchP);
		}
	}


	/*******************************************************************
	 *Helper: Searches for a date entered
	 ******************************************************************/
	public void searchDate(){
		JTextArea results = new JTextArea(5,10);
		JPanel searchP = new JPanel();

		JScrollPane scrollPane = new JScrollPane(results); 
		results.setEditable(false);

		searchP.add(scrollPane);
		searchP.add(results);

		String searchStr = JOptionPane.showInputDialog(this, 
				"Search Enteries");

		for(int i= 0; i < list.getSize(); i++){
			DVD dvd = list.getElementAt(i);
			String str = dvd.dateToString(dvd.getDueBack());
			if(str.equals(searchStr)){
				results.append(dvd.toString()+"\n");
				//results.setCaretPosition(results.getLineCount()+1);
			}
		}

		if(searchStr != null){
			JOptionPane.showMessageDialog(this, searchP);
		}
	}


	/*******************************************************************
	 *ActionListener: Response to menu items
	 *@param the event that happened
	 ******************************************************************/
	public void actionPerformed(ActionEvent e) {
		if(openSerMI == e.getSource()){
			list.loadSer(this);	
		}if(saveSerMI == e.getSource()){
			list.saveSer(this);
		}if(openTxtMI == e.getSource()){
			list.loadTxt(this);
			list.update();
		}if(saveTxtMI == e.getSource()){
			list.saveTxt(this);
			list.update();
		}if(rentDVDMI == e.getSource()){
			DVD dvd = new DVD();
			DialogRentDVD dialog = new DialogRentDVD(this, dvd);
			if(dialog.getCloseStatus() == true){
				list.add(dialog.getUnit());
			}
		}if(rentGameMI == e.getSource()){
			Game game = new Game();
			DialogRentGame dialog = new DialogRentGame(this, game);
			if(dialog.getCloseStatus() == true)
				list.add(dialog.getUnit());
		}if(returnMI == e.getSource() && list.getSize() > 0){
			int index = listModel.getSelectedIndex(); 

			//no selection, so insert at beginning
			if (index == -1) { 
				JOptionPane.showMessageDialog(this, "No selection was "
						+ "made", null, index);
				index = 0;

				//add after the selected item
			} else {           
				DVD selectedDVD = list.getElementAt(index);

				//Changing the due date
				String result = JOptionPane.showInputDialog("Enter "
						+ "Return Date (mm/dd/yyyy)");
				try{
					if(result != null)
						selectedDVD.setDueBack(selectedDVD.
								toDate(result));
				}catch(IllegalArgumentException error){
					JOptionPane.showMessageDialog(this, "The date "
							+ "enterend was not a valid date");
					result = JOptionPane.showInputDialog("Enter "
							+ "Return Date (mm/dd/yyyy)");
				}

				//Info message about return and deleting it
				if(result != null){
					String str= "Thank you "+ selectedDVD.
							getNameOfRenter(); 
					str += " for \nreturning "+ selectedDVD.getTitle(); 
					str +=  ". You owe: " + selectedDVD.getWhatIsOwed();
					list.delete(selectedDVD);
					JOptionPane.showMessageDialog(this, str,
							"Return",index);
				}
			}
		}if(searchTitleMI == e.getSource()){
			searchTitle();
		}if(searchDateMI == e.getSource()){
			searchDate();
		}
	}
}


