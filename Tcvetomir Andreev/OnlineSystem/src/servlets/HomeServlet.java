package servlets;

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
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		out.println(output + "<html>\n" + "<head><title></title></head>");
			out.println("<body>");
			out.println("<form method=\"POST\" action=\"LoginServlet\" >");
			out.println("<p>UserName:</p></br>");
			out.println("<input type=\"text\" name=\"login_name\"/>");
			out.println("<p>Password:</p></br>");
			out.println("<input type=\"password\" name=\"login_password\"/>");
			out.println("</br>");
			out.println("<input type=\"submit\" value=\"login\"/>");
			out.println("</form>");
			
			out.println("<a href=\"RegServlet\">Register here</a>");
			out.println("</body>");
		
		
	}

}
