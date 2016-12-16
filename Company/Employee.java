package package1;

public class Employee extends StaffMember {
	
	protected String socialSecurityNumber;
	protected double payRate;
	
	//Const: Sets up this employee with the specified info
	public Employee(String eName, String eAddress, String ePhone, 
			String socSecNumber, double rate){
		super(eName, eAddress, ePhone);
		
		socialSecurityNumber = socSecNumber;
		payRate = rate;
	}
	
	//Returns info about an employee as a string
	public String toString(){
		String result = super.toString();
		 result += "\nSocial Security Number: " + socialSecurityNumber;
		 
		 return result;
	}
	
	//Returns the pay rate for this employee 
	public double pay(){
		return payRate;
	}
}
