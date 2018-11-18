package de.awacademy.weblogTilLeif.category;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Category {

	@Id
	private String id;
	private String name;



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
}
