package com.jericdy.sample.orm;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jeric Bryle Sy Dy <jeric@jericbryledy.com>
 */
@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
	@NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id"),
	@NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p WHERE p.name = :name")})
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Long id;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 45)
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	public Person() {
	}

	public Person(Long id) {
		this.id = id;
	}

	public Person(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(object instanceof Person)) {
			return false;
		}
		Person other = (Person) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.jericdy.sample.orm.Person[ id=" + id + " ]";
	}

}
