package com.jericdy.sample.controller;

import com.jericdy.sample.dao.PersonDao;
import com.jericdy.sample.orm.Person;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@RestController
public class PersonController {

	@Autowired
	private PersonDao personDao;

	private final Random random;

	public PersonController() {
		random = new Random();
	}

	@Transactional
	@RequestMapping(value = "/person", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Person addPerson() {
		Person p = new Person(null, "john_smith_" + random.nextInt());
		personDao.save(p);
		return p;
	}

	@Transactional
	@RequestMapping(value = "/person", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Person> listPerson() {
		return personDao.getAll();
	}

}
