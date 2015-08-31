package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import yPlayer.Player;

/**
 * Servlet implementation class SignUpPage
 */
@WebServlet("/SignUpPage")
public class SignUpPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String inputName = request.getParameter("username");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		out.println("<html>");
		out.println("<head>");
		out.println("\t<title>Registration</title>");
		out.println("</head>");
		out.println("<BODY BGCOLOR=\"#6FFDE6\">" + "<H2 ALIGN=\"CENTER\">Welcome to our SignUp page</H2>"
				+ "<form method=\"POST\"" + "<Center>" + "<Center>" + "Enter username : "
				+ "<input type =\"text\" name=\"username\"><br/>" + "<Center>" + "Enter password : "
				+ "<input type =\"password\" name=\"password\"><BR/>" + "<Center>" + "Enter password once more : "
				+ "<input type =\"password\" name=\"password2\"><BR/>"
				+ "<input type = \"Submit\" value = \"Register\">"

		+ "</form>");
		out.println("</body></html>");
		Player pl = new Player();
	Player existingName = Querries.findplayer(pl.getLoginName());

		
		if ((inputName != null) && (password != null) && (inputName!= String.valueOf(existingName))) {
			if (password.equals(password2)) {

				HashFunction hf = Hashing.sha256();
				HashCode hc = hf.newHasher().putString(password, Charsets.UTF_8).hash();
				password = hc.toString();
				JSONObject jobject = new JSONObject().put("Status", "Sucessfull registration");
				out.println(jobject);
				
				try {
					Querries.addPlayer(inputName, password);
				} catch (Exception e) {
					response.sendRedirect("http://localhost:8080/yPlayer/ExistingLoginName");
				}

			} else {
				JSONObject jobjectMismatch = new JSONObject().put("Status", "Password mismatch");
				out.println(jobjectMismatch);
			}

		}
		out.println("<FORM ACTION=\"http://localhost:8080/yPlayer/LoginPage\" Method =\"GET\">"
				+ "<INPUT TYPE=\"SUBMIT\" VALUE=\"Go to login\"> <!-- Press this to submit form -->" + "</CENTER>"
				+ "</FORM>");
	}


}
