package de.awacademy.weblogTilLeif.comment;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/{articleId}")
public class CommentController {

	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	public CommentController(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.commentRepository = commentRepository;
	}

	@GetMapping("/comment/new")
	public String createComment(Model model) {
		model.addAttribute("comment", new CommentDTO());
		return "createComment";
	}

	@ModelAttribute("article")
	public Article findArticle(@PathVariable("articleId") String articleId) {
		Optional<Article> article = articleRepository.findById(articleId);
		if (article.isPresent()) {
			return article.get();
		}
		return null;
	}

	@PostMapping("/comment/new")
	public String createComment(@ModelAttribute("article") Article article, @Valid CommentDTO commentDTO, BindingResult bindingResult, Model model, @ModelAttribute("currentUser") User currentUser) {
//		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
//			result.rejectValue("name", "duplicate", "already exists");
//		}
		if (currentUser == null) {
			return "redirect:/";
		}
		Comment comment = new Comment(commentDTO.getText(), currentUser, article);
		article.addComment(comment);
		if (bindingResult.hasErrors()) {
			model.addAttribute("comment", commentDTO);
			bindingResult.addError(new FieldError("comment", "text", "Fehler bei der Eingabe"));
			return "createComment";
		} else {
			this.commentRepository.save(comment);
			return "redirect:/";
		}
	}
}
