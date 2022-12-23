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
	
	public int insertRecord(String insertString) {
		int returnId = 0;
		openDbConnection();
		
		try {
			Statement stmt = connection.createStatement();
	        stmt.executeUpdate(insertString, Statement.RETURN_GENERATED_KEYS);
	        // get the id returned from the insert, otherwise will return 0 as there is an issue with the insert
	        ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				returnId = rs.getInt(1);
			}
        } catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		closeDbConnection();
		return returnId;
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
		return returnId;
	}
}
