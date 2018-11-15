package de.awacademy.weblogTilLeif.comment;

import de.awacademy.weblogTilLeif.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, String> {

	List<Comment> findAllByArticle(Article article);
}
