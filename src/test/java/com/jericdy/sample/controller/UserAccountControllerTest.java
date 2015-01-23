package com.jericdy.sample.controller;

import com.jericdy.sample.Application;
import com.jericdy.sample.dto.AddUserAccountRequest;
import com.jericdy.sample.dto.AddUserAccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@ContextConfiguration(classes = Application.class)
public class UserAccountControllerTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private UserAccountController controller;

	@Test(dataProvider = "testAddUserData")
	public void testAddUser(String username, String password, String firstName, String lastName) {
		AddUserAccountRequest request = new AddUserAccountRequest();
		request.setUsername(username);
		request.setPassword(password);
		request.setFirstName(firstName);
		request.setLastName(lastName);

		AddUserAccountResponse response = controller.addUser(request);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getUsername(), username);
		Assert.assertEquals(response.getFirstName(), firstName);
		Assert.assertEquals(response.getLastName(), lastName);
		Assert.assertNotNull(response.getCreatedTimestamp());
	}

	@DataProvider
	public Object[][] testAddUserData() {
		return new Object[][]{
			{"brucewayne", "nanana", "Bruce", "Wayne"},
			{"johnsmith", "123456", "John", "Smith"}
		};
	}

}
