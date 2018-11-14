package de.awacademy.weblogTilLeif.home;

import de.awacademy.weblogTilLeif.beitrag.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private ArticleRepository articleRepository;

	public HomeController(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}


	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("articles", articleRepository.findAll());
		return "index";
	}
}
