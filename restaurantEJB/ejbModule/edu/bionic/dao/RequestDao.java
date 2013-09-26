package edu.bionic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.bionic.model.Request;

@Stateless
@LocalBean
public class RequestDao implements GenericDao<Request, Integer> {
	@PersistenceContext(unitName = "Restaurant")
	EntityManager em;

	@Override
	public Integer create(Request newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public Request read(Integer id) {
		Request request = em.find(Request.class, id);
		return request;
	}

	@Override
	public void update(Request transientObject) {
		em.merge(transientObject);
	}

	public List<Request> findAllByClient(int clientid) {
		TypedQuery<Request> query = em.createNamedQuery("Request.findAllByClient",
				Request.class);
		query.setParameter("clientid", clientid);
		List<Request> results = query.getResultList();
		return results;
	}
	
	public List<Request> getRange(Integer firstRow, Integer numberOfRows, String like) {
		TypedQuery<Request> query = em.createNamedQuery("Request.findAllNotPaid",
				Request.class);
		query.setParameter("paidStatus", Request.PAID);
		query.setParameter("like", "%"+like+"%");
		query.setFirstResult(firstRow);
		query.setMaxResults(numberOfRows);
		List<Request> results = query.getResultList();
		return results;
	}
	
	public Integer getRowCount() {
		Query query = em.createNativeQuery("select Count(*) from requests where status <>" + Request.PAID);
		Integer result = ((BigInteger) query.getSingleResult()).intValue();
		return result;
	}
	
}
