package package1;

import java.io.*;
import java.util.*;
import javax.swing.*;


/***********************************************************************
 *Manages an Array List that holds all the DVD and Games created 
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class ListEngine extends AbstractListModel {

	/** List of DVD Rented */
	private ArrayList<DVD> listDVDs;


	/*******************************************************************
	Constructor: Creates a DVD
	*******************************************************************/
	public ListEngine(){
		super();
		listDVDs = new ArrayList<DVD>();
	}


	/*******************************************************************
	Helper: Returns a DVD at a specified spot in the array list
	@return listDVDs.get(arg0) a DVD at arg0 index in the array list
	*******************************************************************/
	public DVD getElementAt(int arg0) {
		return listDVDs.get(arg0);
	}


	/*******************************************************************
	Helper: Returns the size of the array list of DVDs
	@return listDVDs.size() the size of the array list 
	*******************************************************************/
	public int getSize() {
		return listDVDs.size();
		//-1?
	}


	/*******************************************************************
	Helper: Loads the array list of dvds using text files
	@param parent the GUIRentalStore frame
	*******************************************************************/
	public void loadTxt(JFrame parent){
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showOpenDialog(parent);
		if (result == JFileChooser.CANCEL_OPTION){
			chooser.cancelSelection();
		}

		try {
			if (result == JFileChooser.APPROVE_OPTION){
				File file = chooser.getSelectedFile();
				Scanner scan = new Scanner(file);
				String info;

				listDVDs.clear();

				while(scan.hasNext()){
					info = scan.nextLine();
					DVD dvd = new DVD(info);
					if(dvd.isGame == true){
						dvd = new Game(info);
					}
					listDVDs.add(dvd);
				}
				scan.close();
			}else{
				chooser.cancelSelection();
			}

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(parent, "No File Chosen");
		} catch (IllegalArgumentException e){
			JOptionPane.showMessageDialog(parent, "A date was "
					+ "incorrectly formated.");
		}


	}


	/*******************************************************************
	Helper: Saves an array list of dvds using text files
	@param parent the GUIRentalStore frame
	*******************************************************************/
	public void saveTxt(JFrame parent){
		try { 
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showSaveDialog(parent);
			if(result != JFileChooser.CANCEL_OPTION){
				File filename = chooser.getSelectedFile();

				PrintWriter out = new PrintWriter(new BufferedWriter(
											 new FileWriter(filename)));

				for (int i = 0; i < listDVDs.size();i++){

					DVD dvdUnit = listDVDs.get(i);

					out.print(dvdUnit.getNameOfRenter() + "\t");
					out.print(dvdUnit.getTitle() + "\t");
					out.print(dvdUnit.dateToString(dvdUnit.getBought()) 
																+ "\t");
					out.print(dvdUnit.dateToString(dvdUnit.getDueBack()) 
																+ "\t");

					if(dvdUnit instanceof Game){
						out.print(((Game) dvdUnit).getPlayer() + "\n");
					}else{
						out.print("\n");
					}

					out.close();
				}
			}
		}catch(IOException e){
			System.out.println("IO Error!");
		}


	}


	/*******************************************************************
	Helper: Loads the array list of dvds using Serializable
	@param parent the GUIRentalStore frame
	*******************************************************************/
	@SuppressWarnings("unchecked")
	public void loadSer(JFrame parent){
		try {

			FileInputStream loadFileStream = new FileInputStream(
														 "saveFile.sv");   
			ObjectInputStream objectStream;
			objectStream = new ObjectInputStream(loadFileStream);

			try {  
				listDVDs = (ArrayList<DVD>) objectStream.readObject();
				update();

			} catch (ClassNotFoundException e1) {
				System.out.println("Class not found");
			}   
			finally {
				loadFileStream.close();
				objectStream.close();   

			}

		} catch (IOException e1) {
			JOptionPane.showConfirmDialog(null,
					"Fail to load output.", "Error Loading", 
					JOptionPane.DEFAULT_OPTION);
		}
	}


	/*******************************************************************
	Helper: Saves using Serializable an array list of dvds using 
	Serializable
	@param parent the GUIRentalStore frame
	*******************************************************************/
	public void saveSer(JFrame parent){

		try {
			FileOutputStream saveFileStream = new FileOutputStream(
														 "saveFile.sv");   
			ObjectOutputStream objectStream = new 
					ObjectOutputStream(saveFileStream);   

			objectStream.writeObject(listDVDs);     

			objectStream.close();   
			saveFileStream.close();   

		} catch (Exception e1) {   
			JOptionPane.showConfirmDialog(null,
					"Fail to save input.", "Error Saving", 
					JOptionPane.DEFAULT_OPTION);
		}
	}


	/*******************************************************************
	Helper: Adds a dvd to the array list
	@param dvd a dvd selected 
	*******************************************************************/
	public void add(DVD dvd){
		listDVDs.add(dvd);
		Collections.sort(listDVDs);
		update();
	}


	/*******************************************************************
	Helper: deletes a dvd from the array list
	@param dvd a dvd selected
	*******************************************************************/
	public void delete(DVD dvd){
		if(getSize() > 0){
			listDVDs.remove(dvd);
			update();
		}
	}


	/*******************************************************************
	Helper: Lets the GUI know something has been changed and updates the 
	entire array list
	*******************************************************************/
	public void update(){
		fireContentsChanged(this, 0, listDVDs.size());
	}

}
