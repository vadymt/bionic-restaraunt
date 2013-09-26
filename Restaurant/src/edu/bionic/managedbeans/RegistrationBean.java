package edu.bionic.managedbeans;

import javax.ejb.EJB;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

import org.jboss.security.auth.spi.Util;

import edu.bionic.dao.UserDao;
import edu.bionic.model.Client;
import edu.bionic.model.Role;
import edu.bionic.model.User;

@ManagedBean(name = "regBean")
@RequestScoped()
public class RegistrationBean {

	@EJB
	private UserDao userDao;
	private User user;
	private String confirmpassword;

	// private String username;
	// private String password;
	// private String confirmpassword;
	// private String firstname;
	// private String lastname;
	//
	// public String getPassword() {
	// return password;
	// }
	//
	public RegistrationBean() {
		user = new User();
		user.setClient(new Client());
		user.getClient().setUser(user);
		user.setRole(new Role());
		user.getRole().setId(2);
	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String addUser() {
		if (!user.getPassword().equals(confirmpassword)) {
			user.setPassword(null);
			setConfirmpassword(null);
			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Passwords don't match!", null));
			return "passwordsdontmatch";
		} else {
			addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
					"User Registration Successful!!!", null));

			// UserDao dao=new UserDao();
			user.setPassword(Util.createPasswordHash("SHA-256",
					Util.BASE64_ENCODING, "UTF-8", null, user.getPassword()));
			userDao.create(user);

			return "registersuccess.xhtml?faces-redirect=true";
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void resetRegistration() {
		this.user = null;
	}

}
