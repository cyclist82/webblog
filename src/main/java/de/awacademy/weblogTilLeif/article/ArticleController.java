package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.category.Category;
import de.awacademy.weblogTilLeif.category.CategoryDTO;
import de.awacademy.weblogTilLeif.category.CategoryRepository;
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
import java.util.Set;

@Controller
public class ArticleController {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private CategoryRepository categoryRepository;

	public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository, CategoryRepository categoryRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
		this.categoryRepository = categoryRepository;
	}

	@GetMapping("/article")
	public String create(Model model, @ModelAttribute("article") ArticleDTO articleDTO) {
		if (!model.containsAttribute("article")) {
			model.addAttribute("article", new ArticleDTO());
		} else {
			model.addAttribute("article", articleDTO);
		}
		model.addAttribute("category", new CategoryDTO());
		return "articles/createArticle";
	}

	// Mapping for Button create new article
	@PostMapping(value = "/article")
	public String create(@ModelAttribute("article") @Valid ArticleDTO articleDTO, BindingResult bindingResult, @ModelAttribute("currentUser") User currentUser) {
		if (currentUser == null || (!currentUser.isAdmin())) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {
			bindingResult.addError(new FieldError("article", "text", "Fehler bei der Eingabe"));
			return "articles/createArticle";
		}
		Article article = new Article(articleDTO.getTitle(), articleDTO.getText(), currentUser);
		articleRepository.save(article);
		return "redirect:/";
	}

	@PostMapping("/{articleId}/delete")
	private String deleteArticle(@PathVariable("articleId") String articleId, @ModelAttribute("currentUser") User currentUser) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		Article article = articleRepository.findById(articleId).get();
		for (Comment comment : article.getComments()) {
			commentRepository.delete(comment);
		}
		articleRepository.delete(article);

		return "redirect:/";
	}

	@GetMapping("/{articleId}/edit")
	public String initEditArticle(@PathVariable("articleId") String articleId, Model model) {
		Article article = this.articleRepository.findById(articleId).get();
		ArticleDTO articleDTO = new ArticleDTO(article.getTitle(), article.getText(), article.getCategories());
		articleDTO.setId(articleId);
		model.addAttribute("article", articleDTO);
		model.addAttribute("category", new CategoryDTO());
		Set<Category> categories = categoryRepository.findByActive(true);
		for (Category category : article.getCategories()) {
			categories.remove(category);
		}
		model.addAttribute("categories", categories);
		return "articles/editarticle";
	}

//	@GetMapping("/{articleId}/edit")
//	public ModelAndView initEditArticle(@PathVariable("articleId") String articleId) {
//		ModelAndView mav=new ModelAndView("articles/editarticle");
//		Article article = this.articleRepository.findById(articleId).get();
//		ArticleDTO articleDTO = new ArticleDTO(article.getTitle(), article.getText(), article.getCategories());
//		articleDTO.setId(articleId);
//		mav.addObject("article", articleDTO);
//		mav.addObject("category", new CategoryDTO());
//		return mav;
//	}


	@PostMapping("/{articleId}/edit")
	private String editArticle(@PathVariable("articleId") String articleId, @ModelAttribute("currentUser") User currentUser, BindingResult bindingResult, @ModelAttribute("article") ArticleDTO articleDTO, @ModelAttribute("category") CategoryDTO categoryDTO) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		Article article = articleRepository.findById(articleId).get();
		article.setTitle(articleDTO.getTitle());
		article.setText(articleDTO.getText());
		articleRepository.save(article);
		return "redirect:/";
	}

}

