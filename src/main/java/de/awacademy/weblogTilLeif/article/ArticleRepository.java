package de.awacademy.weblogTilLeif.article;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, String> {

	List<Article> findAllByOrderByCreationDateTimeDesc();
}
