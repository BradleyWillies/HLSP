package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import util.DatabaseController;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/Dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** 
	    * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	    * @param request servlet request
	    * @param response servlet response
	    */
	    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        // save user preference for filter
	    	HttpSession session = request.getSession();
	    	DatabaseController dbController = new DatabaseController();
	        Connection dbConnection = dbController.getDbConnection();
        	String updateString = "UPDATE `hlsp`.`user` SET `dashboard_filter` = ? WHERE `id` = ?;";
        	User user = (User) session.getAttribute("user");
        	user.setDashboardFilter(request.getParameter("filter"));
        	try {
    			PreparedStatement preparedStatement = dbConnection.prepareStatement(updateString, Statement.RETURN_GENERATED_KEYS);
    			preparedStatement.setString(1, request.getParameter("filter"));
    			preparedStatement.setInt(2, user.getId());
    			dbController.insertRecord(preparedStatement);
    		} catch (SQLException e) {
    			System.out.println("Exception is ;" + e + ": message is " + e.getMessage());
    		}
	    }

		// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	    /** 
	    * Handles the HTTP <code>GET</code> method.
	    * @param request servlet request
	    * @param response servlet response
	    */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    } 

	    /** 
	    * Handles the HTTP <code>POST</code> method.
	    * @param request servlet request
	    * @param response servlet response
	    */
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	        processRequest(request, response);
	    }

}
