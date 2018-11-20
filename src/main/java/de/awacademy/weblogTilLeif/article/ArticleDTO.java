package de.awacademy.weblogTilLeif.article;


import de.awacademy.weblogTilLeif.category.Category;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class ArticleDTO {

	private String id = null;
	@Size(min = 2, message = "Bitte etwas länger")
	private String title;
	@Size(min = 2, message = "Bitte etwas länger")
	private String text;

	private Set<Category> categories = new HashSet<>();
	private MultipartFile file;

	public ArticleDTO() {
	}

	public ArticleDTO(@Size(min = 2, message = "Bitte etwas länger") String title, @Size(min = 2, message = "Bitte etwas länger") String text, Set<Category> categories) {
		this.title = title;
		this.text = text;
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}
