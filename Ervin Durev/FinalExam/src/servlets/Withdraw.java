package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Player;

import org.json.JSONObject;

import queries.Queries;


@WebServlet("/Withdraw")
public class Withdraw extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public Withdraw() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ "<H2 ALIGN=\"CENTER\">Withdraw</H2>"
				+ "<CENTER>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Withdraw\" METHOD = \"POST\">"
				+ "Enter withdraw: "
				+ "<INPUT TYPE=\"TEXT\" NAME=\"Withdraw\"><BR>"
				+ "<input type=\"submit\" value=\"OK\" /><BR><BR>"
				+ "</FORM>"
				+ "</CENTER>"  
				+ "</BODY></HTML>"
		);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		double withdraw = Double.parseDouble(request.getParameter("Withdraw"));
		
		String username = (String) request.getSession().getAttribute("username");
		
		Player player = Queries.getPlayerByName(username);

		double balance = player.getBalance();
		
		int id = player.getId();
		
		JSONObject json = new JSONObject();
		
		if(withdraw > 0 && withdraw <= balance)
		{
			Queries.updateWithdraw(id, withdraw);
			
			json.put("status", "ok");
			json.put("hash", request.getSession().getAttribute("token"));
			
			double updatedBalance = player.getBalance() - withdraw;
			
			response.getWriter().print(
					"<html><head><title></title></head>"
					+ "<body bgcolor=\"#e6fdf5\">\n"
					+ "<form action= \"http://localhost:8080/FinalExam/SignIn\">"
					+ json
					+ "<center>"
					+ "<BR>"
					+ "Succesfull deposit! Your balance now is: "
					+ updatedBalance
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		else
		{
			json.put("status", "failed");
			
			response.getWriter().print(
					"<html><head><title></title></head>"
					+ "<body bgcolor=\"#e6fdf5\">\n"
					+ "<form action= \"http://localhost:8080/FinalExam/SignIn\">"
					+ json
					+ "<center>"
					+ "You do not have enough balance!"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
					
			);
		}
	}

}
