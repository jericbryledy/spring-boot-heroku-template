package com.jericdy.sample.dao;

import java.io.Serializable;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 * @param <T> ORM class
 * @param <P> Primary Key class
 */
public class GenericDao<T, P extends Serializable> {

	private final Class<T> clazz;

	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Autowired
	protected SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected Query getNamedQuery(String name) {
		return getSession().getNamedQuery(name);
	}

	public void save(T orm) {
		getSession().save(orm);
	}

	public T get(P id) {
		return (T) getSession().get(clazz, id);
	}

	public void update(T orm) {
		getSession().update(orm);
	}

	public void delete(T orm) {
		getSession().delete(orm);
	}
}
