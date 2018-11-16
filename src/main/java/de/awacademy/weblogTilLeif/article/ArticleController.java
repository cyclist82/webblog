package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.comment.Comment;
import de.awacademy.weblogTilLeif.comment.CommentRepository;
import de.awacademy.weblogTilLeif.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ArticleController {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;

	public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
	}

	@GetMapping("/article")
	public String create(Model model) {
		model.addAttribute("article", new ArticleDTO());
		return "createArticle";
	}

	@PostMapping("/article")
	public String create(@ModelAttribute("article") @Valid ArticleDTO articleDTO, BindingResult bindingResult, @ModelAttribute("currentUser") User currentUser) {
		if (currentUser == null || (!currentUser.isAdmin())) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {
			bindingResult.addError(new FieldError("article", "text", "Fehler bei der Eingabe"));
			return "createArticle";
		}
		Article article = new Article(articleDTO.getTitle(), articleDTO.getText(), currentUser);
		articleRepository.save(article);
		return "redirect:/";
	}

	@PostMapping("/{articleId}/delete")
	private String deleteArticle(@PathVariable("articleId") String articleId, @ModelAttribute("currentUser") User currentUser) {
		Article article = articleRepository.findById(articleId).get();
		if (currentUser.isAdmin()) {
			for (Comment comment : article.getComments()) {
				commentRepository.delete(comment);
			}
			articleRepository.delete(article);
		}
		return "redirect:/";
	}
}
