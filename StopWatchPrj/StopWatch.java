package package1;

//import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

//import org.junit.Test;

/*******************************************************************
Simulation of a working stop watch 

@author Emily Peterson
@version 8/28/14
*******************************************************************/

public class StopWatch {

	/** Minutes */
	private int minutes;

	/** Seconds */
	private int seconds;

	/** Milliseconds */
	private int milliseconds;

	/**Total time in milliseconds*/
	private int total;

	/** Determines if changes to the time are allowed */
	private static boolean myMutate = true;

	/***************************************************************
	Constructor: Sets the stop watch to zero  
	 ***************************************************************/
	public StopWatch() {
		minutes = 0;
		seconds = 0;
		milliseconds = 0;
		total = 0;
	}


	/***************************************************************
	Constructor: Initializes the all instance variables with 
	provided variables
	@param minutes is the provided minutes 
	@param seconds is the provided seconds
	@param milliseconds is the provided milliseconds
	@throws {IndexOutOfBoundsException} if one of entries are negative
	@throws {IndexOutOfBoundsException} if to many seconds or 
	milliseconds were entered
	 ***************************************************************/
	public StopWatch( int minutes, int seconds, int milliseconds) {
		if (minutes < 0 || seconds < 0 || milliseconds < 0){
			throw new IndexOutOfBoundsException(); 
		}if (seconds >= 60 || milliseconds >= 1000){
			throw new IndexOutOfBoundsException();
		}

		//		if(milliseconds >= 10 && milliseconds < 100){
		//			milliseconds = trimInt(milliseconds,10);
		//		}

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
		this.total = 0;

	}


	/***************************************************************
	Constructor: Initializes the all instance variables with 
	provided variables 
	@param seconds is the provided seconds
	@param milliseconds is the provided milliseconds
	@throws {IndexOutOfBoundsException} if one of entries are negative
	@throws {IndexOutOfBoundsException} if to many seconds or 
	milliseconds were entered
	 ***************************************************************/
	public StopWatch(int seconds, int milliseconds) {

		//Checking for valid entries
		if (seconds < 0 || milliseconds < 0){
			throw new IndexOutOfBoundsException(); 
		}if (seconds >= 60 || milliseconds >= 1000){
			throw new IndexOutOfBoundsException();
		}

		this.minutes = 0;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
		total = 0;
	}


	/***************************************************************
	Constructor: Initializes the all instance variables with 
	provided variables
	@param milliseconds is the provided milliseconds
	@throws {IndexOutOfBoundsException} if the entry is negative or 
	milliseconds were entered
	 ***************************************************************/
	public StopWatch(int milliseconds){

		//Checking for valid entries
		if (milliseconds < 0 || milliseconds >= 1000){
			throw new IndexOutOfBoundsException(); 
		}

		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = milliseconds;
		total = 0;
	}


	/***************************************************************
	Constructor: Takes the given string that is in one of three 
	formats and separates it into using the colons and assigns 
	the values to the correct instance variable. 
	@param startTime the time that the stop watch starts
	@throws {IllegalArgumentException} Not proper entry
	 ***************************************************************/
	public StopWatch(String startTime) {
		this.minutes = 0;
		this.seconds = 0;
		this.milliseconds = 0;



		String [] token = startTime.split(":");

		if(token.length == 3){
			this.minutes = Integer.parseInt(token [0].trim());
			this.seconds = Integer.parseInt(token [1].trim());
			this.milliseconds = Integer.parseInt(token [2].trim());

		}else if(token.length == 2) {
			this.seconds = Integer.parseInt(token [0].trim());
			this.milliseconds = Integer.parseInt(token [1].trim());
		}else if(token.length == 1){ 
			milliseconds = Integer.parseInt(token [0].trim());
		}else{
			throw new IllegalArgumentException();
		}
		
		checkForValidEntry();


	}


	/***************************************************************
	Getter: retrieves the number of minutes that that current 
	StopWatch has 
	@return m number of minutes that that current StopWatch has
	 ***************************************************************/
	public int getM() {
		return minutes;
	}


