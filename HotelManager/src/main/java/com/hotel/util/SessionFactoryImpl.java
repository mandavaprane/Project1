package com.hotel.util;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.primefaces.event.RateEvent;

import com.hotel.managedbean.RoomRatesManagerBean;
import com.hotel.managedbean.SessionManagerBean;



public class SessionFactoryImpl {

	
	private static SessionFactory sfactory;

	private SessionFactoryImpl() {
	};

	public static SessionFactory returnService() {
		synchronized (SessionFactory.class) {
			if (sfactory == null) {
				sfactory = new AnnotationConfiguration().configure()
						.buildSessionFactory();
				return sfactory;
			} else {
				return sfactory;
			}
		}
	}

	public static SessionManagerBean getSessionManagerBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();
		
		SessionManagerBean sessionUserBean = (SessionManagerBean) app.evaluateExpressionGet(facesContext, "#{sessionManagerBean}",  SessionManagerBean.class);
			
		return sessionUserBean;
	}
	
	public static SessionManagerBean getSessionManagerBean(FacesContext facesContext) {
		Application app = facesContext.getApplication();
		SessionManagerBean sessionUserBean = (SessionManagerBean) app
				.evaluateExpressionGet(facesContext, "#{sessionManagerBean}",
						SessionManagerBean.class);
		return sessionUserBean;
	}
	
	public static RoomRatesManagerBean getApplicationManagerBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();
		
		RoomRatesManagerBean rateManagerBean = (RoomRatesManagerBean) app
				.evaluateExpressionGet(facesContext, "#{RateManagerBean}",
						RoomRatesManagerBean.class);
		return rateManagerBean;
	}
/*	public static FileUploaderManagedBean getFileUploadBean() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application app = facesContext.getApplication();
		FileUploaderManagedBean fileUploaderBean = (FileUploaderManagedBean) app
				.evaluateExpressionGet(facesContext, "#{fileUploaderBean}",
						FileUploaderManagedBean.class);
		return fileUploaderBean;
	}	*/
}
