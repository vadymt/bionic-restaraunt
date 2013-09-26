package edu.bionic.managedbeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "menuUtilities")
@ViewScoped
public class MenuUtilities {

    public String register() {

        return "registration.xhtml";
    }
    public String mainpage() {

        return "main.xhtml";
    }

    public String adminpage() {

        return "adminpage.xhtml";
    }

    public String cartpage() {

        return "shoppingcart.xhtml";
    }

    public String contacts() {

        return "contacts.xhtml";
    }

    public String login() {

        return "loginrestrictedpage.xhtml?faces-redirect=true";
    }

    public String orderHistory() {

        return "ordershistory.xhtml";
    }

    public String changeinfo() {

        return "chginfo.xhtml";
    }
    public String homepage() {

        return "homepage.xhtml?faces-redirect=true";
    }
    public String changemenu() {

        return "cngmenu.xhtml";
    }
}
