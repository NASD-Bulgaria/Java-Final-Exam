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

import queries.QueriesDB;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet  {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	
    	PrintWriter out = response.getWriter();
    	String referer = request.getHeader("Referer");
    
		if(referer != null && (referer.equals("http://localhost:8080/FinalExam/MainPage") ||referer.equals("http://localhost:8080/FinalExam/Withdraw")||referer.equals("http://localhost:8080/FinalExam/Deposit")) ){
    	
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("STATUS", "OK");
	
		
		response.setContentType("text/html");

		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Player</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#00FFCC\"> "
				+ jsonObject
				+ "<center> "
				+ "<FORM action=\"http://localhost:8080/FinalExam/Deposit\" >"
				+ "<BR>"
				+ "Deposit:"
				+ "<input type=\"submit\" value=\"Deposit\" /><BR>"
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/Withdraw\">"
				+ "<BR>"
				+ "Withdraw: "
				+ "<input type=\"submit\" value=\"Withdraw\" /><BR>"
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/MainPage\">"
				+ "<BR>"
				+ "<input type=\"submit\" value=\"LogOUT\" /><BR><BR>"
				+ "</FORM>" 
				+ "</center></BODY></HTML>");
		}
		else {
			response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String referer = request.getHeader("Referer");
		if(referer != null && referer.equals("http://localhost:8080/FinalExam/MainPage")){

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		JSONObject jsonObject = new JSONObject();
		
		boolean flag = QueriesDB.isPlayerLogCorrect(username, password);

		if(flag)
		{
			
			HttpSession session = request.getSession(true);
			
			Calendar calendar = Calendar.getInstance();
			Random rand = new Random();

			synchronized (session) {
				long num = calendar.getTimeInMillis();
				int number = rand.nextInt(40000000);
				StringBuilder randomString = new StringBuilder();
				randomString.append(num);
				randomString.append(number);
			
				HashFunction hf = Hashing.sha1();
				HashCode hc = hf.hashString(randomString.toString(), Charsets.UTF_8);
				session.setAttribute("token", hc.toString());
				session.setAttribute("username", username);
				request.getSession().setMaxInactiveInterval(600);
			}
			
			
			response.sendRedirect("http://localhost:8080/FinalExam/Login");
		}
		else
		{
			jsonObject.put("STATUS", "FAILED");
			out.print(
							"<html><head><title></title></head>"
									+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
							+ jsonObject
							+ "<center>"
							+ "Invalid username or password! Try again!<BR>"
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>"
							+ "</form></body></html>"
					);
		}
	}
	else {
		response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
	}
}
}
