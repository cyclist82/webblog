package de.awacademy.weblogTilLeif.user;




import de.awacademy.weblogTilLeif.role.Role;

import javax.persistence.*;
import java.util.*;

@Entity
public class User {

	@OneToMany
	private List<Role> roles = new ArrayList<>();

	@Id
	private String id;

	@Column(unique = true)
	private String username;
	private String password;

	public User() {
	}

	public List<Role> getRoles() {
		return roles;
	}

	//	protected Set<Role> getRolesInternal() {
//		if (this.roles == null) {
//			this.roles = new HashSet<>();
//		}
//		return this.roles;
//	}

//	public List<Role> getPets() {
//		List<Role> sortedRoles = new ArrayList<>(getRolesInternal());
//		PropertyComparator.sort(sortedRoles,
//				new MutableSortDefinition("name", true, true));
//		return Collections.unmodifiableList(sortedRoles);
//	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
//		this.roles.add(new Role("user"));
		this.id = UUID.randomUUID().toString();
	}

	public String getUsername() {
		return username;
	}
}
