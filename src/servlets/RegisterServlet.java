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
        	RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
    		rd.include(request, response);
        }
        // otherwise display the register page
        else {
        	RequestDispatcher rd = request.getRequestDispatcher("/index.html");
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
        
        // insert user record in database
        DatabaseController dbController = new DatabaseController();
        String insertString = "INSERT INTO user (email, password) VALUES ('" + email + "', '" + password + "');";
        Boolean insertException = dbController.insertRecord(insertString);
        
        // if there is a problem creating the user, such as one already exists, go to login
        if (insertException) {
        	// direct to login
            RequestDispatcher rd = request.getRequestDispatcher("/login.html");
    		rd.include(request, response);
        } else {
        	// set the user's email as the session email
            session.setAttribute("userEmail", email);
            
            // direct to dashboard
            RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
    		rd.include(request, response);
        }
	}

}
