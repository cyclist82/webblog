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
	private boolean active;



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
