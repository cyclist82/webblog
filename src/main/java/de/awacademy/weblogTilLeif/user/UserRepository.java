package de.awacademy.weblogTilLeif.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findFirstByUsernameAndPassword(String username, String password);

	boolean existsByUsername(String username);

	Optional<User> findById(String id);
}
