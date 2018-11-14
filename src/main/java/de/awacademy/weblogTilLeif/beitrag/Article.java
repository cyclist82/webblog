package de.awacademy.weblogTilLeif.beitrag;

import javax.persistence.*;

@Entity
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String title;
	@Lob
	private String text;

	public Article() {
	}

	public Article(String title, String text) {
		this.title = title;
		this.text = text;
	}

	public Article(String title) {
		this.title = title;
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