	/***************************************************************
	Getter: retrieves the number of seconds that that current 
	StopWatch has 
	@return s number of seconds that that current StopWatch has
	 ***************************************************************/
	public int getS() {
		return seconds;
	}


	/***************************************************************
	Getter: retrieves the number of milliseconds that that current 
	StopWatch has 
	@return ml number of milliseconds that that current StopWatch has
	 ***************************************************************/
	public int getMl() {
		return milliseconds;
	}

	/***************************************************************
	Getter: retrieves the time of the StopWatch in milliseconds
	@return total time in milliseconds that that current StopWatch 
	has
	 ***************************************************************/
	public int getTotal() {
		return total;
	}

	/***************************************************************
	 Helper:Check for a Valid entry
	 @throws {IndexOutOfBoundsException} there was a negative number
	 entered
	 @throws {IndexOutOfBoundsException} there was to large of an 
	 entry for seconds or milliseconds 
	 ***************************************************************/
	private void checkForValidEntry() {
		if (this.minutes < 0 || this.seconds < 0 || this.milliseconds < 0){
			throw new IndexOutOfBoundsException();
		}if (this.seconds >= 60 || this.milliseconds >= 1000){
			throw new IndexOutOfBoundsException();
		}
	}


	//	/***************************************************************
	//	 Helper:Check for a Valid entry
	//	***************************************************************/
	//	static int trimInt(int i, int place) {
	//		i /= place;
	//		i *= place;
	//		return i;
	//	}


	/***************************************************************
	Determines if the current StopWatch has the same time as 
	the other StopWatch
	@param other is a given StopWatch with a time
	@return true if two StopWatches have the same time
	@return false if the two StopWatches are not the same time
	 ***************************************************************/
	public boolean equals(StopWatch other) {
		other.checkForValidEntry();

		if(this.minutes == other.minutes  && this.seconds == other.seconds && 
				this.milliseconds == other.milliseconds) {
			return true;
		}else{
			return false;
		}
	}


	/***************************************************************
	Casts the object other into a StopWatch then compares it to the 
	current StopWatch to determine if they are the same
	@param other is a given Object with a time
	@return true if two StopWatches have the same time
	@return false if the two StopWatches are not the same time 
	 ***************************************************************/
	public boolean equals(Object other) {
		StopWatch newOther = (StopWatch) other;

		newOther.checkForValidEntry();

		if(this.minutes == newOther.minutes  && this.seconds == newOther.seconds && 
				this.milliseconds == newOther.milliseconds) {
			return true;
		}else{
			return false;
		}
	}


	/***************************************************************
	Compares the two given StopWatches to determine if they are the 
	same
	@param other is a given StopWatch with a time 
	@return true if s1 and s2 have the same time
	@return false if s1 and s2 do not have the same time
	 ***************************************************************/
	public static boolean equals(StopWatch s1, StopWatch s2) {
		s1.checkForValidEntry();
		s2.checkForValidEntry();

		if(s1.minutes == s2.minutes && s1.seconds == s2.seconds && 
				s1.milliseconds == s2.milliseconds ){
			return true;
		}else{
			return false;
		}
	}

	/***************************************************************
	Compares the current StopWatch to the given StopWatch time. 
	@param other a stopWatch object provided by the user
	@return 0 if the current StopWatch has the same time as other
	@return -1 if the current StopWatch time is less than or a shorter
	 time than the other StopWatch 
	@return 1 if the current StopWatch has a greater time than other
	@throws {IllegalArgumentException} Invalid Entry
	 ***************************************************************/
	public int compareTo(StopWatch other){
		int currentTotal, otherTotal;

		other.checkForValidEntry();

		//Convert Both StopWatches to milliseconds
		currentTotal = this.minutes*60000 + this.seconds*1000 
						+ this.milliseconds;
		otherTotal = other.minutes*60000 + other.seconds*1000 
						+ other.milliseconds;

		if(currentTotal == otherTotal){
			return 0;
		}else if(currentTotal < otherTotal){
			return -1;
		}else if(currentTotal > otherTotal){
			return 1;
		}else{
			throw new IllegalArgumentException();
		}
	}


