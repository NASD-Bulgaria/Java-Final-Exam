package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterTest
 */
@WebServlet("/RegisterTest")
public class RegisterTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pr = response.getWriter();
		pr.print("<html><head><title>Test</title></head>"+
		"<body><form action=\"/FinalTest/Register\" method=\"post\">"+
				"<br/><p>loginName</p><input type=\"text\" name=\"loginName\"/>"+
				"<br/><p>loginPass</p><input type=\"password\" name=\"loginPass\"/>"+
				"<br/><p>rePass</p><input type=\"password\" name=\"rePass\"/>"+
				"<br/><p>firstname</p><input type=\"text\" name=\"firstName\"/>"+
				"<br/><p>lastName</p><input type=\"text\" name=\"lastName\"/>"+
				"<br/><p>balance</p><input type=\"text\" name=\"balance\"/>"+
				"<br><input type=\"submit\" value=\"Test\"/>"+
			"</form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
