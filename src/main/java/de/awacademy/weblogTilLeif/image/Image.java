package de.awacademy.weblogTilLeif.image;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Entity
public class Image {

	@Id
	private String id;

	private String name;
	private String type;
	@Lob
	private byte[] picture;

	public Image() {
		this.id = UUID.randomUUID().toString();
	}

	public Image(String name, String type, byte[] picture) {
		this.name = name;
		this.type = type;
		this.picture = picture;
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
