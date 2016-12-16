package Lab;
import java.text.*;
/**********************************************************
Similating a Fight
 
@author Emily Peterson
@version January 29, 2013
*********************************************************/
public class Flight{
    
    /** Airline Name */
    private String Airline;
        
    /** Flight number */
    private int Number;
        
    /** Flight origin */
    private String Origin;
   
    /** Destination */
    private String Destination;
    
    
    /********************************************************
    Make a Flight 
    ********************************************************/
    public Flight(String airline, int num, String orig, String dest) { 
        Airline = airline;
        Number = num;
        Origin = orig;
        Destination = dest;
    }
       
        
    /********************************************************
    Flight Number
    ********************************************************/
    public void setNumber(int num){
        Number = num;
    }
     
    
    /*******************************************************
    Recieve the Flight Number
    ******************************************************/
    public int getNumber(){
        return Number;
    }
    
    
    /*******************************************************
    * Setting an Airline
    *******************************************************/
    public void setAirline(String airline) {
        Airline = airline;
    }
             
    
    /*******************************************************
    Recieve the Airline
    *******************************************************/
    public String getAirline() {
        return Airline;
    }
    
    
    /*******************************************************
    Setting a Destination
    *******************************************************/
    public void setDestination(String dest) {
        Destination = dest;
    }
    
    
    /*******************************************************
    Reiving a Destination
    *******************************************************/
    public String getDestination() {
        return Destination;
    }
    
    
    /*******************************************************
    Setting the Origin
    *******************************************************/
    public void setOrigin(String orig) {
        Origin = orig;
    }
    
    
    /*******************************************************
    Revieving the Origin
    *******************************************************/
    public String getOrigin() {
        return Origin;
    }
    
    
    /*******************************************************
    Displaying the recived airline name and number,
    origin, and destination
    *******************************************************/
    public String toString() {
        String str;
        str = Airline + "\t" + Number + "\t" + Origin + " to " 
        + Destination;
        return str;
    }
    
    
    /*******************************************************
    Main Method For Displaying three example flight details 
    *******************************************************/
    public static void main(String args[]) {
        Flight f1 = new Flight("Southwest", 345, "LAX", "JFK");
        System.out.println(f1);
         
        Flight f2 = new Flight("United  ", 191, "CHI", "JFK");
        System.out.println(f2);
       
        Flight f3 = new Flight("American", 763, "DFW", "GRR");
        System.out.println(f3);
    }
}
