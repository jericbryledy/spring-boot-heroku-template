package com.jericdy.sample.dto;

import java.util.Date;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
public class AddUserAccountResponse {

	private String username;
	private String firstName;
	private String lastName;
	private Date createdTimestamp;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

}
