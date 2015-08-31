package view;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;



/**
 * Servlet implementation class DepositServlet
 */
@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DepositServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = null;
		Cookie[] cookies = request.getCookies();
		if(cookies !=null){
		for(Cookie cookie : cookies){
		    if(cookie.getName().equals("user")) {
		    	token = cookie.getValue();
		    	
		    	HashFunction hf = Hashing.sha1();
				HashCode hc = hf.newHasher().putString(token, Charsets.UTF_8).hash();
		    	
		    	JSONObject jo = new JSONObject().append("hash", hc.toString()).append("status", "ok");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE HTML ><HTML><HEAD><TITLE>Login Post</TITLE></HEAD><BODY>"
						+ "<form method = \"Post\" action=\"http://localhost:8080/PlayerProject/BalanceServlet\""
						+ "<H1>Deposit</H1><p>" 						
						+ "<p>"
						+ jo
						+ "<p>"
						+ "<input type=\"text\" name = \"dep\"></input>" 
						+ "<input type=\"submit\" value=\"deposit\">"
						+ "</form>");
				
	
		    }
		    else if(token == null) {
		    	response.sendRedirect("Login.html");
		    }
		}
		
		}
		
		
	
	}

}