	/***************************************************************
	Adds the given number of milliseconds to the current time
	@param milliseconds given number of milliseconds
	@throws {IndexOutOfBoundsExeption} Negative number was entered
	 ***************************************************************/
	public void add(int milliseconds){

		//Checks for Valid entry of a positive number
		if (milliseconds < 0){
			throw new IndexOutOfBoundsException();
		}

		if (myMutate == true){

			//Converts StopWatch to milliseconds
			total = this.minutes*60000 + this.seconds*1000 
					+ this.milliseconds;

			//Adds the given milliseconds;
			total += milliseconds;

			//Reformatting time back into min sec and millsec
			this.minutes = total/60000;
			total %= 60000;
			this.seconds = total/1000;
			total %= 1000;
			this.milliseconds = total;
		}
	}


	/***************************************************************
	Adds a given time to the current StopWatch to the current time
	@param other given StopWatch
	 ***************************************************************/
	public void add(StopWatch other){
		other.checkForValidEntry();

		if (myMutate == true){

			//Converts the other into milliseconds
			total = other.minutes*60000 + other.seconds*1000 
					+ other.milliseconds;

			this.add(total);
		}
	}	


	/***************************************************************
	Increments the current StopWatch by one millisecond
	 ***************************************************************/
	public void inc(){
		if (myMutate == true){
			add(1);
		}
	}


	/***************************************************************
	Displays the StopWatch in a format as a string
	@return time StopWatch time in a format as a string
	@throws {IndexOutOfBoundsException} Entry for minutes was negative
	@throws {IndexOutOfBoundsException} Entry for seconds was negative
	@throws {IndexOutOfBoundsException} Entry for milliseconds was 
	negative
	 ***************************************************************/
	public String toString(){
		String time, sec, min, mill;
		min = new String("0");
		sec = new String ("00");
		mill = new String("000");

		//Adding the correct minutes the string
		if (this.minutes >= 0)
			min = "" + this.minutes;
		else
			throw new IndexOutOfBoundsException();

		//Adding the correct minutes the string
		if (this.seconds < 10 && this.seconds >= 0)
			sec = "0" + this.seconds;
		else if (this.seconds > 9 && this.seconds < 60)
			sec = "" + this.seconds;
		else
			throw new IndexOutOfBoundsException();

		//Adding the correct minutes the string
		if (this.milliseconds >= 0){
			if (this.milliseconds < 10)
				mill = "00" + milliseconds;
			else if (this.milliseconds < 100)
				mill = "0" + milliseconds;
			else
				mill = "" + milliseconds;
		}else
			throw new IndexOutOfBoundsException();

		time = min + ":" + sec + ":" + mill;
		return time;
	}

	/******************************************************************
	Saves a String to a file
	@param fileName name of file being saved
	 ******************************************************************/
	public void save(String fileName){
		PrintWriter sc;

		try{
			sc = new PrintWriter(new BufferedWriter(
					new FileWriter(fileName)));

			sc.println(this);

			sc.close();

		}catch(FileNotFoundException error) {
			System.out.println("File not found");
		}catch(IOException error) {
			System.out.println("CRAP! Something went wrong.");
		}

	}


	/******************************************************************
	Loads a given file
	@param fileName the file being loaded
	 ******************************************************************/
	public void load(String fileName){
		Scanner sc;
		String info;

		try{
			sc = new Scanner(new File(fileName));

			//continue while there is no more data to read
			info = sc.nextLine();

			StopWatch temp = new StopWatch (info);
			minutes = temp.minutes;
			seconds = temp.seconds;
			milliseconds = temp.milliseconds;

			sc.close();
			
		}catch(FileNotFoundException error) {
			System.out.println("File not found");
		}
//		}catch(IOException error) {
//			System.out.println("CRAP! Something went wrong.");
//		}
	}

