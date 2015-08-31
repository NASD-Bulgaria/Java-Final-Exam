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
 * Servlet implementation class ExistingLoginName
 */
@WebServlet("/ExistingLoginName")
public class ExistingLoginName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExistingLoginName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject job = new JSONObject().put("Status","This name already exist. Please choose another one!");
		out.println( "<HTML>" + "<HEAD><TITLE>Sample Form</TITLE></HEAD>" + "<BODY BGCOLOR=\"#6FFDE6\">"
				+ "<CENTER>" + job+   "</BODY></HTML>");
		out.println("<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/SignUpPage\">"
				+ "<input type=\"submit\" value=\"Back to sign up page\" />" + "</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
