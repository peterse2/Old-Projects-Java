package package1;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


/**********************************************************************
 *All the game operations for superTicTacToe. A Super TicTacToe game is 
 *is a game of the user specified size board with the usual default size
 *of three by three, includes wrapping of the board for more 
 *possibilities to win with a specified number in a row to win (3 in a 
 *row as default).
 *@author Emily Peterson
 *@version 9-27-14
 **********************************************************************/
@SuppressWarnings("serial")
public class SuperTicTacToeGame implements Serializable  {

	/** Board of cells */
	private Cell[] [] board;

	/** Status of the game */
	private GameStatus status;

	/** Number of times x has won */
	private int XWin;

	/** Number of times o had won */
	private int OWin;

	/** The size of board */
	private int boardSize;

	/** How many in a row it takes to win */
	private int winAmount;

	/** List if cell positions chosen by the user */
	private ArrayList <Point> positions;

	/** Which player's turn it is */
	private char player;

	/** Name of a File */
	private String fileName;

	/*******************************************************************
	Constructor: Initializes all instance variables and resets the board
	 *******************************************************************/
	public SuperTicTacToeGame(int size, int start) {
		boardSize = size;
		board = new Cell[boardSize] [boardSize];
		status = GameStatus.IN_PROGRESS;
		XWin = 0;
		OWin = 0;

		winAmount = 3;
		positions = new ArrayList<Point>();

		if(start == 1){
			player = 'o';
		}else{
			player = 'x';
		}

		reset();
	}


	/*******************************************************************
 	Helper: Sets the board size to size
	@param size given size of the board (size,size)
	 *******************************************************************/
	public void setBoardSize(int size){
		boardSize = size;
	}	


	/*******************************************************************
	Helper: Returns the size of the board
 	@return boardSize the size of the board
	 *******************************************************************/
	public int getBoardSize(){
		return boardSize;
	}


	/*******************************************************************
	Marks cell with either an X or O
	@param row the number of row in the game board
	@param col the number of columns in the game board
	 *******************************************************************/
	public void select(int row, int col){
		if (board[row][col] == Cell.EMPTY){
			positions.add(new Point(row,col));

			if(player == 'o'){
				board[row][col] = Cell.O;
				player = 'x';
			}else if(player == 'x'){
				board[row][col] = Cell.X;
				player = 'o';
			}


		}
	}


	/*******************************************************************
	Resets the board to all empty cells
	 *******************************************************************/
	public void reset(){
		for (int row = 0; row < boardSize; row++){
			for(int col = 0; col < boardSize; col++){
				board[row] [col] = Cell.EMPTY;
			}
		}
	}


	//	public char find(Cell[] [] current){
	//		
	//		//Above
	//		if(row == 0){
	//			if(current == select(row.length())){
	//				return 'U';
	//			}
	//		}if(current == board[row-1][]){
	//			return 'U';
	//		}
	//		
	//		//Below
	//		if(row == board.length()){
	//			if(current == board[0][]){
	//				return 'D';
	//			}
	//		}if(current == board[row+1][]){
	//			return 'D';
	//		}
	//		
	//		//Left
	//		if(col == 0){
	//			if(current == board[][col-1]){
	//				return 'L';
	//			}
	//		}
	//		if(current == board[][col-1]){
	//			return 'L';
	//		}
	//		
	//		//Right
	//		if(col == 0){
	//			if(current == board[][col-1]){
	//				return 'L';
	//			}
	//		}
	//		if(current == board[][col-1]){
	//			return 'L';
	//		}
	//	}


	/*******************************************************************
	Helper: Returns the board of type cell that is a 2D array
	@return board board of type cell
	 *******************************************************************/
	public Cell [] [] getBoard(){
		return board;
	}


	/*******************************************************************
	Helper: Returns the name of a file of a game saved
	@return fileName String of the name of a file
	 *******************************************************************/
	public String getFileName(){
		return fileName;
	}


