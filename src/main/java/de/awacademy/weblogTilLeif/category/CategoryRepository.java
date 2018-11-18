package de.awacademy.weblogTilLeif.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
	boolean existsByName(String name);

	Category findByName(String name);

}
