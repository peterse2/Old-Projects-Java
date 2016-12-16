package package1;

public class Volunteer extends StaffMember {

	//Const: Sets up this volunteer using the specified info
	public Volunteer(String eName, String eAddress, String ePhone){
		super(eName, eAddress, ePhone);
	}
	
	//Returns a zero pay value for this volunteer
	public double pay(){
		return 0.0;
	}
}
