package de.awacademy.weblogTilLeif.user;


import de.awacademy.weblogTilLeif.image.Image;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {

	@Id
	private String id;

	@Column(unique = true)
	private String username;
	private String password;
	private boolean isAdmin = false;

	@OneToOne(fetch = FetchType.EAGER)
	private Image backgroundImage;

	public User() {
	}


	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.id = UUID.randomUUID().toString();
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public String getId() {
		return id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public String getUsername() {
		return username;
	}

	public Image getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}
}


