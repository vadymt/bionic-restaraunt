package edu.bionic.converters;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import edu.bionic.managedbeans.MenuEditorBean;
import edu.bionic.model.Menu;

@ManagedBean(name = "menuConverterBean")
@RequestScoped
// @FacesConverter("menuConverter")
public class MenuConverter implements Converter {
    @ManagedProperty(value = "#{menuEditorBean}")
    private MenuEditorBean menuEditorBean;

    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
        Menu menu = new Menu();
        menu.setName(arg2);
        for (Menu m : menuEditorBean.getSelectItems()) {
            if (m.getName().equalsIgnoreCase(menu.getName())) {
                return m;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        if (arg2 instanceof Menu) {
            return ((Menu) arg2).getName();
        }
        return null;
    }

    public MenuEditorBean getMenuEditorBean() {
        return menuEditorBean;
    }

    public void setMenuEditorBean(MenuEditorBean menuEditorBean) {
        this.menuEditorBean = menuEditorBean;
    }

}
