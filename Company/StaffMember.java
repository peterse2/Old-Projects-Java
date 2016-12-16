package package1;

abstract public class StaffMember {
	
	protected String name;
	protected String address;
	protected String phone;
	
	//Const: Sets up this staff member using the specified info
	public StaffMember(String eName, String eAddress, String ePhone){
		name = eName;
		address = eAddress;
		phone = ePhone;
	}
	
	//Returns a string including the basic employee info
	public String toString(){
		String result = "Name: " + name + "\n";
		
		result += "Address: " + address + "\n";
		result += "Phone: " + phone + "\n";
		
		return result;
	}
	
	//derived classes must define the pay method for each type of employee
	public abstract double pay();

}
