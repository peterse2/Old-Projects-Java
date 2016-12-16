package Projects.P3;


/**
 * Write a description of class Craps here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Craps {
   //Instance Variables
   
   /** Maintian 2 die */
   private  GVdie d1;
   private GVdie d2;
   
   /** Current Point */
   private int point;
   
   /** Credit Balance */
   private int credit;
   
   /** Current Message */
   private String mes;
   
   /** Come Out roll */
   private boolean comeOut;
   
   
   /***************************************************
    *Constructor: Sets up all the instance variables
    ***************************************************/
    public Craps() {
        d1 = new GVdie();
        d2 = new GVdie();        
        
        point = -1;
        credit = 10;
        comeOut = true;

        mes = "Welcome to My Game!"; 
    }
    
    
   /***************************************************
    *Accessor: Returns credit Balance
    ***************************************************/
    public int getCredits() {
        return credit;
    }
    
    
   /***************************************************
    *Accessor: Returns the current Point
    ***************************************************/
    public int getPoint() {
        return point;
    }
    
    
   /***************************************************
    *Accessor: Returns appropriate message
    ***************************************************/
    public String getMessage () {
        return mes;
    }
    
    
   /***************************************************
    *Basic: Allows the user to set the amount of credits
    ***************************************************/
    public void setCredits(int amount) {
        if (amount >= 0){
            credit = amount;
        }
    }
    
    
   /***************************************************
    *Basic: Processes situations when the comeout button
    *is pushed
    ***************************************************/
    public void comeOut ( ) {
        
        //Test to see if it is time to come out
        if (okToRoll() == true) {
            mes = "Push Roll.";
        
        }//Determine if there is enough credits
        else if (credit == 0){
            mes = "You need more Credits.";   
        
        }//Roll
        else{
            d1.roll();
            d2.roll();
            int total = d1.getValue() + d2.getValue();
            point = total;
            
            //Win if come out is 7 or 11
            if (total == 7 || total == 11){
                mes = "You Win!";
                credit ++;
                point = -1;
                comeOut = true;
            
            }//Player loses if the come out is 2, 3, or 12
            else if(total == 2 || total == 3 || total == 12){
                mes = "You lose.";
                credit --; 
                point = -1;
                comeOut = true;
            
            }//if the player does not win or lose right away they continue to roll and point is set
            else{
                point = total;
                mes = "Point is: " + point + ". Push Roll.";
                comeOut = false;
            }
        }
    }
    
    
   /***************************************************
    *Basic: Processes situations when the roll button 
    is pushed 
    ***************************************************/
   public void roll ( ) {
       
       //if It is not time to roll 
       if (okToRoll() == false){
            mes = "Push Come Out.";
       
        }//Roll both dice if it is ok to roll 
       else{
            d1.roll();
            d2.roll();
            int total = d1.getValue() + d2.getValue(); 
            
            //If ok to roll and a 7 is rolled 
            if (total == 7){
                mes = "You Lose.";
                credit --;
                point = -1;
                comeOut = true;
            
            }//If ok to roll and the point is rolled
            else if (total == point){
                mes = "You Win!";
                credit ++;
                point = -1;
                comeOut = true;
                
            }//If the player does not win or lose they roll again    
            else{
               mes = "Point is: " + point + ". Roll Again."; 
            }
        }
    }
    
    
   /***************************************************
    Basic: Determines if it is ok to roll
    ***************************************************/
    public boolean okToRoll ( ) {
        if (comeOut == false){
            return true;
        }else{
            return false;
        }
    }
    
    
   /***************************************************
    Accesor: Returns the face value of the die indicated
    ***************************************************/
    public GVdie getDie (int num) {
       if (num == 1){
           return d1;
       }else{
           return d2;
       }
    }
    
    
   /***************************************************
    Main: Test
    ***************************************************/
    public static void main (String [] args){
        Craps c1 = new Craps();
        
        System.out.println(c1.getMessage());
       
        c1.comeOut();
       
        System.out.println(c1.getMessage());
        
        while (c1.okToRoll() == true){
            c1.roll();
            System.out.println(c1.getMessage());
        }
         
        c1.getCredits();
    }
}
