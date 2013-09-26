package edu.bionic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.bionic.model.Menu_Request;

public class MenuRequestDao implements GenericDao<Menu_Request, Integer> {
	@PersistenceContext(unitName = "Restaurant") EntityManager em;

	@Override
	public Integer create(Menu_Request newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public void update(Menu_Request transientObject) {
		em.merge(transientObject);
		
	}

	@Override
	public Menu_Request read(Integer id) {
		Menu_Request menuRequest = em.find(Menu_Request.class, id);
		return menuRequest;
	}



	

}
