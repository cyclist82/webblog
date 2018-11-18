package de.awacademy.weblogTilLeif.category;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.comment.CommentDTO;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/{articleId}")
public class CategoryController {

	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;

	public CategoryController(ArticleRepository articleRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
	}

	@ModelAttribute("article")
	public Article findArticle(@PathVariable("articleId") String articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			return article.get();
		}
		return null;
	}

	@GetMapping("createCategory")
	public String createCategoryInit(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "category/createCategory";
	}

	@PostMapping("createCategory")
	public String createCategory(@ModelAttribute("category") @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return "category/createCategory";
		}
		Category category=new Category(categoryDTO.getName());
		categoryRepository.save(category);
		return "redirect:/";
	}

	//	@GetMapping("/addCategory/{categoryId}")
//	public String addCategory(@ModelAttribute("article") Article article, Model model, @ModelAttribute("currentUser") User currentUser, @PathVariable String categoryId) {
//		if (currentUser == null || (!currentUser.isAdmin())) {
//			return "redirect:/";
//		}
//		Optional<Category> category = categoryRepository.findById(categoryId);
//		article.getCategories().add(category.get());
//		articleRepository.save(article);
//		return "articles/editarticle";
//	}
//	@ModelAttribute("categories")
//	public Set<Category> findArticle() {
//		return categoryRepository.findByActive(true);
//	}
//	@PostMapping("createCategory")
//	public String createCategory(@ModelAttribute("article") Article article, @ModelAttribute("category") CategoryDTO categoryDTO, @ModelAttribute("currentUser") User currentUser, BindingResult bindingResult) {
//		if (currentUser == null || (!currentUser.isAdmin())) {
//			return "redirect:/";
//		}
//		Category category = new Category(categoryDTO.getName());
//		article.getCategories().add(category);
//		articleRepository.save(article);
//		return "redirect:/" + article.getId() + "/edit";
//	}

	@PostMapping("/addCategory/{categoryId}/add")
	public String addCategory(@ModelAttribute("article") Article article, @ModelAttribute("currentUser") User currentUser, @PathVariable String categoryId, BindingResult bindingResult) {
		if (currentUser == null || (!currentUser.isAdmin())) {
			return "redirect:/";
		}
		Category category = categoryRepository.findById(categoryId).get();
		article.getCategories().add(category);
		articleRepository.save(article);
		return "redirect:/" + article.getId() + "/edit";
	}

	@PostMapping("/deleteCategory/{categoryId}")
	public String deleteCategory(@ModelAttribute("article") Article article, @ModelAttribute("currentUser") User currentUser, @PathVariable String categoryId, BindingResult bindingResult) {
		if (currentUser == null || (!currentUser.isAdmin())) {
			return "redirect:/";
		}
		Category category = categoryRepository.findById(categoryId).get();
		article.getCategories().remove(category);
		articleRepository.save(article);
//		model.addAttribute("category", new CategoryDTO());
		return "redirect:/" + article.getId() + "/edit";
	}
}
