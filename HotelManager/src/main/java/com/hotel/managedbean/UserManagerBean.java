package com.hotel.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.hotel.entity.Cusers;
import com.hotel.services.CUserService;
import com.hotel.services.CUserServiceImpl;

@ManagedBean(name="CuserBean")
@ViewScoped
public class UserManagerBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cusers user= new Cusers();

	private transient UIComponent showResultMessage;
	
	private transient UIComponent emailMessage;
	
	private transient UIComponent usernameMessage;
	
	public Cusers getUser() {
		return user;
	}

	public void setUser(Cusers user) {
		this.user = user;
	}

	public UIComponent getShowResultMessage() {
		return showResultMessage;
	}

	public void setShowResultMessage(UIComponent showResultMessage) {
		this.showResultMessage = showResultMessage;
	}
	
	
	
	public UIComponent getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(UIComponent emailMessage) {
		this.emailMessage = emailMessage;
	}

	public UIComponent getUsernameMessage() {
		return usernameMessage;
	}

	public void setUsernameMessage(UIComponent usernameMessage) {
		this.usernameMessage = usernameMessage;
	}

	public void createMember(){
		
		CUserService userService = new CUserServiceImpl();

		
		String isexist = userService.isUserNameOrEmailExist(user);
		boolean invalidstatus = false;
		if (isexist.equalsIgnoreCase("invalidname") || user.getUsername().equalsIgnoreCase("admin")) {
			FacesContext.getCurrentInstance().addMessage(
					usernameMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Username Already Exists. Please Choose another."));
			invalidstatus = true;
		}

		isexist = userService.isUserNameOrEmailExist(user);

		if (isexist.equalsIgnoreCase("invalidid")) {
			FacesContext.getCurrentInstance().addMessage(
					emailMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"E-mail Already Exists. Please Choose another."));
			invalidstatus = true;
		}

		if (invalidstatus == true) {
			return ;
		}

		else {
			 

			 userService.createUser(user);
			 user=new Cusers();
			 FacesContext.getCurrentInstance().addMessage(
						showResultMessage.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"Congratulation! New Member has been added to organization."));
			 
			 return ;
		}

		
 
	 	
		
		
		
	}
}
