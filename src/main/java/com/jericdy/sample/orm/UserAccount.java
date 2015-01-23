package com.jericdy.sample.orm;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Entity
@Table(name = "user_account")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u"),
	@NamedQuery(name = "UserAccount.findById", query = "SELECT u FROM UserAccount u WHERE u.id = :id"),
	@NamedQuery(name = "UserAccount.findByUsername", query = "SELECT u FROM UserAccount u WHERE u.username = :username"),
	@NamedQuery(name = "UserAccount.findByPassword", query = "SELECT u FROM UserAccount u WHERE u.password = :password"),
	@NamedQuery(name = "UserAccount.findByFirstName", query = "SELECT u FROM UserAccount u WHERE u.firstName = :firstName"),
	@NamedQuery(name = "UserAccount.findByLastName", query = "SELECT u FROM UserAccount u WHERE u.lastName = :lastName"),
	@NamedQuery(name = "UserAccount.findByCreatedTimestamp", query = "SELECT u FROM UserAccount u WHERE u.createdTimestamp = :createdTimestamp")})
public class UserAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "username", nullable = false, unique = true, length = 45)
	private String username;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "password", nullable = false, length = 45)
	private String password;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "first_name", nullable = false, length = 45)
	private String firstName;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;
	@Basic(optional = false)
	@NotNull
	@Column(name = "created_timestamp", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

	public UserAccount() {
	}

	public UserAccount(Long id) {
		this.id = id;
	}

	public UserAccount(Long id, String username, String password, String firstName, String lastName, Date createdTimestamp) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdTimestamp = createdTimestamp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserAccount)) {
			return false;
		}
		UserAccount other = (UserAccount) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.jericdy.sample.orm.UserAccount[ id=" + id + " ]";
	}
}
