package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
        
        // create a DatabaseController to interact with the db
        DatabaseController dbController = new DatabaseController();
        
        // get the user id for the current session user email
        String queryString = "SELECT id FROM user WHERE email = '" + session.getAttribute("userEmail") + "';";
        int userId = dbController.getRecordId(queryString);
        
        // TODO HLSP -  use user bean to get id instead of db query
        
        
        // if a user id was found
        if (userId > 0) {
        	// insert activity record
            String insertString = "INSERT INTO `hlsp`.`daily_entry` (`user_id`, `meal_calories`, `exercise_calories`, `exercise_time`, `exercise_steps`, `work_time`, `work_stress`, `sleep_time`, `sleep_restfulness`, `meditation_time`, `entry_date`) "
            		+ "VALUES ("+userId+", "+mealCalories+", "+exerciseCalories+", "+exerciseTime+", "+exerciseSteps+", "+workTime+", "+workStress+", "+sleepTime+", "+sleepRestfulness+", "+meditationTime+", '"+LocalDate.now()+"');";
            dbController.insertRecord(insertString);
        }
        
        
        // return to the dashboard
        RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		rd.include(request, response);
	}

}
