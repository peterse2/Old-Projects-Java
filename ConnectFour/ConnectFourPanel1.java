package week3;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//import javax.swing.colorchooser.ColorChooserComponentFactory;


@SuppressWarnings("serial")
public class ConnectFourPanel1 extends JPanel {

	private JLabel[][] board;
	private JButton[] selection;

	//private int player;

	private JPanel top;
	private JPanel bottom;

	private JButton exit;
	private JButton reset;

	private ConnectFourGame1 game;
	
	private JMenuItem quitItem;
	//private JMenuItem gameItem;

	public ConnectFourPanel1(JMenuItem quitItem, JMenuItem gameItem){		

		this.quitItem = quitItem;
		//this.gameItem = gameItem;
		
		//player = 0;
		top = new JPanel();
		bottom = new JPanel();			

		reset = new JButton ("Reset");
		top.add(reset);
		exit = new JButton ("Exit");
		top.add(exit);

		game = new ConnectFourGame1(); 

		bottom.setLayout(new GridLayout(10+1,10,1,1));  // room for top row

		ButtonListener listener = new ButtonListener();
		exit.addActionListener(listener);
		reset.addActionListener(listener);
		quitItem.addActionListener(listener);
		//gameItem.addActionListener(listener);
		
		selection = new JButton[10];

		for (int col = 0; col < 10; col++) {
			selection[col] = new JButton ("Select");
			selection[col].addActionListener(listener);
			bottom.add(selection[col]);
		}

		board = new JLabel[10][10];

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				board[row][col] = new JLabel("X");
				board[row][col].setForeground(Color.GREEN);
				bottom.add(board[row][col]);					
			}
		}

		setLayout(new BorderLayout());
		add (BorderLayout.NORTH,top);
		add (BorderLayout.CENTER,bottom);
		
		displayBoard();
	}



	//*****************************************************************
	//  Represents a listener for button push (action) events.
	//*****************************************************************
	private class ButtonListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the counter and label when the button is pushed.
		//--------------------------------------------------------------
		public void actionPerformed (ActionEvent event)
		{

			JComponent comp = (JComponent) event.getSource();

			for (int col = 0; col < 10; col++)
				if (comp == selection[col]) {
					int row = game.selectCol(col);
					if (row != -1) {
						board[row][col].setText(""+game.getCurrentPlayer());
						
					
						game.nextPlayer();
					}
					else
						JOptionPane.showMessageDialog(null,"Col is full!");
				}
				
					
			if (game.isWinner() == GameStatus.Player1WON)
				JOptionPane.showMessageDialog(null,"Player1 won!");
			
			if (game.isWinner() == GameStatus.Player2WON)
				JOptionPane.showMessageDialog(null,"Player2 won!");
				
					
		    
				
			if ((comp == exit) || (quitItem == comp))
				System.exit(1);
		
		
		}

	}
	
	private void displayBoard( ) {
		for (int row = 1; row < 10; row++) 
			for (int col = 0; col < 10; col++) {
				if (game.getBoard(row, col) == ConnectFourGame1.EMPTY) 
					board[row][col].setText("x");
				if (game.getBoard(row, col) == ConnectFourGame1.PLAYER1) 
					board[row][col].setText("1");
				if (game.getBoard(row, col) == ConnectFourGame1.PLAYER2) 
					board[row][col].setText("2");
			}

	}

}