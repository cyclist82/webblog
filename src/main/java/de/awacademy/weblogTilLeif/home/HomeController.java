package de.awacademy.weblogTilLeif.home;

import de.awacademy.weblogTilLeif.beitrag.ArticleRepository;
import de.awacademy.weblogTilLeif.session.Session;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {

	private ArticleRepository articleRepository;
	private SessionRepository sessionRepository;

	public HomeController(ArticleRepository articleRepository, SessionRepository sessionRepository) {
		this.articleRepository = articleRepository;
		this.sessionRepository = sessionRepository;
	}


	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("articles", articleRepository.findAllByOrderByCreationDateTimeDesc());
		return "index";
	}
}
