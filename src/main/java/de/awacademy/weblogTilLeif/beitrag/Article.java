package de.awacademy.weblogTilLeif.beitrag;

import de.awacademy.weblogTilLeif.user.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.UUID;

@Entity
public class Article {

	@Id
	private String id;

	private String title;
	@Lob
	private String text;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private User user;

	private LocalDateTime creationDateTime;

	public Article() {
	}

	public Article(String title, String text, User user) {
		this.title = title;
		this.text = text;
		this.user = user;
		this.creationDateTime = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
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

	public User getUser() {
		return user;
	}

	// Should this be Part of the Article...or Article Service???
	public String getCreationDateTime() {
		return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.LONG).withLocale(Locale.GERMANY).withZone(ZoneId.of("Europe/Berlin")).format(creationDateTime);
	}
}
