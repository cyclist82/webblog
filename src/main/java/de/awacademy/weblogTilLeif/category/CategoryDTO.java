package de.awacademy.weblogTilLeif.category;

import javax.validation.constraints.Size;

public class CategoryDTO {

	@Size(min = 2, message = "Bitte etwas länger")
	private String name;

	public CategoryDTO() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
