package controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import yPlayer.Player;

/**
 * Servlet implementation class AuthenticationPage
 */
@WebServlet("/AuthenticationPage")
public class AuthenticationPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Page cannot be accessed!Go to login!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String referer = request.getHeader("Referer");
		if ((referer!=null) &&(referer.equals("http://localhost:8080/yPlayer/LoginPage"))) {
			
		response.setContentType("text/html");
		
		String userName = request.getParameter("Username");
		String password = request.getParameter("Password");
		
		HashFunction hf = Hashing.sha256();
		HashCode hc = hf.newHasher().putString(password, Charsets.UTF_8).hash();
		password = hc.toString();
	
		Player play = Querries.findplayer(userName);
		
		
		if (play == null) {
		
			response.sendRedirect("http://localhost:8080/yPlayer/UnsuccessfullLogin");
		} else {

			if ((userName != null) && (play.getLoginPassword().equals(password))) {
				
				
				String tokenUni = "tokenUni";
				Querries.hashFunction(tokenUni);
				HttpSession session = request.getSession(true);
				session.setAttribute(tokenUni,play);
				session.setMaxInactiveInterval(60*10);
				
				response.sendRedirect("http://localhost:8080/yPlayer/SuccessfullLogin");

			} else {
				response.sendRedirect("http://localhost:8080/yPlayer/LoginPage");

			}

		}
		}
	}
}
