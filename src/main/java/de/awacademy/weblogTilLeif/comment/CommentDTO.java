package de.awacademy.weblogTilLeif.comment;

import javax.validation.constraints.Size;

public class CommentDTO {

	@Size(min = 1, message = "Bitte etwas länger")
	private String commenttext;

	public CommentDTO() {
	}

	public String getCommenttext() {
		return commenttext;
	}

	public void setCommenttext(String commenttext) {
		this.commenttext = commenttext;
	}
}
