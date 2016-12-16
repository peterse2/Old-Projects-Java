package package1;

import java.util.Scanner;


/***********************************************************************
 * EditorCLI class provides a text-based user interface to the editor
 * supporting a read-process-print loop.
 * @author Emily Peterson
 * @version 11/5/2014
 **********************************************************************/
public class EditorCLI {


	/*******************************************************************
	 *Main: Runs the console interface
	 *@param args 
	 *******************************************************************/
	public static void main(String args[]) {
		Editor e = new Editor();
		Scanner scan = new Scanner(System.in);

		System.out.print("CMD: ");
		String s = scan.nextLine();
		//System.out.println (s);
		try {
			while(!s.equalsIgnoreCase("x")) {
				e.processCommand(s);

				System.out.print("CMD: ");
				s = scan.nextLine();
				//System.out.println (s);
			}

		} catch (EditorException e1) {
			e1.printStackTrace();
		}

		scan.close();
	}
}