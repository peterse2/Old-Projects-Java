package Lab;
import java.text.*;
/********************************************************************
Simulated a Bank account with basic functions of withdrawing 
and depositing

@author Emily Peterson
@version January 30, 2013
********************************************************************/
public class BankAccount {
    
    /** Costomers Name */
    private String costomerName;
        
    /** The Account Number */
    private int acctID;
        
    /** Current Account Balance */
    private double acctBalance;

        
    /*******************************************************************
    Information to set up a new account
    *******************************************************************/
    public BankAccount(String name, int acct, double balance) {
        costomerName = name;
        acctID = acct;
        acctBalance = balance;
    }
    
    
    /********************************************************************
    Information to Set up a New Account
    ********************************************************************/
    public void makeDeposit(double amount) {
        acctBalance = acctBalance + amount;
    }
    
    
    /********************************************************************
    Withdrawal a Specific Amount From The Account
    ********************************************************************/
    public void makeWithdrawal(double amount) {
        acctBalance = acctBalance - amount;
    }
    
    
    /********************************************************************
    Displays Current Account Balance
    ********************************************************************/
    public double getBalance() {
        return acctBalance;
    }
    
    
    /********************************************************************
    Displays Costomer Name
    ********************************************************************/
    public String getName() {
        return costomerName;
    }
    
    
    /********************************************************************
    Displays Account Number
    ********************************************************************/
    public int getID() {
        return acctID;
    }
    
    
    /********************************************************************
    Displays All Account Information 
    Including Name, Account Number, and Current Balance
    ********************************************************************/
    public String toString() {
        NumberFormat ftm = NumberFormat.getCurrencyInstance();
        return costomerName + "\t" + acctID + "\t" + acctBalance;
    }
    
    
    /********************************************************************
    Displaying Joe's and Mary's Account Information
    ********************************************************************/
    public static void main(String args[]) {
        BankAccount joe = new BankAccount("Joe Smith", 5643, 1000.0);
        joe.makeDeposit(247.35);
        System.out.println(joe);

        BankAccount mary = new BankAccount("Mary Smith", 1946, 1000000.0);
        mary.makeDeposit(234.99);
        System.out.println(mary);
    }
}
