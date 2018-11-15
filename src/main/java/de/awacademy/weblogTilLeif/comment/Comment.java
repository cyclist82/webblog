package de.awacademy.weblogTilLeif.comment;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

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
		createdDateTime = LocalDateTime.now();
	}

	public Comment(String text, User user,Article article) {
		this.text = text;
		this.user = user;
		this.article=article;
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
}
