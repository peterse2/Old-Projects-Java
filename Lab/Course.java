package Lab;

import java.util.*;
/**
 * Write a description of class Course here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Course
{
    /** Array list of Strings */
    private ArrayList <Student> group;
    
    
    /***************************************************
     Constructor: Instantiates the ArrayList
     ***************************************************/
     public Course(){
        group = new ArrayList<Student>();
     }

     
    /***************************************************
     Basic: Opens the provided file name and reads 
     all the data
     ***************************************************/
     public void readStudentData(String filename){
        String info;
        try{
            
            //opens the data file
            Scanner fileReader = new Scanner(new File(filename));
            Scanner lineReader;
            
            //continue while there is no more data to read
            while (fileReader.hasNext()) {
                
                //read one line of data
                info = fileReader.nextLine();
                
                lineReader = new Scanner(info);
                lineReader.useDelimiter(",");
                
                //read the items one at a time
                String info = lineReader.next();
                Student s = new Student(info);
                
                //add student to the ArrayList
                myStudents.add(s);
            }
            
            //could not find file
            }catch(FileNotFoundException error) {
                System.out.println("File not found ");
            }
     }
     
     
    /***************************************************
     Basic: Prints the roster
     ***************************************************/
     public void printRoster(){
        for (Student roster : group){
            System.out.println(roster);
        }
     }
     
     
    /***************************************************
     Basic: Adds student to the ArrayList
     ***************************************************/
     public void addStudent(Student s){
        group.add(s);
     }
     
     
    /***************************************************
     Basic: Adds student with a gpa lower than 2.0 to 
     the new ArrayList
     ***************************************************/
     public ArrayList <Student> probationList(){
        ArrayList <Student> probation = new ArrayList <Student> ();
        
        for (Student p : group){
            if (p.getGPA() < 2.0){
                probation.add(p);
            }
        }
        
        return probation;
     }
     
     
    /***************************************************
     Basic: Prints the student with the highest grade
     ***************************************************/
     public Student findHighestGrade(){
        Student highest = group.get(0); 
         
        for (Student h : group){
            if (h.getGPA() > highest.getGPA()){
                highest = h;
            }
        }
        
        return highest;
     }
}
