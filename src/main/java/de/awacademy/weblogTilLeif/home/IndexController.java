package de.awacademy.weblogTilLeif.home;

import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.comment.CommentDTO;
import de.awacademy.weblogTilLeif.login.LoginDTO;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

	private ArticleRepository articleRepository;
	private SessionRepository sessionRepository;

	public IndexController(ArticleRepository articleRepository, SessionRepository sessionRepository) {
		this.articleRepository = articleRepository;
		this.sessionRepository = sessionRepository;
	}


	// Website is loaded
	@GetMapping("/")
	public String homeGet(Model model) {
		model.addAttribute("articles", articleRepository.findAllByOrderByCreationDateTimeDesc());
		return "index";
	}
}
