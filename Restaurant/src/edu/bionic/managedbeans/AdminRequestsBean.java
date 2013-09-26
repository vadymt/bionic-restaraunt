package edu.bionic.managedbeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import edu.bionic.dao.RequestDao;
import edu.bionic.datamodels.DynamicDataModel;
import edu.bionic.model.Request;

@ManagedBean(name = "adminRequestBean")
@ViewScoped()
public class AdminRequestsBean extends DynamicDataModel<Request> {
	@EJB
	private RequestDao requestDao;
	private Request currentOrder;
	private String like = "";

	@Override
	protected List<Request> getItemsByrange(Integer firstRow2, int intValue) {
		return requestDao.getRange(firstRow2, intValue, like);
	}

	@Override
	public int getRowCount() {
		if (null == totalRows) {
			totalRows = requestDao.getRowCount();
			return this.totalRows;
		}
		return totalRows;
	}

	public void sendToKitchen() {
		currentOrder.setStatus(Request.IN_KITCHEN);
		requestDao.update(currentOrder);
	}

	public void chequeOrder() {
		currentOrder.setStatus(Request.CHEQUED);
		requestDao.update(currentOrder);
	}

	public void likeChangeListener(ValueChangeEvent e) {
		like = e.getNewValue().toString();
	}

	public Request getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Request currentOrder) {
		this.currentOrder = currentOrder;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	};

}
