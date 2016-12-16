package week3;

//import java.awt.Point;
//import java.util.*;

public class ConnectFourGame1 {

	private int[][] board;
	private int player;

	public static int EMPTY = -1;
	public static int PLAYER1 = 1;
	public static int PLAYER2 = 2;
	
	public ConnectFourGame1 () {
		player = 1;
		board = new int[10][10];
		reset();
	}

	public void reset() {
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 10; c++)
				board[r][c] = -1;
	}

	public int selectCol (int pCol) {
		for (int r = 9; r >= 0; r--)
			if (board[r][pCol] == -1) {
				board[r][pCol] = player;
				return r;
			}
		return -1;
	}

	public int nextPlayer() {
		
		if (player == 1)
			player = 2;
		else
			player = 1;
		
		return player;

	}

	public int getCurrentPlayer () {
		return player;
	}

	public GameStatus isWinner() {	
		
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 7; c++)
				if ((board[r][c] == 1) && (board[r][c+1] == 1) &&
						(board[r][c+2] == 1) && (board[r][c+3] == 1))
							return GameStatus.Player1WON;
				
				
		for (int r = 0; r < 10; r++)
			for (int c = 0; c < 7; c++)
				if ((board[r][c] == 2) && (board[r][c+1] == 2) &&
						(board[r][c+2] == 2) && (board[r][c+3] == 2))
							return GameStatus.Player2WON;
		
		return GameStatus.InProgress;
	}

	public int getBoard(int row, int col) {
		return board[row][col];
	}


}