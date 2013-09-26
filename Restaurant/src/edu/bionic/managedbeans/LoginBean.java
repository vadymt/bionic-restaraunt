package edu.bionic.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.Size;

@ManagedBean(name = "loginBean")
@ViewScoped()
public class LoginBean {
	
		@Size(min = 2, max = 20, message = "Must be betwen 2 and 20 chars")
	    private String login;
	    @Size(min = 1, message = "Please Enter your password")
	    private String password;

	    //getters and setters...

	    public String getLogin() {
			return login;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

	
}
