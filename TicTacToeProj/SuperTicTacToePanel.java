package package1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;


/***********************************************************************
*GUI that displays a tic tac toe game board,and buttons to make a move 
*or quit. Includes a pull down bar to load previous games, save current 
*game, and undo moves.
*@author Emily Peterson
*@version 9-27-14
***********************************************************************/
@SuppressWarnings("serial")
public class SuperTicTacToePanel extends JPanel {
	
	/** SuperTicTacToe Game */
	private SuperTicTacToeGame game;
	
	/** GUI Board */
	private JButton [] [] board;
	
	/** Cell Board From Game */
	private Cell [] [] iBoard;
	
	/** Exits The Game */
	private JButton resetButton;

	/** Undoes The Previous Move */
	private JButton undoButton;
	
	/** Image of X */
	private ImageIcon xIcon;
	
	/** Image of O */
	private ImageIcon oIcon;
	
	/** Image of Nothing */
	private ImageIcon emptyIcon;
	
	/** Panel For All Buttons */
	private JPanel buttonPanel;
	
	/** Panel For The Board */
	private JPanel boardPanel;
	
	/** Panel For Number of Wins */
	private JPanel labelPanel;
	
	/** Total Number of Times X Has Won */
	private JLabel numWinX;
	
	/** Total Number of Times O Has Won */
	private JLabel numWinO;
	
	/** The status of the game */
	private JLabel status;
    
    /** Quit Menu Item */
    private JMenuItem quitItem;
    
    /** Save Menu Item */
    private JMenuItem saveItem;
    
    /** Load Menu Item */
    private JMenuItem loadItem;
	
	
	/*******************************************************************
	Sets up all panels, buttons, frames, menus, and button listeners. 
	Also, displays all frames 
	@param size the size of the board 
	@param start the player that starts, if 0 then x starts, if 1 then 
	o starts the game
	@param quit the menu item to quit the game
	@param save the menu item to save the game
	@param load the menu item to save the game
	@param winAmount the number of same "boxes" you need in a row to win
	*******************************************************************/
	public SuperTicTacToePanel(String size, int start, JMenuItem quit, 
							   JMenuItem save,JMenuItem load, String 
							   winAmount){
//		if(game.isValidBoardSize(size) == false){
//			size = "3";
//			JOptionPane.showInputDialog("Enter valid size!" 
//									+ "Try again or exit the program.");
//		}
		//Set Up Menu
		quitItem = quit;
		saveItem = save;
		loadItem = load;
		
		//ButtonListener listener = new ButtonListener();
		
		
		game = new SuperTicTacToeGame(Integer.parseInt(size), start);
		game.setWinAmount(Integer.parseInt(winAmount));
		
		board = new JButton[game.getBoardSize()][game.getBoardSize()];
		
		//Instantiate all but panels
		resetButton = new JButton("Reset");
		undoButton = new JButton("Undo");
		xIcon = new ImageIcon("x.png");
		oIcon = new ImageIcon("o.png");
		emptyIcon = new ImageIcon("empty.png");
		numWinX = new JLabel("Number of X Wins: "+ game.getXWin());
		numWinO = new JLabel("Number of O Wins: "+ game.getOWin());
		status = new JLabel("It is " + game.getPlayer() + "'s turn");
		
		//Button Panel Set Up
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(100,100));
		buttonPanel.add(resetButton);
		buttonPanel.add(undoButton);
		
		//LabelPanel Set up
		labelPanel = new JPanel();
		//labelPanel.setPreferredSize(new Dimension(10,5));
		labelPanel.add(numWinX);
		labelPanel.add(numWinO);
		labelPanel.add(status);
		
		//BoardPanel Set up
		boardPanel = new JPanel();
		boardPanel.setPreferredSize(new Dimension(game.getBoardSize(),
												  game.getBoardSize()));
		boardPanel.setLayout(new GridLayout(game.getBoardSize(),
												  game.getBoardSize()));
		
