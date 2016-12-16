package servlet.register;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	An Form Error class to hold error messages for a registration servlet in an online journal
*/

//Object for validation errors and output logic
public class FormError {
	
	//Properties
	private String main;
	private String firstName; 
	private String lastName;
	private String email;
	private String confirmEmail;
	private String phone;
	private String year;
	private String major;
	private String username;
	private String password;
	private String confirmPassword;
	
	//Accessors: if null will return null, else will return error message wrapped in a span
	public String getMain() {
		
		if(!isEmpty()) {
			String output;
			output = "Please try again.<br>";
			output += (main == null)? "" : main+ "<br>";
			return output;
		} else
			return null;
	}
	public String getFirstName() {
		return (firstName != null)? firstName : "";
	}

	public String getLastName() {
		return (lastName != null)? lastName : "";
	}

	public String getEmail() {
		return (email != null) ? email : "";
	}
	
	public String getConfirmEmail() {
		return (confirmEmail != null) ? confirmEmail : "";
	}
	
	public String getPhone() {
		return (phone != null) ? phone : "";
	}

	public String getYear() {
		return (year != null) ? year : "";
	}

	public String getMajor() {
		return (major != null) ? major : "";
	}

	public String getUsername() {
		return (username != null) ? username : "";
	}

	public String getPassword() {
		return (password != null) ? password : "";
	}
	
	public String getConfirmPassword() {
		return (confirmPassword != null) ? confirmPassword : "";
	}
	
	//Mutators all concats a br tag to the end. If property already has a value, appends to the value.
	public void setMain(String main) {
		if(main != "")
			this.main = (this.main != null) ? this.main + main + "<br>" : main + "<br>";
	}
	
	public void setFirstName(String firstName) {
		if(firstName != "")
			this.firstName = (this.firstName != null) ? this.firstName + firstName + "<br>" : firstName + "<br>";
	}
	
	public void setLastName(String lastName) {
		if(lastName != "")
			this.lastName = (this.lastName != null) ? this.lastName+lastName+"<br>" : lastName+"<br>";
	}
	
	public void setEmail(String email) {
		if(email != "")
			this.email = (this.email != null) ? this.email+email+"<br>" : email+"<br>";
	}
	
	public void setConfirmEmail(String confirmEmail) {
		if(confirmEmail != "")
			this.email = (this.confirmEmail != null) ? this.confirmEmail+confirmEmail+"<br>" : confirmEmail+"<br>";
	}
	
	public void setPhone(String phone) {
		if(phone != "")
			this.phone = (this.phone != null) ? this.phone+phone+"<br>": phone+"<br>";
	}
	
	public void setYear(String year) {
		if(year != "") 
			this.year = (this.year != null) ? this.year+year+"<br>" : year+"<br>";
	}
	
	public void setMajor(String major) {
		if(major != "")
			this.major = (this.major != null) ? this.major+major+"<br>" : major+"<br>";
	}
	
	public void setUsername(String username) {
		if(username != "")
			this.username = (this.username != null) ? this.username+"<br>"+username+"<br>" : username+"<br>";
	}
	
	public void setPassword(String password) {
		if(password != "")
			this.password = (this.password != null) ? this.password+"<br>"+password+"<br>" : password+"<br>";
	}
	
	public void setConfirmPassword(String confirmPassword) {
		if(confirmPassword != "")
			this.confirmPassword = (this.confirmPassword != null) ? this.confirmPassword+"<br>"+confirmPassword+"<br>" : confirmPassword+"<br>";
	}
	
	//Checks if all the properties are empty.
	
	public boolean isEmpty() {
		
		return ((main == null || main == "") &&
				(firstName == null || firstName == "") &&
				(lastName == null || lastName == "") &&
				(email == null || email == "") &&
				(phone == null || phone== "") &&
				(year == null || year == "") &&
				(major == null || major == "") &&
				(username == null || username == "") &&
				(password == null || password == ""));
	}

}
