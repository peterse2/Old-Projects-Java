package package1;

import javax.swing.*;


/***********************************************************************
*Main GUI set up that is the overall frame and set up of a game
*@author Emily Peterson
*@version 9-27-14
***********************************************************************/
public class SuperTicTacToe{

	
	/*******************************************************************
	Main: creates a JPanel abject of the type SuperTicTacToe
	*******************************************************************/
	public static void main (String[] args){
		JMenuBar menu;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem saveItem;
		JMenuItem loadItem;
		
		JFrame frame = new JFrame("Super TicTacToe");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        //Creates menu 
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        
        
        //Displays the Menu
        fileMenu.add(quitItem);
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        menu = new JMenuBar();
        
        menu.add(fileMenu);
        frame.setJMenuBar(menu);
        
       
		

		
		String size = JOptionPane.showInputDialog 
								("Enter the size of the board: ");
		
		String[] options = { "X", "O"};
		int start = JOptionPane.showOptionDialog
		(null, "Who Starts the Game?", null, JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
		String win = "3";
		win = JOptionPane.showInputDialog("How many"
						  + " do you have to get in a row to win?");
		
		SuperTicTacToePanel panel = new SuperTicTacToePanel(size,start,
									   quitItem,saveItem,loadItem, win);
		frame.getContentPane();
		frame.add(panel);
		//frame.add(size);
		
		frame.pack();
		//frame.setSize(500, 300);
		frame.setVisible(true);
	}
}
	