	/******************************************************************
	Turns on and off the power to change a time on a StopWatch
	@param mutate
	 ******************************************************************/
	public static void setMutate(boolean mutate){
		myMutate = mutate;
	}

	/******************************************************************
	Main Method testing all methods above 
	@param args
	 ******************************************************************/
	public static void main(String[] args) {

		StopWatch s = new StopWatch("20:10:8");
		System.out.println("Time: " + s);

		s = new StopWatch("20:8");
		System.out.println("Time: " + s);

		s = new StopWatch("8");
		System.out.println("Time: "+ s);

		s = new StopWatch("8:00:000");
		System.out.println("Time: "+ s);

		s = new StopWatch("0:8:000");
		System.out.println("Time: "+ s);

		s = new StopWatch("0:00:8");
		System.out.println("Time: "+ s);

		StopWatch s1 = new StopWatch(20, 2, 200);
		System.out.println("Time: " + s1);

		if (!StopWatch.equals (s,  s1))
			System.out.println("s is Not equal to s1");

		s1.add(1000);
		System.out.println("Time: " + s1);

		StopWatch s2 = new StopWatch(40, 10, 20);
		s2.add(100);
		for (int i = 0; i < 4000; i++)
			s2.inc();
		System.out.println("Time: " + s2);	

		s = new StopWatch("5:59:300");
		StopWatch.setMutate(false);
		s.add(1000);
		System.out.println(s);

		s = new StopWatch("5:59:300");
		StopWatch.setMutate(true);
		s.add(1000);
		System.out.println(s);

		System.out.println(s.toString());

		s = new StopWatch(0,0,0);
		s.add(3);
		System.out.println(s.toString());


		//Test Default constructor 
		System.out.println("Testing Default Constructor");

		s = new StopWatch();
		System.out.println("Correct: 0:00:000 Result: " + s + "\n");


		//Test Three Parameter Constructor
		System.out.println("Testing Three Parameter Constructor");

		s = new StopWatch(0,0,0);
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch(1,2,3);
		System.out.println("Correct: 1:02:003 Result: " + s);

		s = new StopWatch(1,21,31);
		System.out.println("Correct: 1:21:031 Result: " + s);

		s = new StopWatch(1,21,311);
		System.out.println("Correct: 1:21:311 Result: " + s + "\n");


		//Test Two Parameter Constructor
		System.out.println("Testing Two Parameter Constructor");

		s = new StopWatch(0,0);
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch(2,3);
		System.out.println("Correct: 0:02:003 Result: " + s);

		s = new StopWatch(21,31);
		System.out.println("Correct: 0:21:031 Result: " + s);

		s = new StopWatch(21,311);
		System.out.println("Correct: 0:21:311 Result: " + s + "\n");


		//Test One Parameter Constructor
		System.out.println("Testing One Parameter Constructor");

		s = new StopWatch(0);
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch(3);
		System.out.println("Correct: 0:00:003 Result: " + s);

		s = new StopWatch(31);
		System.out.println("Correct: 0:00:031 Result: " + s);

		s = new StopWatch(311);
		System.out.println("Correct: 0:00:311 Result: " + s + "\n");

		//Test String Constructor
		System.out.println("Testing String Constructor");

		s = new StopWatch("0:0:0");
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch("1:2:3");
		System.out.println("Correct: 1:02:003 Result: " + s);

		s = new StopWatch("1:21:31");
		System.out.println("Correct: 1:21:031 Result: " + s);

		s = new StopWatch("1:21:311");
		System.out.println("Correct: 1:21:311 Result: " + s);

		s = new StopWatch("0:0");
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch("2:3");
		System.out.println("Correct: 0:02:003 Result: " + s);

		s = new StopWatch("21:31");
		System.out.println("Correct: 0:21:031 Result: " + s);

		s = new StopWatch("21:311");
		System.out.println("Correct: 0:21:311 Result: " + s);

		s = new StopWatch("0");
		System.out.println("Correct: 0:00:000 Result: " + s);

		s = new StopWatch("3");
		System.out.println("Correct: 0:00:003 Result: " + s);

		s = new StopWatch("31");
		System.out.println("Correct: 0:00:031 Result: " + s);

		s = new StopWatch("311");
		System.out.println("Correct: 0:00:311 Result: " + s + "\n");

		//Test equals stopWatch method
		System.out.println("Testing StopWatch equals method");

		s1 = new StopWatch(0,0,0);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: True Result: " + s1.equals(s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: False Result: " + s1.equals(s2));