	/*******************************************************************
	Helper: Returns the number of times x has won the game.
	@return XWin the number of times x has won the game
	 *******************************************************************/
	public int getXWin() {
		return XWin;
	}


	/*******************************************************************
	Helper: Returns the number of times o has won the game.
	@return XWin the number of times o has won the game
	 *******************************************************************/
	public int getOWin() {
		return OWin;
	}


	/*******************************************************************
	Helper: Returns how many in a row a player needs to win
	@return winAnount the number of similar cells needed for a player to 
	win the game
	 *******************************************************************/
	public int getWinAmount() {
		return winAmount;
	}


	/*******************************************************************
	Helper: Sets how many in a row a player needs to win
	@param winAmount the amount that need to be in a row to win
	 *******************************************************************/
	public void setWinAmount(int winAmount) {
		this.winAmount = winAmount;
	}


	/*******************************************************************
	Helper: Returns a list of moves in order that have been made
	@return positions array of moves in order that have been made
	 *******************************************************************/
	public ArrayList<Point> getPositions() {
		return positions;
	}


	//	/**************************************************************
	//*****
	//	Helper: Allows you to make a list of moves that has been made
	//	*************************************************************
	//******/
	//	public void setPositions(ArrayList<Point> positions) {
	//		this.positions = positions;
	//	}


	/*******************************************************************
	Helper: Returns player that has the next turn
	@return player the player that is allowed to make the next move
	 *******************************************************************/
	public char getPlayer() {
		return player;
	}


	/*******************************************************************
	Helper: Sets the player that has the next turn
	@param player the player that is able to make the next move
	 *******************************************************************/
	public void setPlayer(char player) {
		this.player = player;
	}


	/*******************************************************************
	Helper: Determines if a given board size is valid
	@param size a board the size of (size,size)
	@return true if the size entered is between 3 and 14
	@return false if the size is not between 3 and 14
	 *******************************************************************/
	public boolean isValidBoardSize(String size){
		if(size.length() > 2 && size.length() <15){
			return true;
		}
		return false;
	}


