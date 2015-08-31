package view;

import java.io.IOException;
import java.io.PrintWriter;

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

import control.PlayerQueries;
import validation.DataValidation;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		 
		 HttpSession last = (HttpSession) request.getSession().getAttribute("token");
		 int t = last.getMaxInactiveInterval();
		 if(t<=0){
			 last.invalidate();
		 }else{
		 
		 PrintWriter out = response.getWriter();
		    
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean isAvailable = DataValidation.available(userName);
			
			if(isAvailable){
				HashFunction hash = Hashing.sha256();
				HashCode ha = (HashCode) hash.newHasher().putString(password, Charsets.UTF_8).hash();
				String finalPass = ha.toString();
			PlayerQueries.registerNewPlayer(userName, finalPass);
			JSONObject object = new JSONObject().put("status", t);
			out.println("<p>"+object.toString()+"</p>");
			out.println("<a href= \"http://localhost:8080/Game/index.jsp\">");
			out.println("Go back to login page");
			out.println("</a>");
			}
			else{
				out.println("<p>User already exist</p>");
				out.println("<a href= \"http://localhost:8080/Game/registration.jsp\">");
				out.println("Go back to register page");
				out.println("</a>");
			}
		 }
	}

}
