package de.awacademy.weblogTilLeif.articleOLD;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.user.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class ArticleOLD {

	@Id
	private String id;

	private String title;
	@Lob
	private String text;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private User creatorUser;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Article parentArticle;

	private LocalDateTime savedDateTime;

	public ArticleOLD() {
		this.id = UUID.randomUUID().toString();
		this.savedDateTime = LocalDateTime.now();
	}

	public ArticleOLD(String title, String text, User user, User creatorUser, Article parentArticle) {
		this.id = UUID.randomUUID().toString();
		this.title = title;
		this.text = text;
		this.user = user;
		this.creatorUser = creatorUser;
		this.parentArticle = parentArticle;
		this.savedDateTime = LocalDateTime.now();
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

	public User getCreatorUser() {
		return creatorUser;
	}

	public LocalDateTime getSavedDateTime() {
		return savedDateTime;
	}
}
