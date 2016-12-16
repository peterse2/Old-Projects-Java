package package1;

import java.util.GregorianCalendar;


/***********************************************************************
 *Creates all the pieces required for a game
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class Game extends DVD{

	/** Represents the type of player */
	private PlayerType player;


	/*******************************************************************
	Constructor: Instantiates all instance variables
	*******************************************************************/
	public Game(){
		super();
		player = PlayerType.Xbox360;
	}

	
	/*******************************************************************
	Constructor: Creates a Game given a string of info
	@throws e is the date is incorrectly formated 
	*******************************************************************/
	public Game(String info) {
		String [] token = info.split("\t");
		nameOfRenter = token[0].trim();
		title = token[1].trim();try{
			bought = toDate(token[2].trim());
			dueBack = toDate(token[3].trim());
		}catch(IllegalArgumentException e){
			throw e;
		}
		player = PlayerType.valueOf(token[4].trim());
	}
	

	/*******************************************************************
	Constructor: Creates a game with all the param info given
	@param pBought the date the game was rented out on
	@param pdueDate the date the game is due
	@param pTitle tile of the game
	@param pName name of the renter
	@param pType the type of console the game is for 
	@throws e if the date is incorrectly formated
	*******************************************************************/
	public Game(String pBought, String pDueDate, String pTitle, 
		String pName, PlayerType pType) throws IllegalArgumentException{

		try{
			bought = toDate(pBought);
			dueBack = toDate(pDueDate);
		}catch(IllegalArgumentException e){
			throw e;
		}

		title = pTitle;
		nameOfRenter = pName;
		player = pType;
	}
	
	
	/*******************************************************************
	Helper: Returns the current type of player
	@return player the type of console
	*******************************************************************/
	public PlayerType getPlayer() {
		return player;
	}


	/*******************************************************************
	Helper: Sets the current player to entered player
	@param player the type of console
	*******************************************************************/
	public void setPlayer(PlayerType player) {
		this.player = player;
	}


	/*******************************************************************
	Helper: Turns this game info into a string
	@return str string of the games information
	*******************************************************************/
	public String toString() {
		String str = super.toString() + "\tConsole: " + player;
		return str;
	}



	/*******************************************************************
	Helper: Calculates the amount owed
	@return amount the amount owed
	*******************************************************************/
	public String getWhatIsOwed() {
		GregorianCalendar today = new GregorianCalendar();
		String str = "$5 total";

		if(dueBack.compareTo(today) > 0){
			str = "$5 for the rental and $10 for being late. That is";
			str += " a total of $15";
		}
		return str;
	}
}