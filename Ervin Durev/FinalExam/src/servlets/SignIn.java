package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import queries.Queries;


@WebServlet("/SignIn")
public class SignIn extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    
    public SignIn() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		JSONObject json = new JSONObject();
		json.put("status", "ok");
	
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#e6fdf5\">"
				+ json
				+ "<center> "
				+ "Login succesfully!"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Deposit\" >"
				+ "<BR>"
				+ "Make deposit: "
				+ "<input type=\"submit\" value=\"Deposit\" /><BR><BR>"
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/Withdraw\">"
				+ "<BR>"
				+ "Make withdraw: "
				+ "<input type=\"submit\" value=\"Withdraw\" /><BR><BR>"
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/LoginPage\">"
				+ "<BR>"
				+ "<input type=\"submit\" value=\"log out\" /><BR><BR>"
				+ "</FORM>" 
				+ "</center></BODY></HTML>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JSONObject json = new JSONObject();
		json.put("status", "failed");
		
		if(Queries.authentification(username, password))
		{
			
			HttpSession session = request.getSession(true);
			
			Calendar calendar = Calendar.getInstance();
			Random rand = new Random();
		
			long num = calendar.getTimeInMillis();
			int number = rand.nextInt(1000000);
			
			String randomString = Long.toString(num) + Integer.toString(number);
			String sessionId = hash(randomString);
			
			session.setAttribute("token", sessionId);
			session.setAttribute("username", username);
			request.getSession().setMaxInactiveInterval(600);
			
			response.sendRedirect("http://localhost:8080/FinalExam/SignIn");
		}
		else
		{
			response.getWriter().print(
							"<html><head><title></title></head>"
							+ "<body bgcolor=\"#e6fdf5\">\n"
							+ "<form action= \"http://localhost:8080/FinalExam/LoginPage\">"
							+ json
							+ "<center>"
							+ "Invalid username or password!<BR>"
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>"
							+ "</form></body></html>"
					);
		}
	}
	
	private static String hash(String string)
	{
		HashFunction hf = Hashing.sha1();
		HashCode hc = hf.hashString(string, Charsets.UTF_8);

		return hc.toString();
	}

}
