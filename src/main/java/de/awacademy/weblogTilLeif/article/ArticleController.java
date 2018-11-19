package de.awacademy.weblogTilLeif.article;

import de.awacademy.weblogTilLeif.articleOLD.ArticleOLD;
import de.awacademy.weblogTilLeif.articleOLD.ArticleOLDRepository;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Controller
public class ArticleController {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private CategoryRepository categoryRepository;
	private ArticleOLDRepository articleOLDRepository;

	public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository, CategoryRepository categoryRepository, ArticleOLDRepository articleOLDRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
		this.categoryRepository = categoryRepository;
		this.articleOLDRepository = articleOLDRepository;
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
			return "articles/createArticle";
		}
		Article article = new Article(articleDTO.getTitle(), articleDTO.getText(), currentUser);
		Article article1 = articleRepository.save(article);
		return "redirect:/" + article.getId() + "/edit";
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
		for (ArticleOLD articleOLD : articleOLDRepository.findByParentArticleIdOrderBySavedDateTimeDesc(articleId)) {
			articleOLDRepository.delete(articleOLD);
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
		Set<Category> categories = categoryRepository.findByActive(true);
		for (Category category : article.getCategories()) {
			categories.remove(category);
		}
		model.addAttribute("categories", categories);
		model.addAttribute("category", new CategoryDTO());
		return "articles/editarticle";
	}

	@PostMapping("/{articleId}/edit")
	private String editArticle(@ModelAttribute("article") @Valid ArticleDTO articleDTO, BindingResult bindingResult, @PathVariable("articleId") String articleId, @ModelAttribute("currentUser") User currentUser) {
		if (bindingResult.hasErrors()) {
			return "articles/editarticle";
		}
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		Article article = articleRepository.findById(articleId).get();
		String title = article.getTitle();
		String text = article.getText();
		article.setTitle(articleDTO.getTitle());
		article.setText(articleDTO.getText());
		Article articleOld = new Article(articleRepository.findById(articleId).get().getId(), title, text);
		if (article.equals(articleOld)) {
			return "redirect:/";
		}
		articleOLDRepository.save(new ArticleOLD(articleOld.getTitle(), articleOld.getText(), currentUser, article.getUser(), article));
		article.setLastEditedDateTime(LocalDateTime.now());
		article.setLastEditUser(currentUser);
		articleRepository.save(article);
		return "redirect:/";
	}

	@GetMapping("/{articleId}/showHistory")
	public String showHistory(@PathVariable("articleId") String articleId, Model model) {
		Article article = this.articleRepository.findById(articleId).get();
		List<ArticleOLD> oldArticles = this.articleOLDRepository.findByParentArticleIdOrderBySavedDateTimeDesc(articleId);
		model.addAttribute("article", article);
		model.addAttribute("oldArticles", oldArticles);
		return "articles/showhistory";
	}

}

