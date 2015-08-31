package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		PrintWriter out = response.getWriter();
		out.println(output + "<head><title>Registration page</title></head>"
				+ "<h2>Hello, please register:</h2>"
				+ "<br><br>"
				+ "<form action=\"RegistrationSucssess\" method=post>"
				+ "<p><strong>Please Enter Your User Name: </strong>"
	        +"<input type=\"text\" name=\"login_name\" size=\"25\">"
	        +"<p><p><strong>Please Enter Your Password: </strong>"
	        +"<input type=\"password\" size=\"15\" name=\"login_password\">"
	        + "<p>Please Enter Your First name:</p>"
	        + "<input type=\"text\" name=\"firstName\" size=\"25\"/>"
	        + "<p>Please Enter Your Last name</p>"
	        + "<input type=\"text\" name=\"lastName\" size=\"25\"/><br>"
	        +"<p><p>"
	        +"<input type=\"submit\" value=\"Submit\">"
	        +"<input type=\"reset\" value=\"Reset\">"
	      			
	        +"</form></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */


}
