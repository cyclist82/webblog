package de.awacademy.weblogTilLeif.session;

import de.awacademy.weblogTilLeif.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Session {

	@Id
	private String id;

	@ManyToOne
	@JoinColumn
	private User user;

	public Session() {

	}

	public Session(User user) {
		this.user = user;
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public User getUser() {
		return user;
	}


}
