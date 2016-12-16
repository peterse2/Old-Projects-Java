package week3;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ConnectFour1 {

	//-----------------------------------------------------------------
	//  Creates and displays the main program frame.
	//-----------------------------------------------------------------
	public static void main (String[] args)
	{
		JMenuBar menus;
		JMenu fileMenu;
		JMenuItem quitItem;
		JMenuItem gameItem;
		
		fileMenu = new JMenu ("File:");
		quitItem = new JMenuItem("Quit!");
		gameItem = new JMenuItem("reset");
		
		JFrame frame = new JFrame ("Connect Four");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		fileMenu.add(quitItem);
		fileMenu.add(gameItem);
		menus = new JMenuBar();
		menus.add(fileMenu);
		
		frame.setJMenuBar(menus);
		
		ConnectFourPanel1 panel = new ConnectFourPanel1(quitItem, gameItem);
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setSize(500, 300);
		frame.setVisible(true);
	}
}
