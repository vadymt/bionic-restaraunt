package edu.bionic.managedbeans;

import java.security.Principal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.bionic.dao.UserDao;
import edu.bionic.model.User;

@ManagedBean(name = "securityUtilities")
@SessionScoped
public class SecurityUtilities {
	@EJB
	private UserDao userDao;
	private User user = null;

	private void setCurrentUser() {
		Principal currentUserPrincipal = FacesContext.getCurrentInstance()
				.getExternalContext().getUserPrincipal();
		if (currentUserPrincipal != null) {
			this.setUser(userDao.readByLogin(currentUserPrincipal.getName()));
		}
	}

	public User getUser() {
		if (user == null) {
			setCurrentUser();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		user = null;
		return "main.xhtml?faces-redirect=true";
	}

}
