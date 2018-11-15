package de.awacademy.weblogTilLeif.article;


import javax.validation.constraints.Size;

public class ArticleDTO {

	@Size(min = 2, message = "Bitte etwas länger")
	private String title;
	@Size(min = 2, message = "Bitte etwas länger")
	private String text;

	public ArticleDTO() {
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
}
