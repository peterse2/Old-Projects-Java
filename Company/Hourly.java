package package1;

public class Hourly extends Employee {

	private int hoursWorked;
	
	//Const: Sets up this hourly employee using the specified info
	public Hourly(String eName, String eAddress, String ePhone, 
			String socSecNumber, double rate){
		super(eName, eAddress, ePhone, socSecNumber, rate);
		
		hoursWorked = 0;
	}
	
	//Adds the specified number of hours to this employee's acc hours
	public void addHours(int moreHours){
		hoursWorked += moreHours;
	}
	
	//Computes and returns the pay for this hourly employee
	public double pay(){
		double payment = payRate * hoursWorked;
		
		hoursWorked = 0;
		
		return payment;
	}
	
	//Return the info about this hourly employee as a string
	public String toString(){
		String result = super.toString();
		
		result += "\nCurrnet hours: " + hoursWorked;
		//count--;
		
		return result;
	}
}
