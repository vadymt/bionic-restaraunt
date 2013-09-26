package edu.bionic.managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.*;
import javax.faces.bean.*;
import javax.faces.context.*;

import edu.bionic.dao.UserDao;
import edu.bionic.model.Client;
import edu.bionic.model.Role;
import edu.bionic.model.User;

@ManagedBean(name = "userInfoBean")
@RequestScoped()
public class UserInfoBean {

    @EJB
    private UserDao userDao;
    private User user;
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    @ManagedProperty(value = "#{securityUtilities}")
    private SecurityUtilities secUtilities;

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
    public UserInfoBean() {
        user = new User();
        user.setClient(new Client());
        user.getClient().setUser(user);
        user.setRole(new Role());
        user.getRole().setId(2);

    }

    @PostConstruct
    public void user() {
        setUser(secUtilities.getUser());
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String updateUser() {
        // if (!user.getPassword().equals(confirmpassword)) {
        // addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
        // "Passwords don't match!", null));
        // return "passwordsdontmatch";
        // } else {
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Information updated!", null));

        // UserDao dao=new UserDao();
        userDao.update(user);
        return "success";
        // }
    }

    public String changePassword() {
        if (!user.getPassword().equals(oldPassword)) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Passwords don't match!", null));
            return "passwordsdontmatch";
        } else if (newPassword.equals(confirmNewPassword)) {
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Password updated!", null));
            user.setPassword(newPassword);
            // UserDao dao=new UserDao();
            userDao.update(user);
            return "success";
        }
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Passwords don't match!", null));
        return "passwordsdontmatch";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void resetRegistration() {
        // this.username = null;
        // this.password = null;
        // this.confirmpassword = null;
        // this.firstname = null;
        // this.lastname = null;
    }

    public SecurityUtilities getSecUtilities() {
        return secUtilities;
    }

    public void setSecUtilities(SecurityUtilities secUtilities) {
        this.secUtilities = secUtilities;
    }

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

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

}
