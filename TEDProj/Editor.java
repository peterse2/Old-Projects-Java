package package1;

import java.io.*;
import java.util.*;
import javax.swing.*;


/***********************************************************************
 * Editor class implements a simple line-oriented editor.
 * @author Emily Peterson
 * @version 11/06/2014 
 **********************************************************************/
public class Editor implements IEditor {

	/** List of each line */
	private MyLinkedList list;

	/** Indicated comand */
	private String command;
	
	/** If it is ok to close the editor */
	private boolean okToClose;
	
	/** ClipBoard */
	private ArrayList<String> clipboard;

	
	/*******************************************************************
	 * Main: New list is created, initial command is "", okToClost is 
	 * set to false
	 ******************************************************************/
	public Editor() {
		list = new MyLinkedList();
		command = "";
		okToClose = false;
		clipboard = new ArrayList <String>();
	}
	
	
	/*******************************************************************
	 * Processes the given editor command
	 * @param command letters from the keys pressed by user
	 ******************************************************************/
	public void processCommand(String pCommand) throws EditorException {
		String str = new String();
		
		String [] token = pCommand.split(" ", 2);

		command = token[0].trim();
		command.toLowerCase();

		if(token.length <=1){
			
		}else{	
			str = token[1].trim();
		}
		
		//System.out.println ("cmd" + command);
		switch (command){
			
		//Inserts line before current line
			case "b": 
				before(str);
				break;
			
		//Inserts line after current line
			case "i":
				insert(str);
				break;
				
		//Move current line indicator down n positions. If not 
		//indocatied move down one
			case "m":
				down(str);	
				break;
		
		//Move current line indicator up n positions. If not 
		//indocatied move up one
			case "u": 
				up(str);
				break;

		//Removes indicated number of lines starting at the current 
		//line; if not indicated remove current line.
			case "r":
				remove(str);
				break;
				
		//Display lines from line n1 to line n2 (inclusive) in the 
		//buffer/file WITH LINE NUMBERS. If no range indicated display
		//all
			case "d":
				display(str);
				break;
						
				
		//Clears all the lines in the buffer after confirmation
			case "c":
				int result = JOptionPane.showConfirmDialog(null, 
													   "Are you sure?");
				
				//Ok button was pushed
				if(result == 0)
					list.clear();
				break;
				
		//Saves the content of the specified file
			case "s": 
				if (str == null || str.length() <= 0)
					throw new EditorException("No file name was "
														 + "indicated");
				
				else
					saveTxt(str);
				break;
				
		//Loads the content of the specified file
			case "l":
				if (str == null || str.length() <= 0)
					throw new EditorException("No file name was "
														 + "indicated");
				
				else
					loadTxt(str);	
				break;
				
		//Displays a help Page with all the commands
			case "h": 
				help();
				break;
				
		//Exit the editor after confirmation
			case "x": 
				result = JOptionPane.showConfirmDialog(null, 
													   "Are you sure?");
				if(result == 0)
					okToClose = true;
				else
					okToClose = false;
				break;
		
		//Insert sentence after the last line
			case "e":
				insertAfter(str);
				break; 
					
		//Reverses the lines in the buffer/file.
			case "rev":
				reverse();
				break;
	
		//Cut lines from the file from line n1 to line n2 inclusive
		//and copy to an internal clipboard. 
			case "cut":
				cut(str);
				break;
				
		//Paste the clipboard contents before the current line
			case "pas":
				paste();
				break;
		}

	}


	/*******************************************************************
	 * Returns number of lines in the buffer
	 * @return num number of lines in the buffer
	 ******************************************************************/
	public int nbrLines() {
		return list.size();
	}


	/*******************************************************************
	 * Returns the line at the given line number
	 * @param lineNbr the number associated to a line in the list
	 * @return str string of text in a line
	 ******************************************************************/
	public String getLine(int lineNbr) throws EditorException {
		if(lineNbr > list.size()){
			throw new EditorException("Line does not exist.");
		}
		if(list.get(lineNbr).contains(":)"))
			return list.get(lineNbr).substring(2);
		
		return list.get(lineNbr);
	}