		//Frame Set up
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH,labelPanel);
		add(BorderLayout.EAST,buttonPanel);
		add(BorderLayout.CENTER,boardPanel);
		
		//Add ButtonListener
		ButtonListener boardListener = new ButtonListener();
		for (int row = 0; row < game.getBoardSize(); row++){
			for (int col = 0; col < game.getBoardSize(); col++){
				board[row] [col] = new JButton (emptyIcon);
				board[row] [col].addActionListener(boardListener);
				boardPanel.add(board[row] [col]);
			}
		}
		
		quitItem.addActionListener(boardListener);
		saveItem.addActionListener(boardListener);
		loadItem.addActionListener(boardListener);
		resetButton.addActionListener(boardListener);
		undoButton.addActionListener(boardListener);
		
		//Display
		displayBoard();
	}
	
	
	/*******************************************************************
	Adds an action to anything with an actionListener 
	*******************************************************************/
	private class ButtonListener implements ActionListener{

		
		/***************************************************************
		Puts correct actions to an event. Puts correct icons to the 
		correct cell, display appropriate messages when buttons are 
		pushed.
		@param e and action event from user
		***************************************************************/
		public void actionPerformed(ActionEvent e) {
			for (int row = 0; row < game.getBoardSize(); row++){
				for (int col = 0; col < game.getBoardSize(); col++){
					if (board[row] [col] == e.getSource()){
						game.select(row, col);
						status.setText("It is " + game.getPlayer() + "'s turn");
						game.getStatus();
						displayBoard();
					}
				}
			}
			//displayBoard();
			
			if (game.getStatus() == GameStatus.O_WON){
				JOptionPane.showMessageDialog(null, "O Won and X" +
						" lost!\n The game will reset");
				game.reset();
				displayBoard();
			}if (game.getStatus() == GameStatus.X_WON){
				JOptionPane.showMessageDialog(null, "X Won and O" +
						" lost!\n The game will reset");
				game.reset();
				displayBoard();
			}if (game.getStatus() == GameStatus.CATS){
				JOptionPane.showMessageDialog(null, "Cat Game" +
						"\nThe game will reset");
				game.reset();
				displayBoard();
			}
			
			if (e.getSource() == quitItem){
                JOptionPane.showConfirmDialog(null, 
                					  "Are You sure you want to quit?");
				saveGame();
                System.exit(1);
            }if (e.getSource() == saveItem){
            	saveGame();
            }if (e.getSource() == loadItem){
            	loadGame();
            }
            
            if(e.getSource() == resetButton){
            	game.reset();
            	displayBoard();
            }
            
            if(e.getSource() == undoButton){
            	game.undo();
            	
            	displayBoard();
            }
		}
	}
	
	
	
	
	/*******************************************************************
	Gets the TicTacToe board from the game and sets appropriate icons to
	the JButtons
	*******************************************************************/
	private void displayBoard(){
		iBoard = game.getBoard();
		for (int row = 0; row < game.getBoardSize(); row++){
			for (int col = 0; col < game.getBoardSize(); col++){
				if(iBoard[row] [col] == Cell.O){
					board[row] [col].setIcon(oIcon);
				}if(iBoard[row] [col] == Cell.X){
					board[row] [col].setIcon(xIcon);
				}if(iBoard[row] [col] == Cell.EMPTY){
					board[row] [col].setIcon(emptyIcon);
				}
			}
		}
		status.setText("It is " + game.getPlayer() + "'s turn");
	}
	
	
	/*******************************************************************
	Saves a String to a file
	@param file name of file being saved
	*******************************************************************/
	public void saveGame() {
		try {
			FileOutputStream saveFileStream = new FileOutputStream("saveFile.sv");   
			ObjectOutputStream objectStream = new 
										 ObjectOutputStream(saveFileStream);   

			objectStream.writeObject(game);     

			objectStream.close();   
			saveFileStream.close();   

		} catch (Exception e) {   
			JOptionPane.showConfirmDialog(null, e.toString() 
					+ "\nFail to save game state.", 
					"SuperTicTacToe Game", JOptionPane.DEFAULT_OPTION);
		}
	}   
	
	
	/*******************************************************************
	Loads a given file
	@param file the file being loaded
	*******************************************************************/
	public void loadGame() {
		try {

			FileInputStream fileStream = new FileInputStream("saveFile.sv");   
			ObjectInputStream objectStream;
			objectStream = new ObjectInputStream(fileStream);

			try {  
				game = (SuperTicTacToeGame) objectStream.readObject();
			} catch (ClassNotFoundException e) {
				System.out.println("Class not found");
				e.printStackTrace();
			}   
			finally {
				fileStream.close();
			}


		} catch (IOException e) {
			System.out.println("Sorry, somthing went wrong. Your game "
					+ "	was not able to load.");
			e.printStackTrace();
		}
		displayBoard();
	}
	
	
	/*******************************************************************
	Resets anything that needs to be reset after a game is loaded
	*******************************************************************/
	public void loadReset() {
		status.setText("It is " + game.getPlayer() + "'s turn");
	}
}