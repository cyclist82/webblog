package de.awacademy.weblogTilLeif.role;

import de.awacademy.weblogTilLeif.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositoy extends JpaRepository <User, String> {

    Optional<Role> findFirstByRole(String role);

}
