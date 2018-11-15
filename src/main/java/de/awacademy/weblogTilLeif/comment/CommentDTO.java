package de.awacademy.weblogTilLeif.comment;

import javax.validation.constraints.Size;

public class CommentDTO {

	@Size(min = 2, message = "Bitte etwas länger")
	private String text;

	public CommentDTO() {
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
