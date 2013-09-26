package edu.bionic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import edu.bionic.model.Role;

public class RoleDao implements GenericDao<Role, Integer> {
	@PersistenceContext(unitName = "Restaurant") EntityManager em;

	@Override
	public Integer create(Role newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public Role read(Integer id) {
		Role role = em.find(Role.class, id);
		return role;
	}

	@Override
	public void update(Role transientObject) {
		em.merge(transientObject);
	}

}
