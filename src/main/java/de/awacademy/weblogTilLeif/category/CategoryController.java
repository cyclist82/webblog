package de.awacademy.weblogTilLeif.category;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.comment.CommentDTO;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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
	public String createCategoryInit(Model model, @ModelAttribute("currentUser") User currentUser) {
		if (!(currentUser == null || currentUser.isAdmin())) {
			return "redirect:/";
		}
		model.addAttribute("category", new CategoryDTO());
		model.addAttribute("categories", categoryRepository.findAllByOrderByNameAsc());
		return "category/createCategory";
	}

	@PostMapping("createCategory")
	public String createCategory(@ModelAttribute("category") @Valid CategoryDTO categoryDTO, BindingResult bindingResult, @ModelAttribute("currentUser") User currentUser) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		if (bindingResult.hasErrors()) {
			return "category/createCategory";
		}
		if (categoryRepository.existsByName(categoryDTO.getName())) {
			bindingResult.addError(new FieldError("category", "name", "Kategorie bereits vorhanden"));
			return "category/createCategory";
		}
		Category category = new Category(categoryDTO.getName());
		categoryRepository.save(category);
		return "redirect:/1/createCategory";
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
		category.getArticles().add(article);
		article.getCategories().add(category);
		categoryRepository.save(category);
		articleRepository.save(article);
		return "redirect:/" + article.getId() + "/edit";
	}

	//	This deletes one Category just from the associated article
	@PostMapping("/deleteCategory/{categoryId}")
	public String deleteCategory(@ModelAttribute("article") Article article, @ModelAttribute("currentUser") User currentUser, @PathVariable String categoryId, BindingResult bindingResult) {
		if (currentUser == null || (!currentUser.isAdmin())) {
			return "redirect:/";
		}
		Category category = categoryRepository.findById(categoryId).get();
		category.getArticles().remove(article);
		article.getCategories().remove(category);
		categoryRepository.save(category);
		articleRepository.save(article);
//		model.addAttribute("category", new CategoryDTO());
		return "redirect:/" + article.getId() + "/edit";
	}

	@GetMapping("/setActive/{categoryId}")
	public String setActiveCategory(@PathVariable("categoryId") String categoryId, @ModelAttribute("currentUser") User currentUser) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		Category category = categoryRepository.findById(categoryId).get();
		category.setActive(!category.isActive());
		categoryRepository.save(category);
		return "redirect:/1/createCategory";
	}

//	@GetMapping("/showAll/(categoryId)")
//	public String showCategoryUsage(@PathVariable("categoryId") String categoryId{
//		for (Category category : categoryRepository.findAllById(categoryId){
//
//		}
//	}

	//This DELETES the WHOLE CATEGORY
	@GetMapping("/delete/{categoryId}")
	public String deleteWholeCategory(@PathVariable("categoryId") String categoryId, @ModelAttribute("currentUser") User currentUser) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		Category category = categoryRepository.findById(categoryId).get();
		for (Article article : articleRepository.findByCategoriesContainsOrderByCreationDateTimeAsc(category)) {
			article.getCategories().remove(category);
		}
		categoryRepository.delete(category);
		return "redirect:/1/createCategory";
	}


//	@GetMapping("/edit/{categoryId}")
//	public String editCategory(Model model, @PathVariable("categoryId") String categoryId, @ModelAttribute("currentUser") User currentUser) {
//		if (!currentUser.isAdmin()) {
//			return "redirect:/";
//		}
//		model.addAttribute("allCategory", categoryRepository.findById(categoryId).get());
//		return "redirect:/1/createCategory";
//	}


}
