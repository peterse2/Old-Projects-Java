package Projects.P2;

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The test class VendingTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VendingTest
{
    VendingMachine v;
    private final int PRICE = 125;
    /**
     * Default constructor for test class VendingTest
     */
    public VendingTest()
    {
        v = new VendingMachine();
    }

    /******************************************************
     * Test initial values of the constructor
     *****************************************************/
    @Test 
    public void testConstructor()
    {
        VendingMachine v = new VendingMachine();    
        Assert.assertEquals("VendingMachine(): inventory not set to 10", 
                10, v.getInventory());       
        Assert.assertEquals("VendingMachine(): price not set to 125", 
                PRICE, v.getPrice());  
        Assert.assertEquals("VendingMachine(): credit should start at 0", 
                0, v.getCredit());                  
        Assert.assertEquals("VendingMachine(): sales should start at 0", 
                0, v.getTotalSales()); 
    }  
 

    /******************************************************
     * Test initial values of the constructor
     *****************************************************/
    @Test 
    public void testSecondConstructor()
    {
        VendingMachine v = new VendingMachine(25);    
        Assert.assertEquals("VendingMachine(25): inventory not set to 25", 
                25, v.getInventory());       
        Assert.assertEquals("VendingMachine(25): price not set to 125", 
                PRICE, v.getPrice());  
        Assert.assertEquals("VendingMachine(25): credit should start at 0", 
                0, v.getCredit());                  
    }     
    /******************************************************
     * Test insert Coin
     *****************************************************/
    @Test 
    public void testInsertCoin()
    {
        VendingMachine v = new VendingMachine(1);
        int amt = v.getCredit();
        Assert.assertEquals("insertCoin(): credits should start at 0", 
                0, v.getCredit()); 
        v.insertCoin(25);        
        Assert.assertEquals("insertCoin(): recognize a quarter", 
                25, v.getCredit());                  
        v.insertCoin(20);        
        Assert.assertEquals("insertCoin(): should ignore invalid coins such as 20", 
                25, v.getCredit());   
        v.insertCoin(5);        
        Assert.assertEquals("insertCoin(): recognize a nickel", 
                30, v.getCredit());  
        v.insertCoin(10);        
        Assert.assertEquals("insertCoin(): recognize a dime", 
                40, v.getCredit());  
        v.insertCoin(100);        
        Assert.assertEquals("insertCoin(): recognize a dollar", 
                140, v.getCredit());  
        v.makeSelection(); 
        
        // should be out of stock now
        Assert.assertEquals("insertCoin(): should be out of stock", 
                0, v.getInventory()); 
        v.insertCoin(25);        
        Assert.assertEquals("if out of stock: do not accept coins", 
                0, v.getCredit());                 
    }  

    /******************************************************
     * Test Cancel Sale
     *****************************************************/
    @Test 
    public void testCancelSale()
    {
        int amt = v.getCredit();
        Assert.assertEquals("insertCoin(): credits should start at 0", 
                0, v.getCredit()); 
        v.insertCoin(25);                        
        v.cancelSale();        
        Assert.assertEquals("cancelSale(): credit should be zero", 
                0, v.getCredit()); 
                
    } 
    
    /******************************************************
     * Test Restock
     *****************************************************/
    @Test 
    public void testRestock()
    {
        int amt = v.getInventory();
        v.restock(17);
        Assert.assertEquals("restock(): restock with 17", 
                amt+17, v.getInventory()); 
        amt = v.getInventory();        
        v.restock(-17);
        Assert.assertEquals("restock(): ignore negative numbers", 
                amt, v.getInventory());  
                
    }  

    /******************************************************
     * Test Selection
     *****************************************************/
    @Test 
    public void testSelection()
    {
        int count = v.getInventory();
        int sales = v.getTotalSales();
        
        // insert a quarter and try to make selection
        v.insertCoin(25);
        v.makeSelection();
        Assert.assertEquals("makeSelection(): do nothing after inserting a quarter", 
                25, v.getCredit()); 
        Assert.assertEquals("makeSelection(): do nothing after inserting a quarter", 
                count, v.getInventory()); 
        Assert.assertEquals("makeSelection(): do nothing after inserting a quarter", 
                sales, v.getTotalSales()); 
                
        // add a dollar and make selection        
        v.insertCoin(100);        
        v.makeSelection();
        Assert.assertEquals("makeSelection(): reset credit after sale", 
                0, v.getCredit()); 
        Assert.assertEquals("makeSelection(): inventory goes down by one after sale", 
                count-1, v.getInventory());        
        Assert.assertEquals("makeSelection(): sales goes up after sale", 
                sales+125, v.getTotalSales()); 
    }     
    /******************************************************
     * Test Simulate Sales
     *****************************************************/
    @Test 
    public void testSimulate()
    {
        v.simulateSales(100);
        Assert.assertEquals("simulateSales(): should be 10 bottles in stock", 
                10, v.getInventory());  
        Assert.assertEquals("simulateSales(): total sales should be 12500", 
                12500, v.getTotalSales());                  
    }      
 
    /******************************************************
     * Test Simulate Sales
     *****************************************************/
    @Test 
    public void testOutOfStock()
    {
        VendingMachine v = new VendingMachine(2);
        v.insertCoin(100);
        v.insertCoin(25);
        v.makeSelection();
        Assert.assertEquals("Out of Stock: total sales should be 125", 
                125, v.getTotalSales());                  
        v.insertCoin(100);
        v.insertCoin(25);
        v.makeSelection();
        Assert.assertEquals("Out of Stock: total sales should be 250", 
                250, v.getTotalSales());  
        v.insertCoin(100);
        v.insertCoin(25);
        v.makeSelection();
        Assert.assertEquals("Out of Stock: should be no additional sales", 
                250, v.getTotalSales());              
        Assert.assertEquals("Out of Stock: should be no additional sales", 
                0, v.getInventory());              
            
    }   
    
    /******************************************************
     * Test Format Dollars
     *****************************************************/
    @Test 
    public void testFormatDollars()
    {
        Assert.assertEquals("formatDollar(): $1.00 expected", 
                "$1.00", v.formatDollars(100)); 
        Assert.assertEquals("formatDollar(): $0.05 expected", 
                "$0.05", v.formatDollars(5)); 
        Assert.assertEquals("formatDollar(): $12.56 expected", 
                "$12.56", v.formatDollars(1256));                 
    }      

    
    /******************************************************
     * Test Total Sales
     *****************************************************/
    @Test 
    public void testTotalSales()
    {
        v.insertCoin(100);
        v.insertCoin(100);
        v.makeSelection();
        Assert.assertEquals("after one sale: $1.25 expected", 
                125, v.getTotalSales()); 
        v.insertCoin(100);
        v.insertCoin(100);
        v.makeSelection();
        Assert.assertEquals("after two sales: $2.50 expected", 
                250, v.getTotalSales());  
                
        v.insertCoin(100);
        v.insertCoin(100);
        v.makeSelection();
        Assert.assertEquals("after three sales: $3.75 expected", 
                375, v.getTotalSales());              
    }     

}