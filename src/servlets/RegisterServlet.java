package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import util.DatabaseController;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
        // otherwise display the register page
        else {
        	session.removeAttribute("errors");
        	RequestDispatcher rd = request.getRequestDispatcher("/index.jspx");
    		rd.include(request, response);
        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get existing session or create new one
        HttpSession session = request.getSession();
        session.removeAttribute("errors");
        
        // get the input credentials
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // check if user with email exists in db
        DatabaseController dbController = new DatabaseController();
        Connection dbConnection = dbController.getDbConnection();
        String queryString = "SELECT id FROM user WHERE email = ? ;";
        int userId = -1;
        try {
			PreparedStatement preparedStatement = dbConnection.prepareStatement(queryString);
			preparedStatement.setString(1, email);
			userId = dbController.getRecordId(preparedStatement);
		} catch (SQLException e) {
			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
		}
        
        
	    // validate user credentials and create errors if credentials are invalid or user exists in db
	    ArrayList<String> credentialErrors = User.validateCredentials(email, password);
	    if (userId != 0) {
	    	credentialErrors.add("A user with that email already exists");
	    }
        
        // if there is a problem creating the user, such as one already exists, reload page
        if (credentialErrors.size() > 0) {
        	// set the list of errors as a session variable
        	session.setAttribute("errors", credentialErrors);
        	// reload page to display errors
            RequestDispatcher rd = request.getRequestDispatcher("/index.jspx");
    		rd.include(request, response);
        } else {
        	// insert user record in database
        	dbConnection = dbController.getDbConnection();
        	String insertString = "INSERT INTO user (email, password) VALUES ( ? , ? );";
        	int newUserId = -1;
        	try {
    			PreparedStatement preparedStatement = dbConnection.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);
    			preparedStatement.setString(1, email);
    			preparedStatement.setString(2, password);
    			newUserId = dbController.insertRecord(preparedStatement);
    		} catch (SQLException e) {
    			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
    		}
            
            // create the user bean
            User user = new User(newUserId, email);
            
            // set the user bean as a session variable
            session.setAttribute("user", user);
            
            // direct to dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jspx");
    		rd.include(request, response);
        }
	}

}
