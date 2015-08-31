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
 * Servlet implementation class SuccessfullLogin
 */
@WebServlet("/SuccessfullLogin")
public class SuccessfullLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuccessfullLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String referer = request.getHeader("Referer");
		request.getSession().getAttribute("tokenUni");
		if ((referer != null) && (referer.equals("http://localhost:8080/yPlayer/LoginPage")
				|| (referer.equals("http://localhost:8080/yPlayer/Deposit")
						|| (referer.equals("http://localhost:8080/yPlayer/Withdraw"))))) {
			PrintWriter out = response.getWriter();
			JSONObject jobject = new JSONObject().put("Status", "Sucessfull login");
			String output = " <!doctype html public \"-//w3c//dtd html 5.0" + "transitional//en\">\n";
			out.println(output + jobject + "<html>\n" + "<head><title></title></head>\n"
					+ "<H2 ALIGN=\"CENTER\">Choose an option</H2>" + "<body bgcolor=\"#6FFDE6\">\n" + "<CENTER>"
					+ "<form action=\"Deposit\" >" + "<input type=\"submit\" value=\"Deposit\" />" + "</form>"
					+ "<form action=\"Withdraw\" >" + "<input type=\"submit\" value=\"Withdraw\" />" + "</form>"
					+ "<CENTER>" + "<form action=\"http://localhost:8080/yPlayer/LoginPage\">"
					+ "<input type=\"submit\" value=\"Back to login page\" />" + "</form>");

		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