	/*******************************************************************
	 * Returns the current line
	 * @return str string of text in a line
	 * @return null if the list has not been created 
	 ******************************************************************/
	public String getCurrentLine() {
//		if(getCurrentLineNbr() == -1)
//			return null;
//		else
//			return list.get(getCurrentLineNbr()).substring(2);
		
		for(int i=0; i < list.size(); i++)
			try{
				if(list.get(i).substring(0,2).equals(":)"))
					return list.get(i).substring(2);
			}catch(IndexOutOfBoundsException e){
				return null;
			}
		return list.get(0).substring(2);
	}


	/*******************************************************************
	 * Returns the current line number
	 * @return num number assaciated to the line currently selected
	 * @return -1 if it does not exist
	 ******************************************************************/
	public int getCurrentLineNbr() {
		for(int i=0; i < list.size(); i++)
			try{
				if(list.get(i).substring(0,2).equals(":)"))
					return i;
			}catch(IndexOutOfBoundsException e){
				return -1;
			}
		return 0;
	}
	
	
	/*******************************************************************
	 * Sets the current line it index
	 * @param index the line number of the line being set to the current
	 * line
	 ******************************************************************/
	public void setCurrentLine(int current, int next){
		if(current >= 0 && current != next)
			list.getNode(current).setData(getCurrentLine());
		list.getNode(next).setData(":)"+list.get(next));
	}
	
	
	/*******************************************************************
	*Helper: Inserts line before current line; makes inserted line the 
	*current line
	*@param str indicated String
	*******************************************************************/
	public void before(String str){
		int currentLine = getCurrentLineNbr();
		if(list.isEmpty()){
			list.add(str);
			setCurrentLine(0,0);
		}else{
			list.add(currentLine, str);
			setCurrentLine(getCurrentLineNbr(), getCurrentLineNbr() -1);
		}
	}
	
	
	/*******************************************************************
	*Helper: Inserts line after current line; makes inserted line the 
	*current line
	*@param str indicated String
	*******************************************************************/
	public void insert(String str){
		if(list.isEmpty()){
			list.add(0, str);
			setCurrentLine(0,0);
		}else{ 
			list.add(str);
			setCurrentLine(getCurrentLineNbr(), getCurrentLineNbr()+1);
		}
	}
	
	
	/*******************************************************************
	*Helper: Move current line indicator down n positions. Throw 
	*EditorException if move is not possible. If not indocatied move 
	*down one
	*@param str indicated String
	*@throws EditorException is the range is invalid
	*******************************************************************/
	public void down(String str) throws EditorException{
		int num = 1;
		
		if(str != null && str.length() > 0)
			num = Integer.parseInt(str);	
		if(getCurrentLineNbr()+num < list.size())
			setCurrentLine(getCurrentLineNbr(),getCurrentLineNbr()+num);
		else
			throw new EditorException("Move is out of range");
	}
	
	
	/*******************************************************************
	*Helper: Move current line indicator up n positions. Throw 
	*EditorException if move is not possible. If not indicatied move 
	*up one
	*@param str indicated String
	*@throws Editor Exception if the number given is not valid
	*******************************************************************/
	public void up(String str) throws EditorException{
		int num = 1;
		
		if(str !=null && str.length() > 0)
			num = Integer.parseInt(str);
		if(getCurrentLineNbr()-num >= 0){
			setCurrentLine(getCurrentLineNbr(),getCurrentLineNbr()-num);
		}else
			throw new EditorException("Move is out of range");
	}
	
	
	/*******************************************************************
	*Helper: Removes indicated number of lines starting at the current 
	*line; if not indicated remove current line. Make the next line the 
	*current line, if there is not a next line make the previous line 
	*the current line. Throw EditorException if remove is not possible.
	*@param str indicated String
	*@throws EditorException if range is not valid
	*******************************************************************/
	public void remove(String str) throws EditorException{
		int num = 1;
		int currentLine = getCurrentLineNbr();
		
		if (str != null && str.length() > 0)
			num = Integer.parseInt(str);
		if (list == null || getCurrentLineNbr()+num > list.size())
			throw new EditorException("Remove not possible");
		else
			while(num > 0){
				list.remove(currentLine);
				num--;
			}
		list.display();

		//Fix currentLine indicator
		if(list.isEmpty()){
			return;
		}
		if(list.size() > currentLine)
			setCurrentLine(currentLine, currentLine);
		else
			setCurrentLine(currentLine-1,currentLine-1);	
	}
	
	
	/*******************************************************************
	*Helper: Display lines from line n1 to line n2 (inclusive) in the 
	*buffer/file WITH LINE NUMBERS. If no range indicated display all. 
	*Throw EditorException if this operation is not possible. 
	*@param str indicated String
	*@throws EditorException if the range is not possible
	*******************************************************************/
	public void display(String str) throws EditorException{
		int num1 = 0;
		int num2 = 0;
		
		if(str == null || str.length() <= 0){
			list.display();
		}else{
			String [] token = str.split(" ");
			num1 = Integer.parseInt(token[0]);
			num2 = Integer.parseInt(token[2]);

			if(list.size() > num2 && num1 > 0 && num1 > num2)
				list.display(num1, num2);
			else
				throw new EditorException("Range is not valid");
		}
	}
	
	
	/*******************************************************************
	*Helper: Insert sentence after the last line; make the inserted line 
	*the current line
	*@param str indicated String
	*******************************************************************/
	public void insertAfter(String str){
		list.add(str);
		if(!list.isEmpty() && list.size() > 1){
			setCurrentLine(getCurrentLineNbr(), list.size()-1);
		}else
			setCurrentLine(0,0);
	}
	
	
	/*******************************************************************
	*Helper: Reverses the lines in the buffer/file. Last line is first, 
	*and first line is last, and so on.  No change to the current line 
	*indicator. 
	*******************************************************************/
	public void reverse(){
		Stack<String> stack = new Stack<String>();
		Node nPointer = list.getFront();

		//for(int i=0; nPointer.getNext() != null; i++){
		while(nPointer != null){
			stack.push(nPointer.getData());
			nPointer = nPointer.getNext();
		}
		
		list.clear();
		while(!stack.empty()){
			list.add(stack.pop());
		}
		
	}
	
	
	/*******************************************************************
	*Helper: Cut lines from the file from line n1 to line n2 inclusive 
	*and copy to an internal clipboard. No change to the current line 
	*indicator if current line position makes sense after the cut 
	*operation. After the cut operation, if the current line indicator 
	*is not present set it to point at the element before n1. Throw 
	*EditorException if this operation is not possible.
	*@param str indicated String
	*@throws EditorException range is invalid
	*******************************************************************/
	public void cut(String str) throws EditorException{
		String string = "";
		
		if(str.length() < 3){
			return;
		}
		String [] token = str.split(" ");
		int n1 = Integer.parseInt(token[0].trim());
		int n2 = Integer.parseInt(token[1].trim());
		
		if(n2 > list.size() || n1 < 0 || list == null || n1 > n2){
			throw new EditorException("That range does not "
					+ "exist");
		}
		
		//Fixing the line indicator
		if(getCurrentLineNbr() <= n2-1 && getCurrentLineNbr() >= n1)
			setCurrentLine(getCurrentLineNbr(), n1-1);
		
		//Cut
		int count = n2;
		while(count > 0){
			string = list.remove(n1);
			clipboard.add(string);
//		while(n2 >= n1){
//			string = list.remove(n2-1);
//			clipboard.add(string);
//			//System.out.println(list.remove(n2));
//			//list.display();
//			n2--;
		}
		
//		while(n1 <= n2){
//			clipboard.add(list.remove(n1));
//			n1++;
//		}
	}
	
	
	/*******************************************************************
	*Helper: Paste the clipboard contents before the current line 
	*position. Make the first line of the lines pasted the current line. 
	*@param str indicated String
	*******************************************************************/
	public void paste(){
		int num = clipboard.size();
		while(!clipboard.isEmpty()){
			list.add(getCurrentLineNbr(), clipboard.remove(0));
			setCurrentLine(getCurrentLineNbr(),getCurrentLineNbr()-num);
		}
	}
	
	
	/*******************************************************************
	*Helper: Saves the Linked List of the text using text files
	*@param file name the name of the file being saved
	*******************************************************************/
	public void saveTxt(String filename){
		try { 
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(filename)));

