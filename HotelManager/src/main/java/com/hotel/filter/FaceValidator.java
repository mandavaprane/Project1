package com.hotel.filter;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * check request is valid for specified url
 * @author praneetha. 
 */

public class FaceValidator implements PhaseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void afterPhase(PhaseEvent event) {
 	FacesContext facesContext = event.getFacesContext();
		String pageRequested = facesContext.getViewRoot().getViewId();
		HttpSession session = (HttpSession) facesContext.getExternalContext()
				.getSession(false);
		System.out.println("requested page is-------->" + pageRequested);
 		if ((session == null || session.getAttribute("username") == null)
				&& ((!pageRequested.contains("index.xhtml")
						&& !pageRequested.contains("BookRoom.xhtml") ))) {
			NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			System.out.println("session is null....");
			nh.handleNavigation(facesContext, null,
					"/index.xhtml?faces-redirect=true");

			return;
		} else if (session != null	&& (pageRequested.contains("index.xhtml") ||
					pageRequested.contains("BookRoom.xhtml"))
				&& session.getAttribute("username") != null
				&& session.getAttribute("password") != null) {
			 	NavigationHandler nh = facesContext.getApplication()
					.getNavigationHandler();
			System.out.println("session not null....");
			nh.handleNavigation(facesContext, null,
					"/Home.xhtml?faces-redirect=true");

			return;

		}

	}

	public void beforePhase(PhaseEvent event) {
		
		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}
}
