package Lab;

import java.util.*;
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class ArrayList here.
 * 
 * @author Jasmine Bailey  Emily Peterson
 * @version February 25, 2014
 */
public class ArrayLists
{
    /** ArrayList of Strings */
    private ArrayList<String> a;
    
    
   /*****************************************************
    Constructor: Instantiates the ArrayList to hold 
    Strings
    ****************************************************/
    public ArrayLists(){
        a = new ArrayList<String>();
    }
    
    
   /*****************************************************
    Main: Added provided word to the Array list
    ****************************************************/
    public void addWord(String str){
        a.add(str);
    }
    
    
   /*****************************************************
    Main: Prints ArrayList
    ****************************************************/
    public void display(){
        System.out.print(a);
    }
    
    
   /*****************************************************
    Main: Prints 
    ****************************************************/
    public void addWords(int num){
        String dialog, str2; 
        dialog = JOptionPane.showInputDialog(null, "Enter a word: ");
        
        while (num > 0){
            str2 = "" + num;
            a.add(str2);
        }
    }
    
    
   /*****************************************************
    Main: Search for word
    ****************************************************/
    public int search(String str){
        int i = 0, max = a.size(), negOne = -1;
    
        while (i <= max){
            String s = a.get(i);
            if (s.equals(str)){
                return i;
            }
            i ++;
        }
        
        return negOne;
    }
    
   
   /*****************************************************
    Main: Removed everyother String
    ****************************************************/
    public void removeEveryOther(){
        int i = 0, max = a.size();
        while (i <= max){
            i ++;
            a.remove(i);
            i ++;
            }
        }
  }