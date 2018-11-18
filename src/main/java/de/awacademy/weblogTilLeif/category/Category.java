package de.awacademy.weblogTilLeif.category;

import de.awacademy.weblogTilLeif.article.Article;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Category {

	@Id
	private String id;
	private String name;
	private boolean active = true;

	@ManyToMany
	private Set<Article> articles = new HashSet<>();


	public Category() {
		this.id = UUID.randomUUID().toString();
	}

	public Category(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		active = active;
	}

	public String getId() {
		return id;
	}

	public Set<Article> getArticles() {
		return articles;
	}

	public void setArticles(Set<Article> articles) {
		this.articles = articles;
	}
}
