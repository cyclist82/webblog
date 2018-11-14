package de.awacademy.weblogTilLeif.beitrag;

import de.awacademy.weblogTilLeif.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	@Lob
	private String text;

	@ManyToOne
	@JoinColumn
	private User user;

	private Instant creationDateTime;

	public Article() {
	}

	public Article(String title, String text) {
		this.title = title;
		this.text = text;
		this.creationDateTime = Instant.now();
	}

	public Article(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getText() {
		return text;
	}
}
