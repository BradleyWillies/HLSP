package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        	RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
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
        String queryString = "SELECT id FROM user WHERE email LIKE '" + email + "' AND password LIKE '" + password + "';";
        int userId = dbController.getRecordId(queryString);
        
        // if user exists in database direct them to the dashboard
        if (userId > 0) {
        	// set the user's email as the session email
            session.setAttribute("userEmail", email);
            
            // direct to dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
    		rd.include(request, response);
        } else {
        	RequestDispatcher rd = request.getRequestDispatcher("/login.html");
    		rd.include(request, response);
        }
	}

}
