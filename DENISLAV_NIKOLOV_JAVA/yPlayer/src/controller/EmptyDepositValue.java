package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class EmptyDepositValue
 */
@WebServlet("/EmptyDepositValue")
public class EmptyDepositValue extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmptyDepositValue() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String output = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">";
		JSONObject jobjectUnsuccess = new JSONObject().put("Status ", "Please enter a value!");
		out.println(output + "<HTML>" + "<HEAD><TITLE>Sample Form</TITLE></HEAD>" + "<BODY BGCOLOR=\"#6FFDE6\">"
				+ "<CENTER>" + jobjectUnsuccess +   "</BODY></HTML>");
		
		out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/Deposit\">"
				+ "<input type=\"submit\" value=\"Back to deposit page\" />" + "</form>");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
