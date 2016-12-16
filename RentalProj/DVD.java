package package1;

import java.io.Serializable;
import java.text.*;
import java.util.*;


/***********************************************************************
 *Creates all the pieces required to make a DVD
 *@author Emily Peterson
 *@version 10-29-14
 **********************************************************************/
@SuppressWarnings("serial")
public class DVD implements Serializable, Comparable<DVD> {

	/** The date the DVD was rented */
	protected GregorianCalendar bought;

	/** The date the DVD is due back */
	protected GregorianCalendar dueBack;

	/** The title of the DVD */
	protected String title;

	/** The name of the person who is renting the DVD */
	protected String nameOfRenter; 

	/** Determines if a game has been loaded */
	protected boolean isGame;


	/*******************************************************************
	Constructor: Instantiates all instance variables
	*******************************************************************/
	public DVD(){
		bought = new GregorianCalendar();
		GregorianCalendar temp = new GregorianCalendar();
		temp.roll(Calendar.DAY_OF_WEEK, 1);
		dueBack = temp;
		title = "Title";
		nameOfRenter = "John Doe";
	}


	/*******************************************************************
	Constructor: Creates a game with all the param info given
	@param pBought the date the game was rented out on
	@param pdueDate the date the game is due
	@param pTitle tile of the game
	@param pName name of the renter
	@throws e if the dates are invalid
	*******************************************************************/
	public DVD(String pBought, String pDueDate, 
			String pTitle, String pName){

		try{
			bought = toDate(pBought);
			dueBack = toDate(pDueDate);
		}catch(IllegalArgumentException e){
			throw e;
		}

		title = pTitle;
		nameOfRenter = pName;
	}


	/*******************************************************************
	Constructor: Creates a DVD given a string of info
	@throws e if the dates are invalid
	*******************************************************************/
	public DVD(String info) {
		String [] token = info.split("\t");

		nameOfRenter = token[0].trim();
		title = token[1].trim();
		try{
			bought = toDate(token[2].trim());
			dueBack = toDate(token[3].trim());
		}catch(IllegalArgumentException e){
			throw e;
		}
		if(token.length > 4){
			isGame = true;
		}
	}


	/*******************************************************************
	Helper: Retrieves the date that this DVD was rented on
	@return bought the date this DVD was rented on
	*******************************************************************/
	public GregorianCalendar getBought() {
		return bought;
	}


	/*******************************************************************
	Helper: Sets the current bought to the given bought
	@param bought the date this DVD was rented on
	*******************************************************************/
	public void setBought(GregorianCalendar bought) {
		this.bought = bought;
	}


	/*******************************************************************
	Helper: Retrieves the date that this DVD is due
	@return dueBack the date this DVD is due
	 *******************************************************************/
	public GregorianCalendar getDueBack() {
		return dueBack;
	}


	/*******************************************************************
	Helper: Sets the current dueBack to the given bought
	@param dueBack the date this DVD is due back on
	*******************************************************************/
	public void setDueBack(GregorianCalendar dueBack) {
		this.dueBack = dueBack;
	}


	/*******************************************************************
	Helper: Retrieves the title
	@return title the title of this DVD
	*******************************************************************/
	public String getTitle() {
		return title;
	}


	/*******************************************************************
	Helper: Sets the current title to title
	@param title the title of a DVD
	*******************************************************************/
	public void setTitle(String title) {
		this.title = title;
	}


	/*******************************************************************
	Helper: Returns the persons name that rented out a DVD
	@return nameOfRenter name of person that rented out a DVD
	*******************************************************************/
	public String getNameOfRenter() {
		return nameOfRenter;
	}


	/*******************************************************************
	Helper: Sets the current nameOfRenter to nameOfRenter
	@param nameOfRenter the name of a person renting a DVD
	*******************************************************************/
	public void setNameOfRenter(String nameOfRenter) {
		this.nameOfRenter = nameOfRenter;
	}


	/*******************************************************************
	Helper: Turns a date into a string
	@param date a GregorianCalender date
	@return a string of the date
	*******************************************************************/
	public String dateToString(GregorianCalendar date) {
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
		String dateFormatted = fmt.format(date.getTime());
		return dateFormatted;
	}


	/*******************************************************************
	Helper: Turns a string into a date 
	@param stringDate a date in the form of a string
	@return calendarDate the GregorianCalender date that the string 
	refered to
	@throws IllegalArgumentException() if unable to parse due to 
	incorrect formatting
	@throws IllegalArgumentException() if the year is not 4-digits
	*******************************************************************/
	public GregorianCalendar toDate(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		//		Date dateDate;
		//		GregorianCalendar gregCalDate = new GregorianCalendar();
		GregorianCalendar calendarDate = new GregorianCalendar();

		try {
			calendarDate.setTime(sdf.parse(stringDate));
			//			dateDate = sdf.parse(stringDate);
			//			calendarDate.setTime(dateDate);
			//			gregCalDate = calendarDate;
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}

		if(calendarDate.get(Calendar.YEAR) < 1000){
			throw new IllegalArgumentException();
		}

		return calendarDate;
	}


	/*******************************************************************
	Helper: Turns this DVD info into a string
	@return str string of the games information
	*******************************************************************/
	public String toString() {
		String str = "Renter: " + nameOfRenter + "\t Tile: " + title; 
		str	+= "\tBought On: " + dateToString(bought);
		str += "\tDue Back On: " + dateToString(dueBack);

		return str;
	}


	/*******************************************************************
	Helper: Determines what is owned by the user based on the dueDate
	*******************************************************************/
	public String getWhatIsOwed() {
		GregorianCalendar today = new GregorianCalendar();
		String str = "$1.20 total";
		if(dueBack.compareTo(today) > 0 ){
			str = "$1.20 for the rental and $2 for being late. That is";
			str += " a total of $3.20";
		}
		return str;
	}


	/*******************************************************************
	Helper: Compares dvds due dates to whether one date is an earlier or
	later date if the dates are the same it compares the titles, 
	depends on characters values contained in the strings
	@param o the dvd in question, whether it is earlier or later than 
	the current date
	@return this.title.compareTo(o.getTitle()) an int based on equality 
	of the titles
	@return return this.dueBack.compareTo(o.getDueBack()); an int based
	on equality of the due dates
	*******************************************************************/
	public int compareTo(DVD o) {
		if (this.dueBack.compareTo(o.getDueBack()) == 0){
			return this.title.compareTo(o.getTitle());
		}else{
			return this.dueBack.compareTo(o.getDueBack());
		}

	}
}