		s1 = new StopWatch(1,21,31);
		s2 = new StopWatch(2,3,5);
		System.out.println("Expected: False Result: " + s1.equals(s2)
				+ "\n");

		//Test equals two StopWatch
		System.out.println("Testing two StopWatch equals method");

		s1 = new StopWatch(0,0,0);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: True Result: " + equals(s1,s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(1,2,3);
		System.out.println("Expected: True Result: " + equals(s1,s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: False Result: " + equals(s1,s2));

		s1 = new StopWatch(1,21,31);
		s2 = new StopWatch(2,3,5);
		System.out.println("Expected: False Result: " + equals(s1,s2)
				+ "\n");


		//Test compareTo method
		System.out.println("Testing compareTo method");

		s1 = new StopWatch(0,0,0);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: 0 Result: " 
				+ s1.compareTo(s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(1,2,3);
		System.out.println("Expected: 0 Result: " 
				+ s1.compareTo(s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: 1 Result: " 
				+ s1.compareTo(s2));

		s1 = new StopWatch(1,2,3);
		s2 = new StopWatch(0,0,0);
		System.out.println("Expected: -1 Result: " 
				+ s2.compareTo(s1));

		s1 = new StopWatch(1,21,31);
		s2 = new StopWatch(2,3,5);
		System.out.println("Expected: 1 Result: " 
				+ s2.compareTo(s1));

		s1 = new StopWatch(1,21,31);
		s2 = new StopWatch(2,3,5);
		System.out.println("Expected: -1 Result: " 
				+ s1.compareTo(s2) + "\n");


		//Test add integer method
		System.out.println("Testing add integer method");

		s = new StopWatch(0,0,0);
		s.add(3);
		System.out.println("Expected: 0:00:003 Results: " + s);

		s = new StopWatch(2,30,980);
		s.add(20);
		System.out.println("Expected: 2:31:000 Results: " + s);

		s = new StopWatch(1,59,999);
		s.add(1);
		System.out.println("Expected: 2:00:000 Results: " + s);

		s = new StopWatch(2,59,999);
		s.add(70031);
		System.out.println("Expected: 4:10:030 Results: " + s);

		s = new StopWatch(0,0,0);
		s.add(999);
		System.out.println("Expected: 0:00:999 Results: " + s);

		s = new StopWatch(0,0,0);
		s.add(59000);
		System.out.println("Expected: 0:59:000 Results: " + s);

		s = new StopWatch(0,0,0);
		s.add(60000);
		System.out.println("Expected: 1:00:000 Results: " + s);

		s = new StopWatch(0,0,0);
		StopWatch.setMutate(false);
		s.add(20);
		System.out.println("Expected: 0:00:000 Results: " + s + "\n");


		//Test Add StopWatch method
		System.out.println("Testing add StopWatch method"); 

		StopWatch.setMutate(true);

		s = new StopWatch(0,0,0);
		s1 = new StopWatch(0,0,3);
		s.add(s1);
		System.out.println("Expected: 0:00:003 Results: " + s);

		s = new StopWatch(2,30,980);
		s1 = new StopWatch(0,0,20);
		s.add(s1);
		System.out.println("Expected: 2:31:000 Results: " + s);

		s = new StopWatch(1,59,999);
		s1 = new StopWatch(0,0,1);
		s.add(s1);
		System.out.println("Expected: 2:00:000 Results: " + s);

		s = new StopWatch(2,59,999);
		s1 = new StopWatch(2,11,031);
		s.add(s1);
		System.out.println("Expected: 4:10:030 Results: " + s);

		s = new StopWatch(0,0,0);
		s1 = new StopWatch(0,0,999);
		s.add(s1);
		System.out.println("Expected: 0:00:999 Results: " + s);

		s = new StopWatch(0,0,0);
		s1 = new StopWatch(0,59,0);
		s.add(s1);
		System.out.println("Expected: 0:59:000 Results: " + s);

		s = new StopWatch(0,0,0);
		s1 = new StopWatch(1,0,0);
		s.add(s1);
		System.out.println("Expected: 1:00:000 Results: " + s);

		s = new StopWatch(0,0,10);
		s1 = new StopWatch(0,0,10);
		setMutate(false);
		s.add(s1);
		System.out.println("Expected: 0:00:010 Results: " + s + "\n");


		//Test Increment Method 
		System.out.println("Testing add StopWatch method");

		s = new StopWatch(0,0,0);
		s.inc();
		System.out.println("Expected: 0:00:001 Results: " + s);

		s = new StopWatch(1,59,999);
		s.inc();
		System.out.println("Expected: 1:00:000 Results: " + s);

		s = new StopWatch(1,59,999);
		setMutate(false);
		s.inc();
		System.out.println("Expected: 1:59:999 Results: " + s + "\n"); 

		//Test toString method
		System.out.println("Testing toString method");
		
		s = new StopWatch(0,0,0);
		System.out.println("Correct: 0:00:000 Result: " + s.toString());

		s = new StopWatch(1,2,3);
		System.out.println("Correct: 1:02:003 Result: " + s.toString());

		s = new StopWatch(1,21,31);
		System.out.println("Correct: 1:21:031 Result: " + s.toString());

		s = new StopWatch(1,21,31);
		System.out.println("Correct: 1:21:311 Result: " + s.toString());

		s = new StopWatch(0,0);
		System.out.println("Correct: 0:00:000 Result: " + s.toString());

		s = new StopWatch(2,3);
		System.out.println("Correct: 0:02:003 Result: " + s.toString());

		s = new StopWatch(21,31);
		System.out.println("Correct: 0:21:031 Result: " + s.toString());

		s = new StopWatch(21,31);
		System.out.println("Correct: 0:21:311 Result: " + s.toString());

		s = new StopWatch(0);
		System.out.println("Correct: 0:00:000 Result: " + s.toString());

		s = new StopWatch(3);
		System.out.println("Correct: 0:00:003 Result: " + s.toString());

		s = new StopWatch(31);
		System.out.println("Correct: 0:00:031 Result: " + s.toString());

		s = new StopWatch(311);
		System.out.println("Correct: 0:00:311 Result: " + s.toString()
				+ "\n");
		
		
		//Test Load and save
		System.out.println("Testing toString method \n");
		
		s = new StopWatch (0,43,6);
		s.save("file1");
		
		//Test mutate method
		System.out.println("Testing mutate method");
		
		s = new StopWatch(0,0,0);
		setMutate(true);
		System.out.print("Boolean condition: true Before: " + s);
		s.add(1);
		System.out.println(" After add: " + s);
		
		s = new StopWatch(0,0,0);
		setMutate(false);
		System.out.print("Boolean condition: false Before: " + s);
		s.add(1);
		System.out.println(" After add: " + s);
		
		s = new StopWatch(1,59,999);
		setMutate(true);
		System.out.print("Boolean condition: true Before: " + s);
		s.add(1);
		System.out.println(" After add: " + s);
		
		s = new StopWatch(1,59,999);
		setMutate(false);
		System.out.print("Boolean condition: false Before: " + s);
		s.add(1);
		System.out.println(" After add: " + s + "\n");
		
		//Test Getters
		System.out.println("Testing Getter methods \n");
		
		s = new StopWatch(1,2,3);
		System.out.println("Minutes:1 Seconds:2 Milliseconds:3 "
							+ "Results: Minutes:" +  s.getM() 
							+ " Seconds:" + s.getS() 
							+ " Milliseconds:" + s.getMl());
		
		
		//Test check for valid entry
		System.out.println("Testing checkForValidEntry methods \n");
	}
}
