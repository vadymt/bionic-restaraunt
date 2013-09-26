package edu.bionic.dao;

import java.math.BigInteger;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import edu.bionic.model.Menu;

@Stateless
@LocalBean
public class MenuDao implements GenericDao<Menu, Integer> {
	@PersistenceContext(unitName = "Restaurant")
	EntityManager em;

	@Override
	public Integer create(Menu newInstance) {
		em.persist(newInstance);
		return newInstance.getId();
	}

	@Override
	public Menu read(Integer id) {
		Menu menu = em.find(Menu.class, id);
		return menu;
	}

	@Override
	public void update(Menu transientObject) {
		em.merge(transientObject);
	}

	public List<Menu> findAll() {
		TypedQuery<Menu> query = em
				.createNamedQuery("Menu.findAll", Menu.class);
		List<Menu> results = query.getResultList();
		return results;
	}

	public List<Menu> getRange(Integer firstRow, Integer numberOfRows,
			boolean active, String sortOrder, String column) {
		// TypedQuery<Menu> query = em.createNamedQuery("Menu.findAllActive",
		// Menu.class);
		// String columnName = null;
		// String sortOrderString;
		// String queryString;
		// int activeInt = 0;
		// if(active) {
		// activeInt = 1;
		// }
		// if("desc".compareToIgnoreCase(sortOrder) != 0) {
		// sortOrder = "asc";
		// }
		// if("none")
		// TypedQuery<Menu> query =
		// em.createQuery("SELECT m FROM Menu m WHERE m.active = :active",
		// Menu.class);
		// query.setParameter("active", active);
		// query.setFirstResult(firstRow);
		// query.setMaxResults(numberOfRows);
		TypedQuery<Menu> query;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Menu> q = cb.createQuery(Menu.class);
		Root<Menu> m = q.from(Menu.class);
		Predicate condition = cb.equal(m.get("active"), active);
		q.select(m).where(condition);
		if ("desc".compareToIgnoreCase(sortOrder) != 0) {
			q.orderBy(cb.desc(m.get(column)));
		} else {
			q.orderBy(cb.asc(m.get(column)));
		}
		query = em.createQuery(q);
		query.setFirstResult(firstRow);
		query.setMaxResults(numberOfRows);
		List<Menu> results = query.getResultList();
		return results;
	}

	public int getRowCount() {
		Query query = em
				.createNativeQuery("select Count(*) from menuposition where active = 1");
		// query.setParameter("active", active);
		Integer result = ((BigInteger) query.getSingleResult()).intValue();
		return result;
	}

}
