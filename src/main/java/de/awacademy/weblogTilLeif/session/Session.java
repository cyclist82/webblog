package de.awacademy.weblogTilLeif.session;

import de.awacademy.weblogTilLeif.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
public class Session {

	@Id
	private String id;

	@ManyToOne
	private User user;

	public Session(){

	}

	public Session(User user) {
		this.id= UUID.randomUUID().toString();
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}
}
