package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get existing session or create new one
        HttpSession session = request.getSession();
        
        // get the session attribute email
        String userEmail = (String) session.getAttribute("userEmail");
        
        // if the session isn't new and a user email exists, display the dashboard
        if (!session.isNew() && userEmail != null) {
        	RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jspx");
    		rd.include(request, response);
        }
        // otherwise display the login page
        else {
        	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
    		rd.include(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get existing session or create new one
        HttpSession session = request.getSession();
        
        // get the input credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // get the userId from the database using the input credentials
        DatabaseController dbController = new DatabaseController();
        Connection dbConnection = dbController.getDbConnection();
        String queryString = "SELECT id FROM user WHERE email = ? AND password = ? ;";
        int userId = -1;
        try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(queryString);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			userId = dbController.getRecordId(preparedStatement);
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
        
        // if user exists in database direct them to the dashboard
        if (userId > 0) {
        	// get the dashboard filter value for the user from the database
        	dbConnection = dbController.getDbConnection();
            queryString = "SELECT dashboard_filter FROM user WHERE id = ? ;";
            String dashboardFilter = "";
            try {
    			PreparedStatement preparedStatement = dbConnection.prepareStatement(queryString);
    			preparedStatement.setInt(1, userId);
    			dashboardFilter = dbController.getDashboardFilter(preparedStatement);
    		} catch (SQLException e) {
    			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
    		}
        	
            // create bean for user with email, id
            User user = new User(userId, email);
            user.setDashboardFilter(dashboardFilter);
            
            // set the user bean as a session variable
            session.setAttribute("user", user);
            
            // add dailyEntry to session containing totals of today's daily entries for that user
            session.setAttribute("dailyEntry", dbController.getTodaysDailyEntryForUser(userId));
            
            // direct to dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jspx");
    		rd.include(request, response);
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
    		rd.include(request, response);
        }
	}

}
