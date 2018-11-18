package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.category.Category;
import de.awacademy.weblogTilLeif.comment.Comment;
import de.awacademy.weblogTilLeif.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article")
	@OrderBy(value = "createdDateTime ASC")
	private List<Comment> comments = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@OrderBy(value = "name DESC")
	private Set<Category> categories = new HashSet<>();


	private LocalDateTime creationDateTime;

	public Article() {
	}

	public Article(String title, String text, User user) {
		this.title = title;
		this.text = text;
		this.user = user;
		this.creationDateTime = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
		this.categories = categories;
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


	public LocalDateTime getFormattedCreationDateTime() {
		return creationDateTime;
	}

	public LocalDateTime getCreationDateTime() {
		return creationDateTime;
	}

	public String getId() {
		return id;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}

	public List<Comment> getComments() {
		return comments;
	}

	public Set<Category> getCategories() {
		return categories;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
