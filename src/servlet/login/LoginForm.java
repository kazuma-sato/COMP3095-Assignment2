package servlet.login;

/*
COMP3095 Web Application Development with Java
Assignment 1 - Servlets
Instructor : Sergio Santilli sergio.santilli@georgebrown.ca

by Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca
Date: Monday, October 10, 2016

Description:
	Registration Servlet for an online journal
*/

public class LoginForm {
	
	private String html = "<!DOCTYPE html>\r\n<html>\r\n<!--\r\n\tCOMP3095 Web Application Development with Java\r\n\tAssignment 1 - Servlets\r\n\tInstructor : Sergio Santilli sergio.santilli@georgebrown.ca\r\n\t\r\n\tby Kazuma Sato 100 948 212 kazuma.sato@georgebrown.ca\r\n\tDate: Monday, October 10, 2016\r\n\r\n\tDescription:\r\n\t\tHTML file for login page for an online journal\r\n-->\n";
	private String head = "<head>\r\n\t\t<meta charset='UTF-8'>\r\n\t\t<title>Login | Journal</title>\n";
	private String style = "<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto'>\r\n\t\t<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Roboto+Slab'>\r\n\t\t<link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Source+Code+Pro'>\r\n\t\t<style>\r\n* {\r\n\tmargin: 0;\r\n\tpadding: 0;\r\n}\r\nbody {\r\n\twidth:85%;\r\n\tmax-width:96em;\r\n\tfont-size:62.5%;\r\n\tbackground-color: #333333;\r\n\tmargin: 2em auto 2em auto;\r\n}\r\nform{\r\n margin-left: auto;\r\n margin-right: auto;\r\n}\r\nfieldset{\r\n\tborder-width: 1px;\r\n\tborder-top-style: solid;\r\n\tborder-color: white;\r\n\tpadding: .5em;\r\n}\r\nlegend, h2{\r\n\ttext-align: center;\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 36px;\r\n\tcolor: white;\r\n}\r\nlabel, h3{\r\n\ttext-align: right;\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 24px;\r\n\tcolor: #75a3d1;\r\n}\r\n.error{\r\n\tfont-family: 'Roboto Slab', Times, serif;\r\n\tfont-size: 24px;\r\n\tcolor: #E05151;\r\n}\r\ninput, button, select{\r\n\tborder: solid;\r\n\tborder-width: 1px;\r\n\tborder-color: white;\r\n\tbackground-color: #555555;\r\n\tpadding: .25em;\r\n\tmargin: .25em;\r\n\tfont-family: 'Source Code Pro', Courier, monospace;\r\n\tfont-size: 20px;\r\n\tcolor: #FFD452;\r\n}\r\n#phone1, #phone2{\r\n\twidth: 3em;\r\n}\r\n#phone3{\r\n\twidth: 4em;\r\n}\r\n#phoneformat, #or {\r\n\tfont-family: 'Source Code Pro', Courier, monospace;\r\n\tfont-size: 20px;\r\n\tcolor: #FFD452;\r\n}\r\n\t\t</style>\r\n";
	private String body = "<head>\n";
	private String form = "\t<form method='post' action='AuthenticateServlet'>\r\n\t\t<fieldset id='userfield'>\r\n\t\t\t<legend>Login</legend>\r\n\t\t\t\t<span class='error' id='errorMessage'></span>\r\n\t\t\t\t<label for='username'>Username: </label>\r\n\t\t\t\t<input id='username' type='text' name='username'/><br />\r\n\t\t\t\t<span class='error' id='usernameError'></span>\r\n\r\n\t\t\t\t<label for='password'>Password: </label>\r\n\t\t\t\t<input type='password' name='password'/><br />\r\n\t\t\t\t<span class='error' id='passwordError'></span>\r\n\r\n\r\n\t\t\t\t<label for='remember'>Remember me:</label>\r\n\t\t\t\t<input type='checkbox' name='remember' value='true'/><br />\r\n\t\t\t\t\r\n\t\t\t\t<input type='button' id='register' name='name' value='Register' onclick='location.href=\"/assignment2/RegisterServlet\"'/>\r\n\t\t\t\t<span id='or'>-or-\r\n\t\t\t\t</span><input type='submit' value='Login' />\r\n\t\t</fieldset>\r\n\t</form>\r\n";

	public String print() {
		
		return html + getHead() + getBody() + "</html>";
	}
		
	private String getHead() {
		
		return head + style + "</head>";
	}
	
	private String getBody() {
		
		return body + form + "</body>";
	}
}
