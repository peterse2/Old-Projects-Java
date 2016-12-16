package Lab;


/**
 * Write a description of class Student here.
 * 
 * @author Emily Peterson 
 * @version 12-11-13
 */
public class Student
{
    /** First Name */
    private String first;
    
    /** Last Name */
    private String last;

    /** ID number */
    private int id;
    
    /** GPA */
    private double gpa;
    
    
    /***************************************************
     Constructor:
     ***************************************************/
     public Student(String info){
        String [] token = info.split(",");
        first = token [0];
        last = token [1];
        id = Integer.parseInt(token [2].trim());
        gpa = Double.parseDouble(token [2].trim());
     }

     
    /***************************************************
     Accessor: Retuens the first name of the student
     ***************************************************/
     public String getFirst(){
        return first;
     }
    
    
    /***************************************************
     Accessor: Retuens the last name of the student
     ***************************************************/
     public String getLast(){
        return last;
     }
     
     
    /***************************************************
     Accessor: Retuens the id of the student
     ***************************************************/
     public int getID(){
        return id;
     }
     
     
    /***************************************************
     Accessor: Retuens the last name of the student
     ***************************************************/
     public double getGPA(){
        return gpa;
     }
     
     
    /***************************************************
     Basic:
     ***************************************************/
     public String toString(){
        String str = first + " " + last + " (" + id + ") " + gpa;
        return str;
     }
     
     
    /***************************************************
     Main: Prints a sample line for a test
     ***************************************************/
     public static void main(String [] args){
        Student s = new Student ("Jenna, Moskowitz, 12245, 3.8 ");
        System.out.println(s);
     }
}
