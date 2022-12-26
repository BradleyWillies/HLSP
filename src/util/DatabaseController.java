package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import beans.DailyEntry;

public class DatabaseController {
	private Connection connection;
	private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/hlsp";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	
	public Connection getDbConnection() {
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
		return connection;
	}
	
	private void closeDbConnection() {
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
	}
	
	public int insertRecord(PreparedStatement preparedStatement) {
		int returnId = 0;
		
		try {
			returnId = preparedStatement.executeUpdate();
        } catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
		
		closeDbConnection();
		return returnId;
	}
	
	public int getRecordId(PreparedStatement preparedStatement) {
		int returnId;
		
		try {
			ResultSet rs = preparedStatement.executeQuery();
			// If there is a row in the ResultSet
			if (rs.next()) {
				// get the record id from this row
				returnId = rs.getInt(1);
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

	public DailyEntry getTodaysDailyEntryForUser(int userId) {
		 DailyEntry dailyEntry = new DailyEntry();
		 String queryString = "SELECT * FROM daily_entry WHERE user_id = ? "
				 + "AND entry_date = '" + LocalDate.now() + "';";
		 try {
			 PreparedStatement preparedStatement = getDbConnection().prepareStatement(queryString);
			 preparedStatement.setInt(1, userId);
			 ResultSet rs = preparedStatement.executeQuery();
			 while (rs.next()) {
	            	dailyEntry.setMealCalories(dailyEntry.getMealCalories() + rs.getInt(3));
	            	dailyEntry.setExerciseCalories(dailyEntry.getExerciseCalories() + rs.getInt(4));
	            	dailyEntry.setExerciseTime(dailyEntry.getExerciseTime() + rs.getInt(5));
	            	dailyEntry.setExerciseSteps(dailyEntry.getExerciseSteps() + rs.getInt(6));
	            	dailyEntry.setWorkTime(dailyEntry.getWorkTime() + rs.getInt(7));
	            	dailyEntry.setWorkStress(rs.getInt(8));
	            	dailyEntry.setSleepTime(dailyEntry.getSleepTime() + rs.getInt(9));
	            	dailyEntry.setSleepRestfulness(rs.getInt(10));
	            	dailyEntry.setMeditationTime(dailyEntry.getMeditationTime() + rs.getInt(11));
	            }
		 } catch (SQLException e) {
			 System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		 }
		 closeDbConnection();
		 return dailyEntry;
	}
	
	public String getDashboardFilter(PreparedStatement preparedStatement) {
		String returnFilter;
		
		try {
			ResultSet rs = preparedStatement.executeQuery();
			// If there is a row in the ResultSet
			if (rs.next()) {
				// get the record id from this row
				returnFilter = rs.getString(1);
			} else {
				returnFilter = "";
			}
        } catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
			returnFilter = "";
		}
		
		closeDbConnection();
		return returnFilter;
	}
}
