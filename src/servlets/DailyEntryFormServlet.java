package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
		RequestDispatcher rd = request.getRequestDispatcher("/dailyEntryForm.jsp");
		rd.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get existing session or create new one
        HttpSession session = request.getSession();
        
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
        
        // TODO HLSP - might not need these session attributes anymore
        // TODO HLSP - use DailyEntry validate method to mirror js validation
        // append to session attributes
        // checks if request parameter has a value and if session attribute already has a value
        if(request.getParameter("mealCalories") != "") {
        	mealCalories = Integer.parseInt(request.getParameter("mealCalories"));
	        if(session.getAttribute("mealCalories") != null)
	        	session.setAttribute("mealCalories", (Integer) session.getAttribute("mealCalories") + mealCalories);
	        else
	        	session.setAttribute("mealCalories", mealCalories);
        }
        
        if(request.getParameter("exerciseCalories") != "") {
        	exerciseCalories = Integer.parseInt(request.getParameter("exerciseCalories"));
	        if(session.getAttribute("exerciseCalories") != null)
	        	session.setAttribute("exerciseCalories", (Integer) session.getAttribute("exerciseCalories") + exerciseCalories);
	        else
	        	session.setAttribute("exerciseCalories", exerciseCalories);
        }
        
        if(request.getParameter("exerciseTime") != "") {
        	exerciseTime = Integer.parseInt(request.getParameter("exerciseTime"));
	        if(session.getAttribute("exerciseTime") != null)
	        	session.setAttribute("exerciseTime", (Integer) session.getAttribute("exerciseTime") + exerciseTime);
	        else
	        	session.setAttribute("exerciseTime", exerciseTime);
        }
        
        if(request.getParameter("exerciseSteps") != "") {
        	exerciseSteps = Integer.parseInt(request.getParameter("exerciseSteps"));
	        if(session.getAttribute("exerciseSteps") != null)
	        	session.setAttribute("exerciseSteps", (Integer) session.getAttribute("exerciseSteps") + exerciseSteps);
	        else
	        	session.setAttribute("exerciseSteps", exerciseSteps);
        }
        
        if(request.getParameter("workTime") != "") {
        	workTime = Integer.parseInt(request.getParameter("workTime"));
	        if(session.getAttribute("workTime") != null)
	        	session.setAttribute("workTime", (Integer) session.getAttribute("workTime") + workTime);
	        else
	        	session.setAttribute("workTime", workTime);
        }
        
        // overwrite the attribute value for this day
        if(request.getParameter("workStress") != "") {
        	workStress = Integer.parseInt(request.getParameter("workStress"));
        	session.setAttribute("workStress", workStress);
        }
        
        if(request.getParameter("sleepTime") != "") {
        	sleepTime = Integer.parseInt(request.getParameter("sleepTime"));
	        if(session.getAttribute("sleepTime") != null)
	        	session.setAttribute("sleepTime", (Integer) session.getAttribute("sleepTime") + sleepTime);
	        else
	        	session.setAttribute("sleepTime", sleepTime);
        }
        
        // overwrite the attribute value for this day
        if(request.getParameter("sleepRestfulness") != "") {
        	sleepRestfulness = Integer.parseInt(request.getParameter("sleepRestfulness"));
        	session.setAttribute("sleepRestfulness", sleepRestfulness);
        }
        
        if(request.getParameter("meditationTime") != "") {
        	meditationTime = Integer.parseInt(request.getParameter("meditationTime"));
	        if(session.getAttribute("meditationTime") != null)
	        	session.setAttribute("meditationTime", (Integer) session.getAttribute("meditationTime") + meditationTime);
	        else
	        	session.setAttribute("meditationTime", meditationTime);
        }
        
        // use user bean to get id
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        
    	// insert activity record
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
    	
    	// get the existing dailyEntry from the session if one exists
    	DailyEntry dailyEntry = (DailyEntry) session.getAttribute("dailyEntry");
    	
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
	}

}
