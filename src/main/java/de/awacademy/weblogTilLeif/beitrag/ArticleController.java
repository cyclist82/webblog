package de.awacademy.weblogTilLeif.beitrag;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

	private ArticleRepository articleRepository;

	public ArticleController(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@GetMapping("/article")
	public String create(Model model) {
		model.addAttribute("article", new ArticleDTO());
		return "createArticle";
	}

	@PostMapping("/article")
	public String create(@ModelAttribute("greeting") ArticleDTO articleDTO) {
		Article article = new Article(articleDTO.getTitle(), articleDTO.getText());
		articleRepository.save(article);
		return "redirect:/";
	}


}