	/*******************************************************************
	Helper: Determines the status of the game
	@return IN_PROGRESS incomplete game, no one has won
	@return X_WON player x has enough in a row to win
	@return O_WON player o has enough in a row to win
	@return CATS all spaces have been chosen and neither player has 
	enough in a row to win
	 *******************************************************************/
	public GameStatus getStatus(){
		for (int row = 0; row < (boardSize-1); row++){
			for(int col = 0; col < (boardSize-1); col++){
				if(colWin(row,col,winAmount) == true){
					status = GameStatus.X_WON;
				}if(rowWin(row,col,winAmount) == true){
					status = GameStatus.X_WON;
				}if(diag1Win(row,col,winAmount) == true){
					status = GameStatus.X_WON;
				}if(diag2Win(row,col,winAmount) == true){
					status = GameStatus.X_WON;
				}
			}
		}
//		if(isFull() == true){
//			status = GameStatus.CATS;
//		}else{
//			status = GameStatus.IN_PROGRESS;
//		}
		return status;
		
	}
//		int count = 0;
//
//		//Check win in row 
//		for (int row = 0; row < (boardSize-1); row++){
//			for(int col = 0; col < (boardSize-1); col++){
//				if(board[row%boardSize][col%boardSize] == Cell.EMPTY){
//					status = GameStatus.IN_PROGRESS;
//					return status;
//				}
//				if(board[row%boardSize][col%boardSize] == board[row][(col+1)%boardSize]){
//					count++;
//					if(count >= winAmount){
//						if(board[row][col] == Cell.O){
//							status = GameStatus.O_WON;
//						}if(board[row][col] == Cell.X){
//							status = GameStatus.X_WON;
//						}
//						return status;
//					}
//				}else{
//					count = 0;
//				}
//
//
//			}
//		}
//
//		//Check win in columns
//		for (int col = 0; col < (boardSize-1); col++){
//			//less than equal to and no -1
//			for(int row = 0; row < (boardSize-1); row++){
//				if(board[row][col] == Cell.EMPTY){
//					status = GameStatus.IN_PROGRESS;
//					return status;
//				}
//				if(board[row][col] == board[row+1%boardSize][col]){
//					count++;
//				}else{
//					count = 0;
//					break;
//				}
//
//				if(count >= winAmount){
//					if(board[row][col] == Cell.O){
//						status = GameStatus.O_WON;
//					}if(board[row][col] == Cell.X){
//						status = GameStatus.X_WON;
//					}
//					return status;
//				}
//			}
//		}
//
//		//Check win in diagonals with wrap
//		for (int row = 0; row < (boardSize-1); row++){
//			for(int col = 0; col < (boardSize-1); col++){
//				int row1 = Math.abs(row - boardSize) %boardSize;
//				int col1 = (col + 1)%boardSize; 
//				if(board[row][col] == Cell.EMPTY){
//					status = GameStatus.IN_PROGRESS;
//					return status;
//				}
//				if(board[row][col] == board[row1][col1]){
//					count++;
//				}else{
//					count = 0;
//				}
//
//				if(count == winAmount){
//					if(board[row][col] == Cell.O){
//						status = GameStatus.O_WON;
//					}if(board[row][col] == Cell.X){
//						status = GameStatus.X_WON;
//					}
//					return status;
//				}
//			}
//		}
//		for (int row = 0; row < (boardSize-1); row++){
//			for(int col = 0; col < (boardSize-1); col++){
//				int row1 = (row + boardSize - 1)%boardSize;
//				int col1 = (col + boardSize -1)%boardSize;
//				if(board[row][col] == Cell.EMPTY){
//					status = GameStatus.IN_PROGRESS;
//					return status;
//				}
//				if(board[row][col] == board[row1][col1]){
//					count++;
//				}else{
//					count = 0;
//				}
//
//				if(count == winAmount){
//					if(board[row][col] == Cell.O){
//						status = GameStatus.O_WON;
//					}if(board[row][col] == Cell.X){
//						status = GameStatus.X_WON;
//					}
//					return status;
//				}
//			}
//		}
//		return GameStatus.CATS;
//	}

	//		//Check win in main diagonals only 
	//		for (int row = 0; row < boardSize+winAmount-1; row++){
	//			int col = 0;
	//				if(board[row][col] == Cell.EMPTY){
	//					status = GameStatus.IN_PROGRESS;
	//					return status;
	//				}
	//				if(board[row][col] == board[row+1%boardSize]
	//		[col+1%boardSize]){
	//					count++;
	//				}else{
	//					count = 0;
	//				}
	//
	//				if(count == winAmount){
	//					if(board[row][col] == Cell.O){
	//						status = GameStatus.O_WON;
	//					}if(board[row][col] == Cell.X){
	//						status = GameStatus.X_WON;
	//					}
	//					return status;
	//				}
	//			col++;
	//		}
	//		for (int row = 0; row < boardSize+winAmount-1; row++){
	//			int col = boardSize -1;
	//				if(board[row][col] == Cell.EMPTY){
	//					status = GameStatus.IN_PROGRESS;
	//					return status;
	//				}
	//				if(board[row][col] == board[row+1%boardSize]
	//		[col-1%boardSize]){
	//					count++;
	//				}else{
	//					count = 0;
	//				}
	//
	//				if(count == winAmount){
	//					if(board[row][col] == Cell.O){
	//						status = GameStatus.O_WON;
	//					}if(board[row][col] == Cell.X){
	//						status = GameStatus.X_WON;
	//					}
	//					return status;
	//				}
	//			col--;
	//		}


