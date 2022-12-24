package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.DailyEntry;
import beans.User;
import util.DatabaseController;

/**
 * Servlet implementation class DailyEntryFormServlet
 */
@WebServlet("/DailyEntryFormServlet")
public class DailyEntryFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DailyEntryFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.removeAttribute("errors");
		RequestDispatcher rd = request.getRequestDispatcher("/dailyEntryForm.jspx");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get existing session or create new one
        HttpSession session = request.getSession();
        session.removeAttribute("errors");
        
        // get the existing dailyEntry from the session if one exists
    	DailyEntry dailyEntry = (DailyEntry) session.getAttribute("dailyEntry");
        
        // create variables for the entry form values
        int mealCalories = 0;
        int exerciseCalories = 0;
        int exerciseTime = 0;
        int exerciseSteps = 0;
        int workTime = 0;
        int workStress = 0;
        int sleepTime = 0;
        int sleepRestfulness = 0;
        int meditationTime = 0;
        
        // checks if request parameter has a value then gets value if it exists
        if(request.getParameter("mealCalories") != "") {
        	mealCalories = Integer.parseInt(request.getParameter("mealCalories"));
        }
        
        if(request.getParameter("exerciseCalories") != "") {
        	exerciseCalories = Integer.parseInt(request.getParameter("exerciseCalories"));
        }
        
        if(request.getParameter("exerciseTime") != "") {
        	exerciseTime = Integer.parseInt(request.getParameter("exerciseTime"));
        }
        
        if(request.getParameter("exerciseSteps") != "") {
        	exerciseSteps = Integer.parseInt(request.getParameter("exerciseSteps"));
        }
        
        if(request.getParameter("workTime") != "") {
        	workTime = Integer.parseInt(request.getParameter("workTime"));
        }
        
        if(request.getParameter("workStress") != "") {
        	workStress = Integer.parseInt(request.getParameter("workStress"));
        } else if (dailyEntry != null) {
        	workStress = dailyEntry.getWorkStress();
        }
        
        if(request.getParameter("sleepTime") != "") {
        	sleepTime = Integer.parseInt(request.getParameter("sleepTime"));
        }
        
        if(request.getParameter("sleepRestfulness") != "") {
        	sleepRestfulness = Integer.parseInt(request.getParameter("sleepRestfulness"));
        } else if (dailyEntry != null) {
        	sleepRestfulness = dailyEntry.getSleepRestfulness();
        }
        
        if(request.getParameter("meditationTime") != "") {
        	meditationTime = Integer.parseInt(request.getParameter("meditationTime"));
        }
        
        // validate daily entry values and create errors if any are invalid
	    ArrayList<String> formErrors = DailyEntry.validateDailyEntry(
	    		mealCalories,
	    		exerciseCalories,
	    		exerciseTime,
	    		exerciseSteps,
	    		workTime,
	    		workStress,
	    		sleepTime,
	    		sleepRestfulness,
	    		meditationTime);
        
	    if (formErrors.size() == 0) {
	        // use user bean to get id
	        User user = (User) session.getAttribute("user");
	        int userId = user.getId();
	        
	        // insert dailyEntry record in database
	        DatabaseController dbController = new DatabaseController();
	        Connection dbConnection = dbController.getDbConnection();
	        String insertString = "INSERT INTO `hlsp`.`daily_entry` (`user_id`, `meal_calories`, `exercise_calories`, `exercise_time`,"
	        		+ " `exercise_steps`, `work_time`, `work_stress`, `sleep_time`, `sleep_restfulness`, `meditation_time`, `entry_date`) "
	        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '"+LocalDate.now()+"');";
	    	try {
				PreparedStatement preparedStatement = dbConnection.prepareStatement(insertString);
				preparedStatement.setInt(1, userId);
				preparedStatement.setInt(2, mealCalories);
				preparedStatement.setInt(3, exerciseCalories);
				preparedStatement.setInt(4, exerciseTime);
				preparedStatement.setInt(5, exerciseSteps);
				preparedStatement.setInt(6, workTime);
				preparedStatement.setInt(7, workStress);
				preparedStatement.setInt(8, sleepTime);
				preparedStatement.setInt(9, sleepRestfulness);
				preparedStatement.setInt(10, meditationTime);
				dbController.insertRecord(preparedStatement);
			} catch (SQLException e) {
				System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
			}
	    	
	    	// if a dailyEntry exists, add the daily attributes to the form attributes to get daily totals
	    	if (dailyEntry != null) {
	    		mealCalories += dailyEntry.getMealCalories();
	    		exerciseCalories += dailyEntry.getExerciseCalories();
	    		exerciseTime += dailyEntry.getExerciseTime();
	    		exerciseSteps += dailyEntry.getExerciseSteps();
	    		workTime += dailyEntry.getWorkTime();
	    		sleepTime += dailyEntry.getSleepTime();
	    		meditationTime += dailyEntry.getMeditationTime();
	    	} else {
	    		// create the dailyEntry bean
	    		dailyEntry = new DailyEntry();
	    	}
	    	
	    	// set the dailyEntry bean with the new attributes
	    	dailyEntry.setMealCalories(mealCalories);
	    	dailyEntry.setExerciseCalories(exerciseCalories);
	    	dailyEntry.setExerciseTime(exerciseTime);
	    	dailyEntry.setExerciseSteps(exerciseSteps);
	    	dailyEntry.setWorkTime(workTime);
	    	dailyEntry.setWorkStress(workStress);
	    	dailyEntry.setSleepTime(sleepTime);
	    	dailyEntry.setSleepRestfulness(sleepRestfulness);
	    	dailyEntry.setMeditationTime(meditationTime);
	    	
	    	// add the dailyEntry bean to the session
	    	session.setAttribute("dailyEntry", dailyEntry);
	        
	        // return to the dashboard
	        RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jspx");
			rd.include(request, response);
	    } else {
	    	// set the list of errors as a session variable
        	session.setAttribute("errors", formErrors);
        	// reload page to display errors
            RequestDispatcher rd = request.getRequestDispatcher("/dailyEntryForm.jspx");
    		rd.include(request, response);
	    }
	}

}
