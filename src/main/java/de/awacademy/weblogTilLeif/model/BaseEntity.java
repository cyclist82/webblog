package de.awacademy.weblogTilLeif.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	public BaseEntity() {
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}
}
