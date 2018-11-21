package de.awacademy.weblogTilLeif.category;

import de.awacademy.weblogTilLeif.article.Article;

import javax.persistence.*;
import java.util.*;

@Entity
public class Category {

	@Id
	private String id;
	private String name;
	private boolean active;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
	private List<Article> articles;


	public Category() {
		this.id = UUID.randomUUID().toString();
		this.active = true;
	}

	public Category(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.active = true;
	}

	public String getName() {
		return name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Category category = (Category) o;

		return name.toUpperCase().equals(category.name.toUpperCase());
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
