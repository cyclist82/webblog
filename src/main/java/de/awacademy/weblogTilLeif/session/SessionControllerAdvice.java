package de.awacademy.weblogTilLeif.session;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.category.Category;
import de.awacademy.weblogTilLeif.category.CategoryRepository;
import de.awacademy.weblogTilLeif.comment.CommentDTO;
import de.awacademy.weblogTilLeif.login.LoginDTO;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@ControllerAdvice
public class SessionControllerAdvice {

	private SessionRepository sessionRepository;
	private UserRepository userRepository;
	private ArticleRepository articleRepository;
	private CategoryRepository categoryRepository;

	public SessionControllerAdvice(CategoryRepository categoryRepository, UserRepository userRepository, SessionRepository sessionRepository, ArticleRepository articleRepository) {
		this.sessionRepository = sessionRepository;
		this.userRepository = userRepository;
		this.articleRepository = articleRepository;
		this.categoryRepository = categoryRepository;
	}

	// runs on every request, no whatter which it is
	// This way it is possible to use it everywhere in the template
	@ModelAttribute("currentUser")
	public User currentUser(@CookieValue(value = "sessionId", defaultValue = "") String sessionId) {
		Optional<Session> session = sessionRepository.findById(sessionId);
		if (session.isPresent()) {
			return session.get().getUser();
		}
		return null;
	}

	@ModelAttribute("login")
	public LoginDTO loginDTO() {
		return new LoginDTO();
	}

	@ModelAttribute("newcomment")
	public CommentDTO commentDTO() {
		return new CommentDTO();
	}

	@ModelAttribute("articles")
	public List<Article> articles() {
		return articleRepository.findAllByOrderByCreationDateTimeDesc();
	}

	@ModelAttribute("activeCategories")
	public Set<Category> activeCategories() {
		return categoryRepository.findByActiveIs(true);
	}
}