			for (int i = 0; i < list.size();i++){
				out.println(list.get(i));
			}
			out.close();

		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "IOException");
		}


	}
	
	
	/*******************************************************************
	*Helper: Loads the Linked List of the text using text files
	*@param file name the name of the file being loaded
	*******************************************************************/
	public void loadTxt(String filename){
		Scanner scan;
		try {
			scan = new Scanner(new File(filename));
			
			okToClose = true;
			list.clear();
			
			while(scan.hasNext()){
				list.add(scan.nextLine());
			}
			scan.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, 
					   "Your file was not found");
		}
	}
	
	
	/*******************************************************************
	 * Displays a help page of all the editor commands
	 ******************************************************************/
	private void help() {
		String str = new String();

		str = "b <sentence>\t Insert sentence before the current line"
				+ "\n\t\t Makes the inserted line the current line.\n";
		str += "i <sentence>\t Insert sentence after the current line"
				+ "\n\t\t Makes the inserted line the current line.\n";
		str += "m\t\t Moves current line indicator down 1 position.\n";
		str += "u\t\t Moves current line indicator up 1 position.\n";
		str += "r\t\t Remove the current line; make the next line the "
				+ "\n\t\t current line, if there is a next line."
				+ "\n\t\t Otherwise it makes the previous line the"
				+ "\n\t\t current line, if there is a previous line.\n";
		str += "d\t\t Display all lines in the editor buffer.\n";
		str += "c\t\t Clear (remove) all lines in the buffer/file. "
				+ "\n\t\t Sets current line indicator appropriately.\n";
		str += "s <filename>\t Save the contents to the specified "
				+ "\n\t\t file.\n";
		str += "l <filename>\t Load the contents of the specified file"
				+ "\n\t\t into editor buffer replacing the current"
				+ "\n\t\t contents. Makes the first line the current "
				+ "\n\t\t line.\n";
		str += "h\t\t Displays a help page of editor commands.\n";
		str += "x\t\t Exit the editor.\n";
		str += "e <sentence>\t Insert sentence after the last line "
				+ "\n\t\t and makes the inserted line the current line."
				+ "\n";
		str += "m n\t\t Moves the current line indicator down n "
				+ "\n\t\t positions. \n";
		str += "u n \t\t Moves the current line indicator up n "
				+ "\n\t\t positions.\n";
		str += "r n\t\t Removes n lines starting at the current line. "
				+ "\n\t\t Makes the next line the current line, if"
				+ "\n\t\t there is a next line. Otherwise makes the"
				+ "\n\t\t previous line the current line.\n";
		str += "d n1 n2\t\t Displays lines from line n1 to line n2 "
				+ "\n\t\t (inclusive) in the buffer/file.\n";
		str += "rev\t\t Reverses the lines in the buffer/file. The last"
				+ "\n\t\t line is first, and the first line is last, "
				+ "\n\t\t and so on. There is no change to the current"
				+ "\n\t\t line indicator.\n";
		str += "cut n1 n2\t Cut lines from the file from line n1 to"
				+ "\n\t\t line n2 (inclusive), and copy to an internal"
				+ "\n\t\t clipboard. No change to the current line"
				+ "\n\t\t indicator if current line position makes "
				+ "\n\t\t sense after the cut operation. After the cut"
				+ "\n\t\t operation if the current line indicator"
				+ "\n\t\t points to beyond the last line, set it to"
				+ "\n\t\t point to the last line. If there are no lines"
				+ "\n\t\t after the cut operations, set the current"
				+ "\n\t\t line indicator appropriately.\n";
		str += "pas\t\t Paste the clipboard contents before the current"
				+ "\n\t\t line position. Make the first line of the lines "
				+ "\n\t\t pasted the current line.\n";
		System.out.println(str);	
	}
	
	
	/*******************************************************************
	*Helper: indicates if it is ok to close the editor
	*@return if it is ok to close
	*******************************************************************/
	public boolean isOkToClose(){
		return okToClose;
	}
}
