package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {
	private Connection connection;
	private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/hlsp";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	
	private void openDbConnection() {
		if (connection == null) {
        	try {
        		// load the db driver
        		Class.forName("com.mysql.cj.jdbc.Driver");
        		
        		// get connection to db
        		connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        	} catch (Exception e) {
        		System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
        	}
        }
	}
	
	private void closeDbConnection() {
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
	}
	
	public Boolean insertRecord(String insertString) {
		Boolean exceptionOccurred = false;
		openDbConnection();
		
		try {
	        connection.createStatement().executeUpdate(insertString);
        } catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
			exceptionOccurred = true;
		}
		
		closeDbConnection();
		return exceptionOccurred;
	}
	
	public int getRecordId(String queryString) {
		int returnId;
		openDbConnection();
		
		try {
			ResultSet rs1 = connection.createStatement().executeQuery(queryString);
			// If there is a row in the ResultSet
			if (rs1.next()) {
				// get the record id from this row
				returnId = rs1.getInt(1);
			} else {
				returnId = 0;
			}
        } catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
			returnId = 0;
		}
		
		closeDbConnection();
		System.out.println("User id returned: " + returnId);
		return returnId;
	}
}
