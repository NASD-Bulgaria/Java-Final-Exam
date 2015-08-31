package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

import org.json.JSONObject;

import control.PlayerEJB;

/**
 * Servlet implementation class Withdraw
 */
@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private PlayerEJB playerEJB;
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");
		dispatcher.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		res.setContentType("text/html");	
		
		String token = req.getParameter("token");
		Player thePlayer = playerEJB.getPlayerByToken(token);
		int userID = thePlayer.getId();
		
		String amountWithdraw = req.getParameter("amountWithdraw");
		double amount = 0;
		try {
			amount = Double.parseDouble(amountWithdraw);
		} catch(NumberFormatException e){
			System.err.println("NumberformatException?!");
		}
		
		JSONObject myJSON = new JSONObject();
		
		if( amount > 0 && amount <= playerEJB.getBalance(userID)){
			playerEJB.withdrawMoney(userID, amount);
			
			myJSON.put("token", token);
			myJSON.put("withdraw status","ok");
			myJSON.put("amount", amount);
			myJSON.put("balance", playerEJB.getBalance(userID));
			
			req.setAttribute("myJSON", myJSON);
						
			dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(req, res);
			
			
		} else {
			myJSON.put("token", token);
			myJSON.put("withdraw status","transaction failed");
			myJSON.put("balance", playerEJB.getBalance(userID));
			
			req.setAttribute("myJSON", myJSON);
						
			dispatcher = getServletContext().getRequestDispatcher("/home");
			dispatcher.forward(req, res);
		}
		
	}

}
