package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.articleOLD.ArticleOLD;
import de.awacademy.weblogTilLeif.category.Category;
import de.awacademy.weblogTilLeif.comment.Comment;
import de.awacademy.weblogTilLeif.image.Image;
import de.awacademy.weblogTilLeif.user.User;
import org.apache.tomcat.util.codec.binary.Base64;

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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private User lastEditUser;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article")
	@OrderBy(value = "createdDateTime ASC")
	private List<Comment> comments = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER)
	@OrderBy(value = "name DESC")
	private Set<Category> categories = new HashSet<>();

	private LocalDateTime creationDateTime;
	private LocalDateTime lastEditedDateTime;
	@OneToOne
	private Image image;

	public Article() {
	}

	public Article(String title, String text, User user) {
		this.title = title;
		this.text = text;
		this.user = user;
		this.creationDateTime = LocalDateTime.now();
		this.id = UUID.randomUUID().toString();
	}

	public Article(String id, String title, String text) {
		this.id = id;
		this.title = title;
		this.text = text;
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

	public LocalDateTime getLastEditedDateTime() {
		return lastEditedDateTime;
	}

	public void setLastEditedDateTime(LocalDateTime lastEditedDateTime) {
		this.lastEditedDateTime = lastEditedDateTime;
	}

	public User getLastEditUser() {
		return lastEditUser;
	}

	public void setLastEditUser(User lastEditUser) {
		this.lastEditUser = lastEditUser;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Article article = (Article) o;

		if (!id.equals(article.id)) return false;
		if (!title.equals(article.title)) return false;
		return text.equals(article.text);
	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + title.hashCode();
		result = 31 * result + text.hashCode();
		return result;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
