package Lab;

import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class Dice here.
 * 
 * @author Jasmine Bailey  Emily Peterson
 * @version February 25, 2014
 */
public class Dice
{
     /** GVdie */
     private GVdie d1;
     
     /** ArrayList of Strings */
    private ArrayList<String> a;
     
 
    /***************************************
     Constructor: Instantiates an ArrayList
     and dice
     ***************************************/
     public Dice (int num){
         a = new ArrayList<String>();
         d1 = new GVdie();
     }
        
    /***************************************
     Constructor: Instantiates an ArrayList
     and dice
     ***************************************/
     public void rollAllDice (){
         int i = 0, max = a.size();
         
         while (i <= max){
             d1.roll();
             i ++;
         }
     }
        
        
    /***************************************
     Constructor: Instantiates an ArrayList
     and dice
     ***************************************/
     public int getTotal (){
         int total = 0;
         int i = 0, max = a.size();
         
         while (i <= max){
             d1.roll();
             i ++;
             total = d1.getValue();
         }
         return total;
     }
}