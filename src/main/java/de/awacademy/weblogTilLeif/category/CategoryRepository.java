package de.awacademy.weblogTilLeif.category;

import de.awacademy.weblogTilLeif.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, String> {
	boolean existsByName(String name);

	Category findByName(String name);

	Set<Category> findByActive(boolean active);

	Set<Category> findAllByOrderByNameAsc();

	Optional<Category> findById(String id);

	Set<Category> findByActiveIs(boolean active);
}
