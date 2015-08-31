package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
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

import validation.DataValidation;

/**
 * Servlet implementation class LoginPage
 */
@WebServlet("/LoginPage")
public class LoginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
	
	boolean ifIsOk = DataValidation.isRegistered(userName,password);
	
	if(ifIsOk){
		HttpSession session = request.getSession(true);
		Date createTime = new Date(session.getCreationTime());
		String sessionPass = String.valueOf(createTime);
		HashFunction hash = Hashing.sha1();
		HashCode ha = (HashCode) hash.newHasher().putString(sessionPass, Charsets.UTF_8).hash();
		String finalPass = ha.toString();
		synchronized (session) {
			session.setAttribute("token", finalPass);
			session.setMaxInactiveInterval(600);
		}		
		JSONObject object = new JSONObject().put("status: ok", finalPass );
		out.println("<p>"+object.toString()+"</p>");
		out.println("<form method=\"post\" action=\"UserPanel\">");
		out.println("<input type=\"hidden\" name=\"user\" value=\""+userName+"\"/>");
		out.println("<input type=\"Submit\" value=\"Go to User Panel\"/>");
		out.println("</form>");
	}
	else{
		out.println("<p>Wrong user name and password!</p>");
		out.println("<a href=\"http://localhost:8080/Game/index.jsp\">Try again here</a>");
	}
	}

}
