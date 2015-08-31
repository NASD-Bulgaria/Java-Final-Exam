package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		PrintWriter out = response.getWriter();
		out.println(output + "<html><head><title>Login Page</title></head>"
				+ "<h2>Hello, please log in:</h2>"
				+ "<br><br>"
				+ "<form action=\"LoginPage\" method=post>"
				+ "<p><strong>Please Enter Your User Name: </strong>"
				+ "<input type=\"text\" name=\"login_name\" size=\"25\">"
				+ "<p><p><strong>Please Enter Your Password: </strong>"
				+ "<input type=\"password\" size=\"15\" name=\"login_password\">"
				+ "<p><p>"
				+ "<input type=\"submit\" value=\"Submit\">"
				+ "<input type=\"reset\" value=\"Reset\">"
				+ "</form></html><br>"
				+ "<a href=\"RegServlet\">New Registration</a>");
	
	}

}
