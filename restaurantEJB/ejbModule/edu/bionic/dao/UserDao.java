package edu.bionic.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import edu.bionic.model.User;

@Stateless
@LocalBean
public class UserDao implements GenericDao<User, Integer> {
	@PersistenceContext(unitName = "Restaurant")
	EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.bionic.dao.UserDaoLocal#create(edu.bionic.model.User)
	 */

	@Override
	public Integer create(User newInstance) {
		// em.getTransaction().begin();
		em.persist(newInstance);
		// em.getTransaction().commit();
		return newInstance.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.bionic.dao.UserDaoLocal#read(java.lang.Integer)
	 */

	@Override
	public User read(Integer id) {
		// EntityManager em =
		// EMFactory.getEntityManagerFactory().createEntityManager();
		// em.getTransaction().begin();
		User user = em.find(User.class, id);
		// em.close();
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.bionic.dao.UserDaoLocal#update(edu.bionic.model.User)
	 */

	@Override
	public void update(User transientObject) {
		// EntityManager em =
		// EMFactory.getEntityManagerFactory().createEntityManager();
		// em.getTransaction().begin();
		em.merge(transientObject);
		// em.getTransaction().commit();
	}
	
	public User readByLogin(String login) {
		Query query = em.createNamedQuery("User.findByLogin");
		query.setParameter("userlogin", login);
		User user = (User) query.getSingleResult();
		return user;
	}

}
