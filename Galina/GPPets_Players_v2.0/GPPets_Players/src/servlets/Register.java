package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import control.PlayerEJB;
import model.Player;
import model.PlayerProfile;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB playerEJB;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		JSONObject myJSON = new JSONObject();
		String welcome = "";
		
		String loginName = req.getParameter("loginName");
		String password = req.getParameter("password");
		double initialBalance = 0;
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		
		if(loginName=="" || password=="" || loginName==null || password==null){
			welcome = "Please enter user name / password";
			
			myJSON.put("registration status", "failed");
			myJSON.put("welcome", welcome);
			
			req.setAttribute("myJSON", myJSON);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
			dispatcher.forward(req, res);
		}
		
		if(playerEJB.findPlayerByName(loginName).isEmpty()){
			playerEJB.addPlayer(initialBalance, loginName, password);
			int id = playerEJB.getPlayer(loginName).getId();
			playerEJB.addPlayerProfile(id, firstName, lastName);
			
			welcome = "Welcome, " + firstName + "! Please, login in order to access your account."; 
			myJSON.put("registration status", "ok");
			myJSON.put("welcome", welcome);
			req.setAttribute("myJSON", myJSON);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
			dispatcher.forward(req, res);
			
		} else {
			welcome = "User name already exists! The registration was unsuccessful! Please try again";
			
			myJSON.put("registration status", "failed");
			myJSON.put("welcome", welcome);
			
			req.setAttribute("myJSON", myJSON);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
			dispatcher.forward(req, res);
		}
		
	}

	
}
