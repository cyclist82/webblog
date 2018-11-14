package de.awacademy.weblogTilLeif.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private long id;

	@Column(unique = true)
	private String username;
	private String password;

	public User() {
	}


	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}
}
