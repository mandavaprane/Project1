package com.hotel.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.hotel.entity.Cusers;
import com.hotel.services.CUserService;
import com.hotel.services.CUserServiceImpl;
import com.hotel.util.SessionFactoryImpl;


@ManagedBean
@ViewScoped
public class LoginBean  implements Serializable {

		private static final long serialVersionUID = 1L;
 
		
		private String username;

		private String logpass;
		
		private boolean  isUserAdmin;
		
		private List<Cusers> employeelist;
		
		private Cusers loggedinuser;

		public String getLogpass() {
			return logpass;
		}

		public void setLogpass(String logpass) {
			this.logpass = logpass;
		}
		
		
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public boolean isUserAdmin() {
			return isUserAdmin;
		}

		public void setUserAdmin(boolean isUserAdmin) {
			this.isUserAdmin = isUserAdmin;
		}

		
		
		public Cusers getLoggedinuser() {
			return loggedinuser;
		}

		public void setLoggedinuser(Cusers loggedinuser) {
			this.loggedinuser = loggedinuser;
		}

		/**
		 * @author praneetha.
		 * login to user and set session params
		 */

		
		
		
	public String  doLogin(){
		CUserService userService= new CUserServiceImpl();
		List<Cusers> adminUserList= userService.getAdminUsers();
		if(adminUserList.size()<1){
			Cusers adminUser= new Cusers();
			adminUser.setFirstname("admin");
			adminUser.setLastname("admin");
			adminUser.setUsername("admin");
			adminUser.setPassword("admin@123");
			adminUser.setRoletype("administrator");
			adminUser.setEmail("admin@hotelmanager.com");
			Cusers updated= userService.createUser(adminUser);
			if(updated!=null)
				System.out.println("Default User Created!");
			else
				System.out.println("We are not able to create default user! please check for this.");
		}
		
		CUserService loginservice= new CUserServiceImpl();
		employeelist=loginservice.getUserList();
		
		if(employeelist==null || employeelist.size()<1 )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: Invalid Username or Password.","!"));
			return null;
		}
		
	

		
	
		boolean valid=loginservice.isUserValidOrNot(employeelist,this);
		
		
		if(!valid)
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error: Invalid Username or Password.","!"));
			return null;
		}
		else
	
		{
			
			SessionFactoryImpl.getSessionManagerBean().setSessionForCurrentUser(this.getLoggedinuser());
			if(SessionFactoryImpl.getSessionManagerBean().getCurrentCuser().getRoletype().equals(SessionManagerBean.USERROLE_ADMIN)){
					SessionFactoryImpl.getSessionManagerBean().setIsadmin(true);
				}
			else{
				//SessionFactoryImpl.getSessionManagerBean().setIstemporaryUser(false);
				
			}
				
			
			loginservice.setLastLogin(new Date());
			
			return "Home.xhtml?faces-redirect=true";
		}
		
	}
	
	
	/**
	 * @author praneetha.
	 * logout user and invalidate session
	 */

	public String doLogout()
	{
		new CUserServiceImpl().setLastLogin(new Date());
		FacesContext facesContext = FacesContext.getCurrentInstance();
	    Application app=facesContext.getApplication();
		SessionManagerBean sessionUserBean= (SessionManagerBean) app.evaluateExpressionGet(facesContext, "#{sessionManagerBean}", SessionManagerBean.class);
		 	if(sessionUserBean.isIsuserloggedin())
		{
		sessionUserBean.logout();
		
		
			return "index.xhtml?faces-redirect=true";
		}
		
		return "index.xhtml?faces-redirect=true";
		
		
	}

	
		

		
	}

	


