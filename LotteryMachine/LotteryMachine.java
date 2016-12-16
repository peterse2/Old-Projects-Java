package package1;

import java.util.*;
import java.text.*;
import java.io.*;
/**
 * Write a description of class LotteryMachine here.
 * 
 * @author Emily Peterson
 * @version 3-25-14
 */
public class LotteryMachine
{
    /** Prize Amount */
    private ArrayList <LotteryTicket> tickets;
    
    /** Winning Numbers */
    private int ball1, ball2, ball3, ball4, ball5;
    private int megaBallWinner;
    
    /** Prize Amount */
    private double prize;
    
    /** Total Amount of Prizes Gave Away */
    private double ttl;
    
    /** Mega Prize */
    private double megaPrize;
    
    /** If ticket holder wins the megaPrize */
    private boolean megaWinner;
    
   /*********************************************************************
    Constructor: Creates 2 empty ArrayLists. The first one 
    for the lottery tickets and the second one holds the 
    five winning numbers   
    *********************************************************************/
    public LotteryMachine( ) {
        tickets = new ArrayList <LotteryTicket> ();
        
        ttl = 0;
        prize = 0;
        megaPrize = 5000000;
        megaWinner = false;
    }

    
   /*********************************************************************
    Adds the Lottery Ticket t to the empty ArrayList tickets 
    @param a new Lottery ticket called t
    *********************************************************************/
    public void addTicket(LotteryTicket t) {
        tickets.add(t);   
    }
    
    
   /*********************************************************************
    Displays the number of tickets that are in the ArrayList
    @return the number of tickets in the ArrayList 
    *********************************************************************/
    public int countTickets( ) {
        return tickets.size();
    }
    
    
   /*********************************************************************
    Picks all the winning numbers for the lottery
    *********************************************************************/
    private void pickNumbers( ) {
        Random gen = new Random();
        
        //ArrayList for list of numbers 1-75
        ArrayList <Integer> numList = new ArrayList <Integer> ();
        
        //ArrayList fot the 5 winning numbers
        int[] winningNum = new int[5];
        
        //Puts the numbers 1-75 into an ArrayList
        for (int count = 1; count <= 75; count++){
            numList.add(count);
        }
        
        //Generating 5 different random numbers and puts them into an ArrayList. 
        //The random number is removed from the randomNum arrayList once picked to prevent duplicates 
        for (int count = 0; count <= 4; count++){
            int num = gen.nextInt(numList.size());
            numList.remove(num);
            winningNum[count] = num;
        }
        
        //Sorts the numbers in accending order //why? this would skew results
        Arrays.sort(winningNum);
        
        //Updates the instance variable
        ball1 = winningNum[0];
        ball2 = winningNum[1];
        ball3 = winningNum[2];
        ball4 = winningNum[3];
        ball5 = winningNum[4];

        //Assigns the megaBall a random number between 1 and 15
        megaBallWinner = gen.nextInt(14) + 1;
    }
    
    
   /*********************************************************************
    Counts how namy numbers in the lottery ticket t matched the winning 
    numbers.
    @returns the number of matches
    @param a new Ticket called t
    *********************************************************************/
    private int countMatches(LotteryTicket t) {
        int match = 0;
        
        if (t.hasBall(ball1)){
            match  ++;
        }
        
        if (t.hasBall(ball2)){
            match  ++;
        }
        
        if (t.hasBall(ball3)){
            match  ++;
        }
        
        if (t.hasBall(ball4)){
            match  ++;
        }
        
        if (t.hasBall(ball5)){
            match  ++;
        }
        
        return match;
    }
    
    
   /*********************************************************************
    Determines if a ticket contains the winning megaBall
    *********************************************************************/
    private int containsMegaBall(LotteryTicket t) {
        int megaMatch;
        
        if (t.hasMegaBall(megaBallWinner)){
            megaMatch = 1; 
        }else{
            megaMatch = 0;
        }
        
        return megaMatch;
    }
    
    
   /*********************************************************************
    Goes through all the tickects and counts how many winning numbers it 
    contains, then assigns them the right prize amount. Also, the total 
    prize giveaway is calculated
    *********************************************************************/
    private void makePayouts( ) {
        for (LotteryTicket l: tickets){
            int m = countMatches(l);
            int megaMatch = containsMegaBall(l);
            
            if (m == 0 && megaMatch == 0){
                prize = 0;
            }else if(m == 0 && megaMatch == 1){
                prize = 1;
            }else if(m == 1 && megaMatch == 1){
                prize = 2; 
            }else if(m == 2 && megaMatch == 1){
                prize = 5;
            }else if(m == 3 && megaMatch == 0){
                prize = 5;
            }else if(m == 3 && megaMatch == 1){
                prize = 50;
            }else if(m == 4 && megaMatch == 0){
                prize = 500;
            }else if(m == 4 && megaMatch == 1){
                prize = 5000;
            }else if(m == 5 && megaMatch == 0){
                prize = 1000000;
            }else if(m == 5 && megaMatch == 1){
                prize = megaPrize;
                megaWinner = true;
            }
            
            l.setPrize(prize);
            
            //Update total prize amount
            ttl += prize;
        }
    }
    
    
   /*********************************************************************
    Displays all the winning numbers in a line or string
    @return a new string of the winning numbers having the megaBall last
    *********************************************************************/
    private String formatNumbers( ) {
        String str = new String();
        str = "Selected numbers: " + ball1 + " " + ball2 + " " + ball3 
              + " " + ball4 + " " + ball5 + "\t" + megaBallWinner;
        
        return str;
    }
    
    
   /*********************************************************************
    Picks the winning numbers and assigns the correct award to all the 
    tickets 
    *********************************************************************/
    public void drawTicket( ) {
        pickNumbers();
        makePayouts();
    }
    
    
   /*********************************************************************
    Assigsn the provided values to the six numbers and then assigns all 
    the tickets the correct awards. Used for testing.
    @param test lotteryTicket numbers
    *********************************************************************/
    public void drawTicket(int b1, int b2, int b3, int b4, int b5, int m ) { 
        ball1 = b1;
        ball2 = b2;
        ball3 = b3;
        ball4 = b4;
        ball5 = b5;
        megaBallWinner = m;
        
        makePayouts();
    }
    
    
   /*********************************************************************
    Opens a filename and reads all the data
    @param a new String called filename that holds about 50,000 tickets 
    *********************************************************************/
    public void readTickets(String filename) {
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            String info;
            
            //continue while there is no more data to read
            while (sc.hasNext()) {
                info = sc.nextLine();
                
                LotteryTicket t = new LotteryTicket(info);
                tickets.add(t);
            }
            sc.close();
            
            }catch(FileNotFoundException error) {
                System.out.println("File not found ");
            }
    }
    
    
   /*********************************************************************
    Creates a short report for all tickets that include the six selected 
    numbers, number of tickets average price anount and the biggest 
    winner
    @return a short report of some of the results and lottery information
    *********************************************************************/
    public String createReport( ) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        double ave = ttl/countTickets();
        if (ttl == 0){
            ave = 0.00;
        }
            
        //Summary of all Sales
        String sumAll = new String();
        sumAll = "Report for All Sales\n" + formatNumbers(); 
        sumAll += "\nTickects Sold: " + countTickets(); 
        sumAll += "Average Prize: " + fmt.format(ave);
        
        //Biggest winner information
        String bigWinner = new String();
        bigWinner = "Biggest Winner\n" + getBiggestWinner();

        
        //Displays both strings
        return sumAll + bigWinner;
    }
    
    
   /*********************************************************************
    Creates a report for only the lottery tickest sold in the state of st
    @return infomation from createRoport
    *********************************************************************/
    public String createReport(String st) {
        int sold = 0;
        double total = 0;
        LotteryTicket biggest = tickets.get(0);
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        String stReport;
        
        for (LotteryTicket l: tickets){
            biggest = l;
            if ( l.getState().equalsIgnoreCase(st)){
                sold ++;
                total += l.getPrize();
                 if (l.getPrize() >= biggest.getPrize()){
                    biggest = l;
                }
            }
        }
        if (sold > 0){
            //if (total > 0){
                //double average = total/sold;
            //}else{
                //double average = 0.00;
            //}
            stReport = "Report About " + st + ": ";
            stReport += "Number of Tickets Sold: " + sold;
            stReport += "Average Prize: " + fmt.format(total/sold);
            stReport += "Biggest Winner: " + biggest.toString();
        }else{
            sold = 0;
            total = 0.00;
            stReport = "Report About " + st + ": ";
            stReport += "Number of Tickets Sold: " + sold;
            stReport += "Average Prize: " + fmt.format(total);
        }
        
        return stReport;
    }
    
    
   /*********************************************************************
    Finds the oldest ticket holder according to year, months, and days
    @return the ticket holder with the 
    *********************************************************************/
    public LotteryTicket getOldestPlayer( ) {     
        int maxYear;
        int maxMonth;
        int maxDay;
        
        LotteryTicket oldest = tickets.get(0);
        maxYear = oldest.getYear();
        maxMonth = oldest.getMonth();
        maxDay = oldest.getDay();
        
        for (LotteryTicket l: tickets){
            int year = l.getYear();
            int month = l.getMonth();
            int day = l.getDay();
            
            //Takes years, months, and days into account
            //if(t.getyear() < oldest.getYear())
            if (year <= maxYear && month <= maxMonth && day <= maxDay){
                maxYear = year;
                maxMonth = month;
                maxDay = day;
                
                //String to display first and last name of the ticket holder
                oldest = l;
            }
        }
        
        return oldest;
    }
    
    
   /*********************************************************************
    Finds the biggest prize winner
    @returns the ticket holder that one the largest prize amount
    *********************************************************************/
    public LotteryTicket getBiggestWinner( ) {
        double amount; 
        double max = 0;
        LotteryTicket big = tickets.get(0);
        
        for (LotteryTicket l: tickets){
            amount = l.getPrize();
            if (amount >= max){
                max = amount;
                big = l;
            }
        }
       
        return big;
    }
    
    
   /*********************************************************************
    Creates a ArrayList containing the tickets that won a pize that is 
    bigger than the indicated amount
    
    @param Prize amount that is considered a major winner
    @return What is defined as a major winning tickets based on the 
    amount given 
    *********************************************************************/
    public ArrayList <LotteryTicket> getMajorWinners(double amount) {
        ArrayList <LotteryTicket> majorWinner = new ArrayList <LotteryTicket> ();
        
        for (LotteryTicket m : tickets){
            if (m.getPrize() >= amount){
                majorWinner.add(m);
            }
        }
        
        return majorWinner;
    }
    
    
   /*********************************************************************
    Adds the Lottery Ticket t to the empty ArrayList 
    *********************************************************************/
    public String multipleGames( ) {
        String str = new String();
        int count = 0;

            do {
                drawTicket();
                count ++;
                megaPrize += 150000000;
            }while (!megaWinner); 
            

        
        str = "Games until Jackpot: " + count;
        str += "\n\n\n" + getBiggestWinner();
        
        return str;
    }
    
    
   /*********************************************************************
    Adds the Lottery Ticket t to the empty ArrayList 
    *********************************************************************/
    public static void main(String args []) {
        LotteryMachine m = new LotteryMachine();
        //info = "Oldest, Person;
        
        m.readTickets("TicketInfo.txt");
        //System.out.println(m.multipleGames());
        //m.drawTicket();
        //System.out.println(m.getOldestPlayer());
        System.out.println(m.getMajorWinners(10.00));
    }
}
