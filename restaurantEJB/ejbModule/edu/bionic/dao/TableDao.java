package edu.bionic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.bionic.model.Tables;

public class TableDao implements GenericDao<Tables, Integer> {
	@PersistenceContext(unitName = "Restaurant") EntityManager em;

	@Override
	public Integer create(Tables newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public Tables read(Integer id) {
		Tables tables = em.find(Tables.class, id);
		return tables;
	}

	@Override
	public void update(Tables transientObject) {
		em.merge(transientObject);
	}

}
