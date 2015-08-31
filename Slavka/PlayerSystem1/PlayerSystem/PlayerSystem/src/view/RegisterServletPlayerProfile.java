package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Queries;
import model.ValidationsUsernames;


/**
 * Servlet implementation class RegisterServletPlayerProfile
 */
@WebServlet("/RegisterServletPlayer")
public class RegisterServletPlayerProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServletPlayerProfile() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String playerName= request.getParameter("username");
		boolean isValid = ValidationsUsernames.validPlayer_profile(playerName);
		if (isValid) {
			Queries.insertPlayer_Profile(playerName, null);
			JSONObject json = new JSONObject().put("status", "ok");
			out.println("<p>"+json.toString()+" </p>");
			out.println("<a href=\"http://localhost:8080/PlayerSystem/index.jsp\">Go back to login");
			out.println("</a>");
		}else {
			out.println("<p>Player profile with this name already exist");
			out.println("<a href=\"http://localhost:8080/PlayerSystem/register.jsp\">Go back");
			out.println("</a>");
		}
	
	}

}
