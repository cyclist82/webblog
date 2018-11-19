package de.awacademy.weblogTilLeif.articleOLD;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleOLDRepository extends JpaRepository<ArticleOLD, String> {
	List<ArticleOLD> findByParentArticleIdOrderBySavedDateTimeDesc(String id);
}
