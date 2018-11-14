package de.awacademy.weblogTilLeif.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {

	@GeneratedValue
	private long id;

	private String username;
	private String password;

	public User() {
	}

	public String getUsername() {
		return username;
	}
}
