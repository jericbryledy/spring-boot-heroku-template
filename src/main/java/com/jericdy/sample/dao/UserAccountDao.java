package com.jericdy.sample.dao;

import com.jericdy.sample.orm.UserAccount;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Repository
public class UserAccountDao extends GenericDao<UserAccount, Long> {

	public UserAccountDao() {
		super(UserAccount.class);
	}

	public List<UserAccount> getAll() {
		return getNamedQuery("UserAccount.findAll").list();
	}

}
