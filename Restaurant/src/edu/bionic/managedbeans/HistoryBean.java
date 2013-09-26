package edu.bionic.managedbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import edu.bionic.dao.RequestDao;
import edu.bionic.model.Request;

@ManagedBean(name = "historyBean")
@ViewScoped()
public class HistoryBean {
	@EJB
	private RequestDao requestDao;
	@ManagedProperty(value = "#{securityUtilities}")
	private SecurityUtilities securityUtilities;
	private List<Request> list;
	private Request currentOrder;

	@PostConstruct
	private void loadList() {
		list = requestDao.findAllByClient(securityUtilities.getUser()
				.getClient().getId());
	}
	
	public void askForCheque() {
		currentOrder.setStatus(Request.WAITING_TO_BE_CHEQUED);
		requestDao.update(currentOrder);
	}
	
	public void payForOrder() {
		currentOrder.setStatus(Request.PAID);
		requestDao.update(currentOrder);
	}

	public SecurityUtilities getSecurityUtilities() {
		return securityUtilities;
	}

	public void setSecurityUtilities(SecurityUtilities securityUtilities) {
		this.securityUtilities = securityUtilities;
	}

	public List<Request> getList() {
		return list;
	}

	public void setList(List<Request> list) {
		this.list = list;
	}

	public Request getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(Request currentOrder) {
		this.currentOrder = currentOrder;
	}

}
