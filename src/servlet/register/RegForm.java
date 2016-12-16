package servlet.register;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Registration Form Class for a registration servlet on an online journal
*/

public class RegForm {
	
	private String html = "<!DOCTYPE html>\r\n<html>\r\n<!--\r\n\tCOMP3095 Web Application Development with Java\r\n\tAssignment 1 - Servlets\r\n\tInstructor : Sergio Santilli sergio.santilli@georgebrown.ca\r\n\t\r\n\tby Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca\r\n\tDate: Monday, October 10, 2016\r\n\r\n\tDescription:\r\n\t\tHTML file for registration page for an online journal\r\n-->\n";
	private String head = "<head>\r\n\t\t<meta charset='UTF-8'>\r\n\t\t<title>Registration | Journal</title>\n";
	private String style = "\t\t<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto'>\r\n\t\t<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto+Slab'>\r\n\t\t<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Source+Code+Pro'>\r\n\t\t<style>\r\n* {\r\n\tmargin: 0;\r\n\tpadding: 0;\r\n}\r\nbody {\r\n\twidth:85%;\r\n\tmax-width:96em;\r\n\tfont-size:62.5%;\r\n\tbackground-color: #333333;\r\n\tmargin: 2em auto 2em auto;\r\n}\r\nform{\r\n margin-left: auto;\r\n margin-right: auto;\r\n}\r\nfieldset{\r\n\tborder-width: 1px;\r\n\tborder-top-style: solid;\r\n\tborder-color: white;\r\n\tpadding: .5em;\r\n}\r\nlegend, h2{\r\n\ttext-align: center;\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 36px;\r\n\tcolor: white;\r\n}\r\nlabel, h3{\r\n\ttext-align: right;\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 24px;\r\n\tcolor: #75a3d1;\r\n}\r\n.error{\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 24px;\r\n\tcolor: #E05151;\r\n}\r\ninput, select{\r\n\tborder: solid;\r\n\tborder-width: 1px;\r\n\tborder-color: white;\r\n\tbackground-color: #555555;\r\n\tpadding: .25em;\r\n\tmargin: .25em;\r\n\tfont-family: 'Source Code Pro', Courier, monospace;\r\n\tfont-size: 20px;\r\n\tcolor: #FFD452;\r\n}\r\n#phone1, #phone2{\r\n\twidth: 3em;\r\n}\r\n#phone3{\r\n\twidth: 4em;\r\n}\r\n#phoneformat{\r\n\tfont-family: 'Source Code Pro', Courier, monospace;\r\n\tfont-size: 20px;\r\n\tcolor: #FFD452;\r\n}\r\n\t\t</style>\r\n";
	private String body = "<body>\n";
	private String form = "\t<form method=\"post\"action=\"AuthenticateServlet\">\r\n\t\t<fieldset id=\"personal\">\r\n\t\t\t<legend>Personal Information</legend>\r\n\t\t\t\t<span class='error' id='mainError'></span>\r\n\t\t\t\t\r\n\t\t\t\t<label for=\"firstName\">First Name: </label>\r\n\t\t\t\t<input id='firstname' type=\"text\"name=\"firstName\" /><br />\r\n\t\t\t\t<span class='error' id='firstNameError'></span>\r\n\r\n\t\t\t\t\r\n\t\t\t\t<label for=\"lastName\">Last Name: </label>\r\n\t\t\t\t<input id='lastname' type=\"text\"name=\"lastName\" /><br />\r\n\t\t\t\t<span class='error' id='lastNameError'></span>\r\n\r\n\t\t\t\t<label for=\"email\">E-mail: </label>\r\n\t\t\t\t<input id='email' type=\"email\"name=\"email\" /><br />\r\n\t\t\t\t<span class='error' id='emailError'></span>\r\n\r\n\t\t\t\t<label for=\"confirmEmail\">Confirm E-mail:</label>\r\n\t\t\t\t<input id='confirmEmail' type=\"email\"name=\"confirmEmail\" /><br />\r\n\t\t\t\t<span class='error' id='confirmEmailError'></span>\r\n\r\n\t\t\t\t<label for=\"phone1\">Telephone: </label>\r\n\t\t\t\t<span id='phoneformat'>\r\n\t\t\t\t\t(<input id='phone1' type=\"text\"name=\"phone1\">)<input id='phone2' type=\"text\" name=\"phone2\"> - <input id='phone3' type=\"text\"name=\"phone3\"><br />\r\n\t\t\t\t</span>\r\n\t\t\t\t<span class='error' id='phoneError'></span>\r\n\r\n\t\t\t\t<label for=\"year\">Year: </label>\r\n\t\t\t\t<select name='year'>\r\n\t\t\t\t\t<option value =\"\">Select One...</option>\r\n\t\t\t\t\t<option value=\"1\">One</option>\r\n\t\t\t\t\t<option value=\"2\">Two</option>\r\n\t\t\t\t\t<option value=\"3\">Three</option>\r\n\t\t\t\t\t<option value=\"4\">Four</option>\r\n\t\t\t\t</select><br />\r\n\t\t\t\t<span class='error' id='yearError'></span>\r\n\r\n\t\t\t\t<label for=\"major\">Major: </label>\r\n\t\t\t\t<select name='major'>\r\n\t\t\t\t\t<option value =\"\">Select One...</option>\r\n\t\t\t\t\t<option value=\"T127\">Computer Programmer Analyst Program (T127)</option>\r\n\t\t\t\t\t<option value=\"T141\">Computer Systems Technician Program (T141)</option>\r\n\t\t\t\t\t<option value=\"T147\">Computer Systems Technology Program (T147)</option>\r\n\t\t\t\t\t<option value=\"T163\">Game Programming Program (T163)</option>\r\n\t\t\t\t</select><br />\r\n\t\t\t\t<span class='error' id='majorError'></span>\r\n\t\t</fieldset>\r\n\r\n\t\t<fieldset id=\"userfield\">\r\n\t\t\t<legend>Username</legend>\r\n\t\t\t\t<label for=\"username\">Username: </label>\r\n\t\t\t\t<input id='username' type=\"text\"name=\"username\"/><br />\r\n\t\t\t\t<span class='error' id='usernameError'></span>\r\n\r\n\t\t\t\t<label for=\"password\">Password: </label>\r\n\t\t\t\t<input type=\"password\"name=\"password\"/><br />\r\n\t\t\t\t<span class='error' id='passwordError'></span>\r\n\t\t\t\t\r\n\t\t\t\t<label for=\"confirmPassword\">Confirm Password: </label>\r\n\t\t\t\t<input type=\"password\"name=\"confirmPassword\"/><br />\r\n\t\t\t\t<span class='error' id='confirmPasswordError'></span>\r\n\t\t</fieldset>\r\n\r\n\t\t<fieldset id=\"register\">\r\n\t\t\t<legend>Register</legend>\r\n\t\t\t\t<table id=\"form\">\r\n\t\t\t\t</table>\r\n\t\t\t\t<input type=\"submit\"value=\"Register\">\r\n\t\t</fieldset>\r\n\t</form>\r\n";
	
	public String print() {
		
		return html + getHead() + getBody() + "</html>\n";
	}
	
	public String printSuccess(String firstName){
		
		String output = "<body>\r\n\t<form>\r\n\t\t<fieldset>\r\n\t\t\t<legend>Congratulations, " + firstName + "</legend>\r\n\t\t\t<label>Your registration is now complete.</label>\r\n\t\t</fieldset>\r\n\t</form>\r\n</body>";
		return html + getHead() + output + "</html>\n";
	}
	
	private String getHead() {
		
		return head + style + "</head>\n";
	}
	
	private String getBody() {
		
		return body + form + "</body>\n";
	}
	
}
