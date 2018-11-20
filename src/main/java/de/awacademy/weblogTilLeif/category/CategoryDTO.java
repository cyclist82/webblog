package de.awacademy.weblogTilLeif.category;

import javax.validation.constraints.Size;

public class CategoryDTO {

	private String id;

	@Size(min = 2, message = "Bitte etwas länger")
	private String name;

	public CategoryDTO() {

	}

	public CategoryDTO(String id, @Size(min = 2, message = "Bitte etwas länger") String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
