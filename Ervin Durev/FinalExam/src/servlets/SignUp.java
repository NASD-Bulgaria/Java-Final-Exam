package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import queries.Queries;


@WebServlet("/SignUp")
public class SignUp extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public SignUp() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String referer = request.getHeader("Referer");
		
		if(referer == null || !referer.equals("http://localhost:8080/FinalExam/LoginPage"))
			response.sendRedirect("http://localhost:8080/FinalExam/LoginPage");
		
		response.getWriter().print(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Registration form</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ "<H2 ALIGN=\"CENTER\">Registration form</H2>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/SignUp\" METHOD=\"POST\">"
				+ "<CENTER>"
				+ "Enter your username:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"username\"><BR>"
				+"<BR>"
				+ "Enter your password:"
				+ "<INPUT TYPE=\"password\" NAME=\"password\"><BR>"
				+"<BR>"
				+ "Enter your balance:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"balance\"><BR>"
				+"<BR>"
				+ "<INPUT type=\"submit\" value=\"Register\" /><BR><BR>"
				+ "</CENTER>" 
				+ "</FORM>" 
				+ "</BODY></HTML>"
		);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		double balance = Double.parseDouble(request.getParameter("balance"));
		
		JSONObject json = new JSONObject();
		
		if(Queries.doesPlayerExist(username))
		{
			
			json.put("status", "failed");
			
			response.getWriter().print(
					"<html><head><title></title></head>"
					+ "<body bgcolor=\"#e6fdf5\">\n"
					+ "<form action= \"http://localhost:8080/FinalExam/SignUp\">"
					+ json
					+ "<center>"
					+ "Sorry, username already exists!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		else
		{
			Queries.addPlayer(username, password, balance);
			
			json.put("status", "ok");
			
			response.getWriter().print(
					"<html><head><title></title></head>"
					+ "<body bgcolor=\"#e6fdf5\">\n"
					+ "<form action= \"http://localhost:8080/FinalExam/LoginPage\">"
					+ "<center>"
					+ "Succesfull registration!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		
	}

}
