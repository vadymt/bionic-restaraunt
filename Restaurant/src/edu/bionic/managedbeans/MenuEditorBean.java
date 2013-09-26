package edu.bionic.managedbeans;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import edu.bionic.dao.MenuDao;
import edu.bionic.model.Menu;

@ManagedBean(name = "menuEditorBean")
@ViewScoped
public class MenuEditorBean {

    @EJB
    private MenuDao menuDao;
    private Menu menu = null;
    private String name = null;
    private String description = null;
    private BigDecimal cost = null;
    private Menu selectedMenu;
    private List<Menu> selectItems;

    public MenuEditorBean() {
    	menu = new Menu();
    }

    @PostConstruct
    public void init() {
        selectItems = menuDao.findAll();
        // selectedMenu = selectItems.get(0);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public String addPosition() {
        // TODO check by name
        menuDao.create(menu);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, menu.getName()
                + " has been succuesfully added to menu!", null));
        return "success";
    }

    public String editPosition() {
    	menu.setCost(cost);
    	menu.setDescription(description);
    	menu.setName(name);
        menuDao.update(menu);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, menu.getName()
                + " has been succesfully updated!", null));
        return "success";
    }

    public String removePosition() {
        menu.setActive(false);
        menuDao.update(menu);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, menu.getName()
                + " has been succesfully removed from menu!", null));
        return "success";
    }

    public void currentCostListener(ValueChangeEvent e) {
        String s = e.getNewValue().toString();
        setCost(new BigDecimal(s));
    }

    public void currentNameListener(ValueChangeEvent e) {
        String s = e.getNewValue().toString();
        setName(s);
    }

    public void currentDescriptionListener(ValueChangeEvent e) {
        String s = e.getNewValue().toString();
        setDescription(s);
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public List<Menu> getSelectItems() {
        return selectItems;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }

    public void setSelectItems(List<Menu> selectItems) {
        this.selectItems = selectItems;
    }

    public void valueChanged(ValueChangeEvent event) {
        if (null != event.getNewValue()) {
            selectedMenu = (Menu) event.getNewValue();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

}
