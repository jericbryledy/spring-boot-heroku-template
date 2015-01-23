package com.jericdy.sample.controller;

import com.jericdy.sample.dao.UserAccountDao;
import com.jericdy.sample.dto.AddUserAccountRequest;
import com.jericdy.sample.dto.AddUserAccountResponse;
import com.jericdy.sample.orm.UserAccount;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@RestController
public class UserAccountController {

	@Autowired
	private UserAccountDao userAccountDao;

	@Transactional
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public AddUserAccountResponse addUser(@RequestBody AddUserAccountRequest request) {
		UserAccount user = new UserAccount();

		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setCreatedTimestamp(new Date());

		userAccountDao.save(user);

		AddUserAccountResponse response = new AddUserAccountResponse();

		response.setUsername(user.getUsername());
		response.setFirstName(user.getFirstName());
		response.setLastName(user.getLastName());
		response.setCreatedTimestamp(user.getCreatedTimestamp());

		return response;
	}

	@Transactional
	@RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserAccount> listUser() {
		return userAccountDao.getAll();
	}

}
