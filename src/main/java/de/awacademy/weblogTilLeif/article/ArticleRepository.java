package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

	List<Article> findAllByOrderByCreationDateTimeDesc();

	List<Article> findByCategoriesContainsOrderByCreationDateTimeAsc(Category category);

}
