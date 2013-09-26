package edu.bionic.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import edu.bionic.dao.MenuDao;
import edu.bionic.datamodels.DynamicDataModel;
import edu.bionic.model.Menu;

@ManagedBean(name = "menuBean")
@ViewScoped()
public class MenuBean extends DynamicDataModel<Menu> {
	@EJB
	private MenuDao menuDao;
	/**
	 * asc - ascending, false - descending;
	 */
	private String sortOrder;
	private String columnName;

	public MenuBean() {
		sortOrder = "desc";
		columnName = "id";
	}

	@Override
	protected List<Menu> getItemsByrange(Integer firstRow2, int intValue) {
		return menuDao.getRange(firstRow2, intValue, true, sortOrder,
				columnName);
	}

	@Override
	public int getRowCount() {
		if (null == totalRows) {
			totalRows = menuDao.getRowCount();
			return this.totalRows;
		}
		return totalRows;
	}

	public void changeSortOrder() {
		if(sortOrder.equals("desc")) {
			sortOrder = "asc";
		} else {
			sortOrder = "desc";
		}
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

}
