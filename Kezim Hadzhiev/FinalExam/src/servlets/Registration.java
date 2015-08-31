package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import queries.QueriesDB;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

  

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referer = request.getHeader("Referer");
		PrintWriter out = response.getWriter();
		if(referer == null || !referer.equals("http://localhost:8080/FinalExam/MainPage"))
			response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
		
		out.print(
				"<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
				+ "<HTML>"
				+ "<HEAD><TITLE>Registration form</TITLE></HEAD>"
				+ "<BODY BGCOLOR=\"#00FFCC\"> "
				+ "<H2 ALIGN=\"CENTER\">Registration form</H2>"
				+ "<FORM action=\"http://localhost:8080/FinalExam/Registration\" METHOD=\"POST\">"
				+ "<CENTER>"
				+ "Enter your username:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"username\"><BR>"
				+"<BR>"
				+ "Enter your password:"
				+ "<INPUT TYPE=\"password\" NAME=\"password\"><BR>"
				+"<BR>"
				+ "Enter your passwordAgain:"
				+ "<INPUT TYPE=\"password\" NAME=\"repeatPassword\"><BR>"
				+"<BR>"
				+ "Enter your balance:"
				+ "<INPUT TYPE=\"TEXT\" NAME=\"balance\"><BR>"
				+"<BR>"
				+ "<INPUT type=\"submit\" value=\"Register\" /><BR><BR>"
				+ "</CENTER>" 
				+ "</FORM>" 
				+ "<FORM action=\"http://localhost:8080/FinalExam/MainPage\">"
				+ "<BR>"
				+ "<input type=\"submit\" value=\"Back\" /><BR><BR>"
				+ "</FORM>" 
				+ "</BODY></HTML>"
		);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String referer = request.getHeader("Referer");
		
		if(referer == null || !referer.equals("http://localhost:8080/FinalExam/Registration"))
			response.sendRedirect("http://localhost:8080/FinalExam/MainPage");
		
		JSONObject jsonObject = new JSONObject();
		try{
		String name = request.getParameter("username");
		String pass = request.getParameter("password");
		String repeatPass = request.getParameter("repeatPassword");

		if(pass.equals(repeatPass) && !pass.equals("")){

		if(QueriesDB.isPlayerExist(name)){
			
			jsonObject.put("STATUS", "FAILED");
			
			out.print(
					"<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
					+ "<form action= \"http://localhost:8080/FinalExam/Registration\">"
					+ jsonObject
					+ "<center>"
					+ "Username already exists! Sorry try with another username!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		else{

			try{
			 double balance = Double.parseDouble(request.getParameter("balance"));
		if(balance>0){
			QueriesDB.AddPlayerDB(name, pass, balance);
			
			jsonObject.put("Status", "Ok");
			
			out.print(
					"<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
					
					+jsonObject
					+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
					+ "<center>"
					+ "Succesfull registration!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		else {
				jsonObject.put("Status", "FAILED");
			
				out.print(
					"<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
					+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
					+jsonObject
					+ "<center>"
					+ "Balance must be greater than 0!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
		
		}
		catch(NumberFormatException e){
			jsonObject.put("Status", "FAILED");

			out.print("<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
							+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
							+ jsonObject 
							+ "<center>" + "Your must entered number! "
							+ "<input type=\"submit\" value=\"Back\" />"
							+ "</center>" + "</form></body></html>"

					);
			
		}
		}
	}
		else {
				jsonObject.put("Status", "FAILED");
			
				out.print(
					"<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
					+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
					+jsonObject
					+ "<center>"
					
					+ "Incorect password fields!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
	
		}
		catch(NullPointerException e){
			jsonObject.put("Status", "FAILED");
			
			out.print(
					"<html><head><title></title></head>"
							+ "<BODY BGCOLOR=\"#00FFCC\"> "
					+ "<form action= \"http://localhost:8080/FinalExam/MainPage\">"
					+jsonObject
					+ "<center>"
					+ "Incorect entered fields! Try again with correct information!<BR>"
					+ "<input type=\"submit\" value=\"Back\" />"
					+ "</center>"
					+ "</form></body></html>"
			);
		}
	}
}
	