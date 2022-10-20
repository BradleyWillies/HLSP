package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        
        // append to session attributes
        // checks if request parameter has a value and if session attribute already has a value
        if(request.getParameter("mealCalories") != "") {
	        if(session.getAttribute("mealCalories") != null)
	        	session.setAttribute("mealCalories", (Integer) session.getAttribute("mealCalories") + Integer.parseInt(request.getParameter("mealCalories")));
	        else
	        	session.setAttribute("mealCalories", Integer.parseInt(request.getParameter("mealCalories")));
        }
        
        if(request.getParameter("exerciseCalories") != "") {
	        if(session.getAttribute("exerciseCalories") != null)
	        	session.setAttribute("exerciseCalories", (Integer) session.getAttribute("exerciseCalories") + Integer.parseInt(request.getParameter("exerciseCalories")));
	        else
	        	session.setAttribute("exerciseCalories", Integer.parseInt(request.getParameter("exerciseCalories")));
        }
        
        if(request.getParameter("exerciseTime") != "") {
	        if(session.getAttribute("exerciseTime") != null)
	        	session.setAttribute("exerciseTime", (Integer) session.getAttribute("exerciseTime") + Integer.parseInt(request.getParameter("exerciseTime")));
	        else
	        	session.setAttribute("exerciseTime", Integer.parseInt(request.getParameter("exerciseTime")));
        }
        
        if(request.getParameter("exerciseSteps") != "") {
	        if(session.getAttribute("exerciseSteps") != null)
	        	session.setAttribute("exerciseSteps", (Integer) session.getAttribute("exerciseSteps") + Integer.parseInt(request.getParameter("exerciseSteps")));
	        else
	        	session.setAttribute("exerciseSteps", Integer.parseInt(request.getParameter("exerciseSteps")));
        }
        
        if(request.getParameter("workTime") != "") {
	        if(session.getAttribute("workTime") != null)
	        	session.setAttribute("workTime", (Integer) session.getAttribute("workTime") + Integer.parseInt(request.getParameter("workTime")));
	        else
	        	session.setAttribute("workTime", Integer.parseInt(request.getParameter("workTime")));
        }
        
        // overwrite the attribute value for this day
        if(request.getParameter("workStress") != "") {
        	session.setAttribute("workStress", Integer.parseInt(request.getParameter("workStress")));
        }
        
        if(request.getParameter("sleepTime") != "") {
	        if(session.getAttribute("sleepTime") != null)
	        	session.setAttribute("sleepTime", (Integer) session.getAttribute("sleepTime") + Integer.parseInt(request.getParameter("sleepTime")));
	        else
	        	session.setAttribute("sleepTime", Integer.parseInt(request.getParameter("sleepTime")));
        }
        
        // overwrite the attribute value for this day
        if(request.getParameter("sleepRestfulness") != "") {
        	session.setAttribute("sleepRestfulness", Integer.parseInt(request.getParameter("sleepRestfulness")));
        }
        
        if(request.getParameter("meditationTime") != "") {
	        if(session.getAttribute("meditationTime") != null)
	        	session.setAttribute("meditationTime", (Integer) session.getAttribute("meditationTime") + Integer.parseInt(request.getParameter("meditationTime")));
	        else
	        	session.setAttribute("meditationTime", Integer.parseInt(request.getParameter("meditationTime")));
        }
        
        // return to the dashboard
        RequestDispatcher rd = request.getRequestDispatcher("/dashboard.jsp");
		rd.include(request, response);
	}

}
