package com.hotel.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hotel.entity.Cusers;
import com.hotel.services.LoginActivityService;
import com.hotel.services.LoginActivityServiceImpl;
import com.hotel.util.SessionFactoryImpl;




/**
 * @author praneetha.
 * @return set session params and store user and conersation property
 */
@ManagedBean
@SessionScoped
public class SessionManagerBean implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2162462649939438825L;
	public static final String USERROLE_ADMIN="administrator";
	public static final String USERROLE_BOOKINGCLERK="bookingclerk";
	
	
	
	private boolean isuserloggedin;
	
	private String username;

	private Cusers currentCuser;
	
	private String password;
	
	
	private boolean isadmin;
	
	

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	public boolean isIsuserloggedin() {
		return isuserloggedin;
	}

	public void setIsuserloggedin(boolean isuserloggedin) {
		this.isuserloggedin = isuserloggedin;
	}

	public boolean isIsadmin() {
		return isadmin;
	}

	public void setIsadmin(boolean isadmin) {
		this.isadmin = isadmin;
	}

	/**@author praneetha.
	 * @Method checks user loggedin or not
	 * return true if user already loggedin,
	 * false otherwise. 
	 * @param user
	 */
	
	
	
	
	

	
	
	/* ****************************************** Methods ******************************************* */
	public boolean isUserAsAdmin()  
	{
		try{
		FacesContext facesContext = FacesContext.getCurrentInstance();
	   SessionManagerBean sessionUserBean = SessionFactoryImpl.getSessionManagerBean();
	
    	
	    if(sessionUserBean.getCurrentCuser().getRoletype().equals(SessionManagerBean.USERROLE_ADMIN))
    	{
	    	this.setIsadmin(true);	
	    	return true;
    	}
    	
    	else {
    		/*ExternalContext externalContext = facesContext.getExternalContext();
		 	externalContext.redirect("index.xhtml?faces-redirect=true");*/
		}
		}catch(Exception e){
			System.out.println("Exception -->>"+e.toString());	
		}
		
			return false;
		
	}
	
	
	
	
	public String check()
	{
		SessionManagerBean sessionBean=SessionFactoryImpl.getSessionManagerBean();
		if(sessionBean==null || sessionBean.getCurrentCuser() == null)
		{
			
			logout();
			return "index.xhtml?faces-redirect=true";
		}
		return "Home.xhtml?faces-redirect=true";
	}
	
	
	
	
	public void logout()
	{
		
		SessionManagerBean sessionBean=SessionFactoryImpl.getSessionManagerBean();
		Cusers currentUser= sessionBean.getCurrentCuser();
		
		LoginActivityService loginService=new LoginActivityServiceImpl();
		
		loginService.addActivityLogEntry("Logout", "Success", new Date(), currentUser);
		
		
		try{
		setDefault();
		}catch (Exception e) {
			loginService.addActivityLogEntry("Logout", "Failed", new Date(), currentUser);
			 System.out.println("Exception ocuures : "+e.toString());
		}
		
	
	}
	
	
	
	/**@author Namit
	 * Method set session parameters and mark current user as loggedin.
	 * @param user
	 */
	
	public void setSessionForCurrentUser(Cusers user)
	{
	
		this.setCurrentCuser(user);
		this.isuserloggedin=true;
		setSessionParams();
	}
	
	
	/**@author Namit
	 * Method reset session parameters and mark current user as loggedout.
	 * @param user
	 * @throws IOException 
	 */
	
	public void setDefault() throws IOException
	{
		System.out.println("in setDefault()......");
		
		this.setCurrentCuser(null);
		this.setPassword("");
		
		this.isuserloggedin=false;
		System.out.println("removing session params...");
		removeSessionParams();
	}

	
	/**@author Namit
	 * Method removes session parameters from session.
	 * @param user
	 */
	public void removeSessionParams()
	{
		
		System.out.println("In remove session param()....");
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession httpSession = request.getSession(false); 
		httpSession.removeAttribute("username");
		httpSession.removeAttribute("password");
		System.out.println("now invalidating session...");
		httpSession.invalidate();
		
	}
	
	/**@author Namit
	 * @return void: set session parameters into session.
	 * @param user
	 */
	public void setSessionParams()
	{
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();  
		HttpSession httpSession = request.getSession(false); 
		SessionManagerBean sessionUserBean = SessionFactoryImpl.getSessionManagerBean();
		httpSession.setAttribute("username", sessionUserBean.getCurrentCuser().getUsername());
		httpSession.setAttribute("password", sessionUserBean.getCurrentCuser().getPassword());
		httpSession.setAttribute("userId", sessionUserBean.getCurrentCuser().getUser_id());
	
	}

	
	/**
	 * @return the currentCuser
	 */
	public Cusers getCurrentCuser() {
		return currentCuser;
	}

	/**
	 * @param currentCuser the currentCuser to set
	 */
	public void setCurrentCuser(Cusers currentCuser) {
		this.currentCuser = currentCuser;
	}



	
	

	

}
