package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Queries;
import model.PlayerProfile;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
		List<PlayerProfile> player =Queries.showAllPlayerProfile();
		PrintWriter out = response.getWriter();
		out.println(output + "<html>\n" + "<head><title></title></head>");
			out.println("<body>");
			out.println("<form method=\"POST\" action=\"RegSucPlayer\" >");	
			out.println("<tr><td>Please insert login name</td><td><br></br>");
			out.println("<input type=\"text\" name=\"loginName\"/><br></br>");
			out.println("<tr><td>Please insert password</td><td><br></br>");
			out.println("<input type=\"password\" name=\"loginPassword\"/><br></br>");		
			for (int i = 0; i < player.size(); i++) {
				out.println("<option value=\""+player.get(i).getPlayerFK()+" \"> ");
				out.println(""+player.get(i).getPlayerFK()+"");
			}	
			out.println("<input type=\"submit\" value=\"register\"/>");
			out.println("</form>");
			out.println("</body>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
