package Projects.P2;

import java.util.*;
import java.text.*;
import java.lang.*;
/***************************************************
 * Simulates a Vending Machine
 * 
 * @author Emily Peterson 
 * @version January 31, 2013
 ****************************************************/
public class VendingMachine {
    /** Credit Balance */
    private int Bal;
    
    /** Number of bottles in stock */
    private int Stock;
    
    /** Starting stock **/
    private int StartingStock;
    
    /** Total Sales */
    private int TtlSales;
    
    /** Price of one drink */
    final private int DRINK_PRICE = 125;
    
    
   /***************************************************
    Constructor: sets the credit balance and total sales
    to zero, and initial inventory to 10
    ***************************************************/
    public VendingMachine () { 
        Bal = 0;
        TtlSales = 0;
        Stock = 10;
        StartingStock = 10;
    }
    
   
   /***************************************************
    Alternative constructor: sets the credit balance and 
    total sales to 0, but allows user to set the starting 
    inventory to units.  
    ***************************************************/
    public VendingMachine (int units) {  
        Bal = 0;
        TtlSales = 0;
        
        //Asking for the invintory
        Stock = units;
    }
    
    
   /***************************************************
    Accessor: Returns the price of one drink
    ***************************************************/
    public int getPrice() {
        return DRINK_PRICE;
    }
    
    
   /***************************************************
    Accessor: Returns the current invintory
    ***************************************************/
    public int getInventory() {
        return Stock;
    } 
    
    
   /***************************************************
    Accessor: Returns costomers credit
    ***************************************************/
    public int getCredit() {
        return Bal;
    }
    
    
   /***************************************************
    Accessor: Returns the price of one drink
    ***************************************************/
    public int getTotalSales() {
        return TtlSales;
    }
    
    
   /***************************************************
    Basic: displays a brief welcome message 
    and the price on two lines
    ***************************************************/
    public void displayGreeting () {
        if (Stock == 0){
           System.out.println ("\n" + "Out of stock \nPlease try again later");
        }else{ 
            System.out.print ("\n" + "Ice cold drinks! \nPrice: " + formatDollars(DRINK_PRICE));
        }
    }
    
    
   /***************************************************
    Basic: Adds units to the inventory 
    ***************************************************/
    public void restock(int unit) {
        if (unit > 0){
            Stock += unit;
        }
    }
    
    
   /***************************************************
    Basic: Cancles purchase, so credit is set to zero 
    and the welcome message is displayed
    ***************************************************/
    public void cancelSale () {
        Bal = 0;
        
        //Displays greeting
        this.displayGreeting();
    }
    
    
   /***************************************************
    Basic: Adds add the given amount to the credit 
    balance 
    ***************************************************/
    public void insertCoin (int amount) {
        
        //Wead out bad coins
        if (amount == 5 || amount == 10 || amount== 25 || amount == 100){
            Bal += amount;
            }
    
        //Mesages when amount is put in     
        if (Stock == 0) {
            this.displayGreeting();
            Bal = 0;
        }else if (amount >= DRINK_PRICE) {
            System.out.print("\nPlease… \nMake A Selection");
            TtlSales += DRINK_PRICE;
            Bal = 0;
            Stock --;
        }else{
            System.out.print("\nCredit: " + Bal + "\nPrice: " 
                              + formatDollars(DRINK_PRICE) + "\n");
            }
    }
    
    
   /***************************************************
    Basic: Dispenses a pop if avaliable 
    ***************************************************/
    public void makeSelection ( )  {
        
        //If out of stock
        if (Stock == 0){
            System.out.print("\n" + "Out of stock \nPlease try again later");
        }
        
        //If exactly the right amount of money 
        else if (Bal == DRINK_PRICE) {
            System.out.println("\n" + "Now dispensing \nA Pepsi"); 
        }
        
        //If paid over the amount of a pop
        else if(Bal > DRINK_PRICE) {
            System.out.print("\n" + "Now dispensing \nA Pepsi and your change: " 
                              + formatDollars(Bal - DRINK_PRICE));
        }
        
        //If not enough money
        else{ 
            System.out.println("\n" + "Credit: " + Bal + "\nPrice: "
                                + formatDollars(DRINK_PRICE/100));
            }
        
        //Reseting instance variables
        Stock -= Bal / DRINK_PRICE;
        TtlSales += Bal;
        Bal = 0;
    }
    
    
   /***************************************************
    Format: Will help display numbers in a currency 
    format 
    ***************************************************/
    public String formatDollars(int amount) {
        String pricefmt;
        int d = amount/100;
        int c = amount - d*100;
        if (c < 10){
            int z = 0;
            return pricefmt = ("$" + d + "." + z + c);
        }else{
            return pricefmt = ("$" + d + "." + c);
        }
    }
    
    
   /***************************************************
    Advanced: Returns current status of invintory and 
    total sales 
    ***************************************************/
    public void displayStatus() {
        NumberFormat pricefmt = NumberFormat.getCurrencyInstance ();
        
        String status;
        status = ("\n" + "Inventory: " + Stock + "\nTotal Sales: " 
                   + formatDollars(TtlSales));
                                    
        //if (TTL_SALES ==2){
           System.out.println("\n" + status);
        //}
    }
    
    
   /***************************************************
    Format: Will help display numbers in a currency 
    format 
    ***************************************************/
    public void simulateSales(int sales) {
        
        //VendingMachine.restock(int units);
        Stock += sales;
        
        while (sales > 0) {
            
            //Simulating inserting coin
            this.insertCoin(100);
            this.insertCoin(25);
            
            //Simulating making a selection
            this.makeSelection();
            
            //Result of one simulation
            sales --;
        }
        this.displayStatus();
    }
    
    
   /***************************************************
    Format: Will help display numbers in a currency 
    format 
    ***************************************************/
    public void clearStock() {
        Stock = 0;
    }
    
    
   /***************************************************
    Main: Tests all posibilities
    ***************************************************/
    public static void main (String [] args){
        //Naming two vending machines
        VendingMachine v1 = new VendingMachine ();
        VendingMachine v2 = new VendingMachine ();
        
        //Have pop in stock
        v1.displayStatus();
        v1.getPrice();
        v1.getInventory();
        v1.getCredit();
        v1.getTotalSales();
        v1.displayGreeting();
        v1.restock(1);
        v1.insertCoin(100);
        v1.insertCoin(25);
        v1.makeSelection();
        v1.simulateSales(1);
        v1.displayStatus();
        v1.cancelSale();
        v1.displayStatus();
        
        //Out of order
        v2.clearStock();
        v2.displayStatus();
        v2.getPrice();
        v2.getInventory();
        v2.getCredit();
        v2.getTotalSales();
        v2.displayGreeting();
        v2.restock(-1);
        v2.insertCoin(0);
        v2.makeSelection();
        v2.simulateSales(0);
        v2.displayStatus();
        v2.cancelSale();
        v2.displayStatus();
        
        //print results
        System.out.print(v1);
        System.out.print(v2);
    } 
}
