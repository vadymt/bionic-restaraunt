package edu.bionic.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.bionic.model.Client;

@Stateless
public class ClientDao implements GenericDao<Client, Integer> {
	@PersistenceContext(unitName = "Restaurant") EntityManager em;

	@Override
	public Integer create(Client newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public Client read(Integer id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	@Override
	public void update(Client transientObject) {
		em.merge(transientObject);
	}

}
