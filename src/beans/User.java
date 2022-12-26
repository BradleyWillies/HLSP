package beans;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String email;
	private int id;
	private String dashboardFilter;
	
	public User(int id, String email) {
		this.id = id;
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDashboardFilter() {
		return dashboardFilter;
	}

	public void setDashboardFilter(String dashboardFilter) {
		this.dashboardFilter = dashboardFilter;
	}

	public static ArrayList<String> validateCredentials(String email, String password) {
		ArrayList<String> errors = new ArrayList<String>();
		
		// validating email
		Pattern validEmailRegex = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = validEmailRegex.matcher(email);
		if (!matcher.matches()) {
			errors.add("Email must be in full and correct format");
		}
		
		// validating password
		int passwordMinLength = 8;
		
		if (password.length() < passwordMinLength) {
			errors.add("Password must be longer than " + passwordMinLength + " characters");
		}
		
		Pattern validPassRegex = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");
		matcher = validPassRegex.matcher(password);
		if (!matcher.matches()) {
			errors.add("Password must contain at least one number, one special character, lower and upper case letters, and have no spaces");
		}
		
		return errors;
	}
	
}
