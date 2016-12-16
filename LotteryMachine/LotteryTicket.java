package package1;

//package Projects.P4;

import java.util.*;
import java.text.*;
/**
 * Extracts information from a lotto ticket and names different parts of it
 * 
 * @author Emily Peterson
 * @version 3-25-14
 */
public class LotteryTicket
{
    /** MegaBall */
    private int megaBall;
    
    /** Prize amount */
    private double prize;
    
    /** Name information */
    private String first;
    private String last;
    
    /** Locations information */
    private String city;
    private String state;
    private int zipCode;
    
    /** Birthday information */
    private int day;
    private int month;
    private int year;
    
    /** The numbers they choose */
    private int num1;
    private int num2;
    private int num3;
    private int num4;
    private int num5;
    
    
   /*********************************************************************
    Constructor that sets up variables with the gived ticket information
    @param Ticket information
    *********************************************************************/
    public LotteryTicket(String info) {
        String [] token = info.split(",|/");
        first = token [0].trim();
        last = token [1].trim();
        city = token [2].trim();
        state = token [3].trim();
        zipCode = Integer.parseInt(token [4].trim());
        day = Integer.parseInt(token [6].trim());
        month = Integer.parseInt(token [5].trim());
        year = Integer.parseInt(token [7].trim());
        num1 = Integer.parseInt(token [8].trim());
        num2 = Integer.parseInt(token [9].trim());
        num3 = Integer.parseInt(token [10].trim());
        num4 = Integer.parseInt(token [11].trim());
        num5 = Integer.parseInt(token [12].trim());
        megaBall = Integer.parseInt(token [13].trim());
    }


   /*********************************************************************
    @return First name of the ticket holder
    *********************************************************************/
    public String getFirst( ) {
        return first;
    }
    
    
   /*********************************************************************
    @return Last Name of the ticket holder
    *********************************************************************/
    public String getLast( ) {
        return last;
    }
    
    
   /*********************************************************************
    @return Name of the City where the ticket holder residence
    *********************************************************************/
    public String getCity( ) {
        return city;
    }
    
    
   /*********************************************************************
    @return State of the ticket holders residence
    *********************************************************************/
    public String getState( ) {
        return state;
    }
    
    
   /*********************************************************************
    @return Ticket holders zipcode
    *********************************************************************/
    public int getZipCode( ) {
        return zipCode;
    }
    
    
   /*********************************************************************
    @return Ticket holders birth day
    *********************************************************************/
    public int getDay( ) {
        return day;
    }
    
    
   /*********************************************************************
    @return Ticket holders birth month
    *********************************************************************/
    public int getMonth( ) {
        return month;
    }
    
    
   /*********************************************************************
    @return Ticket holders birth year
    *********************************************************************/
    public int getYear( ) {
        return year;
    }
    
    
   /*********************************************************************
    @return The Prize amount
    *********************************************************************/
    public double getPrize( ) {
        return prize;
    }
    
    
   /*********************************************************************
    Sets the prize to the amount given is currency format
    @param the prize amount 
    *********************************************************************/
    public void setPrize(double amount) {
        prize = amount;
    }
    
    
   /*********************************************************************
    Determines if the any of the ticket numbers matches the winning 
    balls
    @param val of a posible winning ball
    *********************************************************************/
    public boolean hasBall(int val) {
        if (val == num1 || val == num2 || val == num3 || val == num4 
            || val == num5){
            return true;
        }else{
            return false;
        }
    }
   
    
    
   /*********************************************************************
    Determines the numder val is a the same number as the winning 
    megaBall number.
    @param Val is an inputed number that is a posible winning megaBall 
    number
    *********************************************************************/
    public boolean hasMegaBall(int val) {
        if (val == megaBall){
            return true;
        }else{
            return false;
        }
    }
    
    
   /*********************************************************************
    Creates a currency format for the prize amount and creates a string 
    with all the ticket information. Then displays it is a nice format
    *@return a ticket information in a format
    *********************************************************************/
    public String toString( ) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        
        String sum = first + " " + last + "\n" + city + ", " 
                     + state + " " + zipCode + "\n" + num1 + " " + num2 
                     + " " + num3 + " " + num4 + " " + num5 + "\t" + megaBall + "\nPrize: " + fmt.format(prize);
        return sum;
    }
    
    
   /*********************************************************************
    Main Test: Tests all posibilities
    *********************************************************************/
    public static void main(String args[]) {
        LotteryTicket t = new LotteryTicket("First, Last, City, State,"
                                + " 49501, 1/01/2014, 1, 2, 3, 4, 5, 10");
        LotteryTicket t2 = new LotteryTicket("First, Last, City, State,"
                                + " 49501, 1/01/2014, 0, 0, 0, 0, 0, 0");
        
        t.toString();
        t.getFirst();
        t.getLast();
        t.getCity();
        t.getState();
        t.getZipCode();
        t.getDay();
        t.getMonth();
        t.getYear();
        t.getPrize();
        t.setPrize(2.4);
        t.getPrize();
        t.hasBall(0);
        t.hasMegaBall(0);
        System.out.println(t);
        System.out.println(t2);
    } 
}
