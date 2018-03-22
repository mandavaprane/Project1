package com.hotel.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.hotel.entity.Cusers;
import com.hotel.services.CUserService;
import com.hotel.services.CUserServiceImpl;
import com.hotel.util.SessionFactoryImpl;

@ManagedBean(name="RequestBean")
@ViewScoped
public class RequestManagerBean implements Serializable {

	private transient UIComponent oldPasswordMessage;
	
	private transient UIComponent successPasswordChangeMessage;
	
	private String oldPassword;
	
	private String newPassword;
	
	private String retypePassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	
	
	public UIComponent getOldPasswordMessage() {
		return oldPasswordMessage;
	}

	public void setOldPasswordMessage(UIComponent oldPasswordMessage) {
		this.oldPasswordMessage = oldPasswordMessage;
	}

	
	
	public UIComponent getSuccessPasswordChangeMessage() {
		return successPasswordChangeMessage;
	}

	public void setSuccessPasswordChangeMessage(
			UIComponent successPasswordChangeMessage) {
		this.successPasswordChangeMessage = successPasswordChangeMessage;
	}

	public boolean changePassword(){
		SessionManagerBean sessionBean= SessionFactoryImpl.getSessionManagerBean();
		if(!oldPassword.equals(sessionBean.getCurrentCuser().getPassword())){
			FacesContext.getCurrentInstance().addMessage(
					oldPasswordMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
							"Invalid Current Password. Enter Correct One."));
		return false;
		
	}
		else{
			CUserService userService= new CUserServiceImpl();
			Cusers updatedUser=userService.updateUser(sessionBean.getCurrentCuser(), oldPassword, newPassword);
			
			if(updatedUser==null){
				System.out.println("Due to some problem we are not able to process you request. Try again Later.");
				FacesContext.getCurrentInstance().addMessage(
						successPasswordChangeMessage.getClientId(),
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "",
								"Due to some problem we are not able to process you request. Try again Later."));
				return false;
			}else
			{
				
			sessionBean.setCurrentCuser(updatedUser);
			FacesContext.getCurrentInstance().addMessage(
					successPasswordChangeMessage.getClientId(),
					new FacesMessage(FacesMessage.SEVERITY_INFO, "",
							"Your Password has been updated successfully."));
			}
			
			return true;
		}
			
	}	
	
}
