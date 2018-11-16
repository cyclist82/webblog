package de.awacademy.weblogTilLeif.role;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "Role")
public class Role {

    @Id
    private String id;

    private String role;

    public String getId() {
        return id;
    }

    public Role(String role) {
        this.role = role;
        this.id = UUID.randomUUID().toString();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
