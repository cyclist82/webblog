package de.awacademy.weblogTilLeif.comment;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Comment {

	@Id
	private String id;
	@Lob
	private String text;
	private LocalDateTime createdDateTime;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private Article article;

	public Comment() {
		this.id = UUID.randomUUID().toString();
		createdDateTime = LocalDateTime.now();
	}

	public Comment(String text, User user, Article article) {
		this.text = text;
		this.user = user;
		this.article = article;
		this.id = UUID.randomUUID().toString();
		createdDateTime = LocalDateTime.now();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
}
