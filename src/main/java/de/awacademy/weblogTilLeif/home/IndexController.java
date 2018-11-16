package de.awacademy.weblogTilLeif.home;

import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.login.LoginDTO;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
	public String home(Model model) {
		model.addAttribute("articles", articleRepository.findAllByOrderByCreationDateTimeDesc());
		model.addAttribute("login", new LoginDTO());
		return "index";
	}
}
