package com.jericdy.sample.dao;

import com.jericdy.sample.orm.Person;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Repository
public class PersonDao extends GenericDao<Person, Long> {

	public PersonDao() {
		super(Person.class);
	}

	public List<Person> getAll() {
		return getNamedQuery("Person.findAll").list();
	}

}
