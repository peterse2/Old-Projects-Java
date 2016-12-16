package Lab;

import java.util.*;
import javax.swing.*;
/**
 * Loops here.
 * 
 * @author Emily Peterson 
 * @version 2-11-2014
 */
public class Loops
{
   /***************************************************
    Ask int until given negitive then find sum and 
    average of entered
    ***************************************************/
    public void average() {
        String numStr;
        int num, sum, reps;
        double ave; 
        
        //Initial values
        sum = 0;
        reps = 0;
        ave = 0;
        
        do{
            numStr = JOptionPane.showInputDialog ("Enter an Integer: ");
            num = Integer.parseInt(numStr);
            
            if (num < 0) {
                reps --;
            }else{
                sum +=num;
                reps ++;
            }
        }while (num >= 0);
        
        ave = sum / reps;
        JOptionPane.showMessageDialog(null, "Sum: " + sum + "Average: " 
                                      + ave);
    }
   
    
   /***************************************************
    Sum of feild entered
    ***************************************************/
    public void sum (int low, int high){
        int sum = 0;
        sum += high;
        
        do {
            high --;
            sum += high;
        }while (high != low);
        
        JOptionPane.showMessageDialog(null, "Sum: " + sum);
    }
    
    
   /***************************************************
    Infinate Loop
    ***************************************************/
    public void infinate() {
        int high = 1;
        int count = 0;
        
        do {
            high ++;
            count --;
        }while (high != count);
    }
    
    
   /***************************************************
    returns the number of multiples of a digit in feild
    ***************************************************/
    public int countMultiples (int low, int high, int digit) {
        int num, reps = 0;
        num = low;
        do{
            num += digit;
            reps ++;
        }while (num <= high);
        
        return reps;
    }
}
