package servlets;

import javax.ejb.EJB;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import control.PlayerEJB;

/**
 * Application Lifecycle Listener implementation class SessionLister
 *
 */
@WebListener
public class SessionLister implements HttpSessionListener {
	@EJB
	private PlayerEJB playerEJB;

    public SessionLister() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
         // TODO Auto-generated method stub
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  {
    	
    }
	
}