	//	//Trial 2 
	//	boolean flip = false;
	//	Cell current = board[row][col];
	//	int count = 0;
	//	int inc = 1;
	//
	//	for(int row = 0; row < 3; row++){
	//		for(int col = 0; col < 3; col++){
	//			do{
	//				if(current = Cell.Empty){
	//					return GameStatus.IN_PROGRESS;
	//				}
	//				//if(current == select(row+inc, col) && current == 
	//	row+2, col)){
	//				if(current == board[row+inc][col]% board.length){
	//					flip = true;
	//				}if(current == board[row-inc][col]% board.length){
	//					flip = true;
	//				}if (current == board[row][col+inc]% board.length){
	//					flip = true;
	//				}if (current == board[row][col-inc]% board.length){
	//					flip = true;
	//				}if (current == board[row+inc][col+inc]% board.leng
	//th){
	//					flip = true;
	//				}if (current == board[row-inc][col+inc]% board.leng
	//th){
	//					flip = true;
	//				}if (current == board[row+inc][col-inc]% board.leng
	//th){
	//					flip = true;
	//				}if (current == board[row-inc][col-inc]% board.leng
	//th){
	//					flip = true;
	//				}
	//				if (flip == true){
	//					col++;
	//					count++;
	//					inc++;
	//				}
	//			}while(flip == true && count < 2);
	//		}
	//	}
	//	if(count = 3){
	//		if(current == Cell.X){
	//			status = GameStatus.X_WON;
	//		}
	
	public boolean colWin(int row, int col, int winamount){
		int length = board.length;
		if(board[row][col] == board[row++%board.length-1][col]){
			winamount --;
		}if(winamount == this.winAmount){
			return true;
		}if(board[row][col] != board[row++%board.length-1][col]){
			return false;
		}else{
			return colWin(row++%board.length-1,col, winamount);
		}
	}
	public boolean rowWin(int row, int col, int winamount){
		if(board[row][col] == board[row][col++%board.length-1]){
			winamount --;
		}if(winamount == this.winAmount){
			return true;
		}if(board[row][col] != board[row][col++%board.length-1]){
			return false;
		}else{
			return colWin(row,col++%board.length-1, winamount);
		}
	}
	
	public boolean diag1Win(int row, int col, int winamount){
		if(board[row][col] == board[row++%board.length-1][col++%board.length-1]){
			winamount --;
		}if(winamount == this.winAmount){
			return true;
		}if(board[row][col] != board[row++%board.length-1][col++%board.length-1]){
			return false;
		}else{
			return colWin(row++%board.length-1,col++%board.length-1, winamount);
		}
	}
	
	public boolean diag2Win(int row, int col, int winamount){
		if(board[row][col] == board[row++%board.length-1][col--%board.length-1]){
			winamount --;
		}if(winamount == this.winAmount){
			return true;
		}if(board[row][col] != board[row++%board.length-1][col--%board.length-1]){
			return false;
		}else{
			return colWin(row++%board.length-1,col--%board.length-1, winamount);
		}
	}
	
	public boolean isFull(){
		int count = 0;
		for (int row = 0; row < (boardSize-1); row++){
			for(int col = 0; col < (boardSize-1); col++){
				if(board[row][col] == Cell.EMPTY){
					count ++;
				}
			}
		}
		if(count > 0){
			return false;
		}else
			return true;
	}


	/*******************************************************************
	Helper: Determines if a file name is valid
	@param fileName name of a file
	@return true if the file name is not null
	@return false if the file name is null
	 *******************************************************************/
	public boolean isValidFileName(String fileName){
		if ((fileName != null) && (fileName.length() > 0)) {
			JOptionPane.showInputDialog("Save "
					+ "as... File Name: ");
			return true;
		}else{
			return false;
		}
	}


	/*******************************************************************
	Undoes previous move made and resets variables appropriately
	 *******************************************************************/
	public void undo(){
		Point p = positions.remove(positions.size()-1);
		board[(int) p.getX()][(int) p.getY()] = Cell.EMPTY;
		if(player == 'x'){
			player = 'o';
		}else{
			player = 'x';
		}
		if(status == GameStatus.O_WON){
			OWin--;
		}if (status == GameStatus.X_WON){
			XWin--;
		}
		status = GameStatus.IN_PROGRESS;
	}
}
