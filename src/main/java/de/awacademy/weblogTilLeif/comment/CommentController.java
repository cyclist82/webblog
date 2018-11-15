package de.awacademy.weblogTilLeif.comment;

import de.awacademy.weblogTilLeif.article.Article;
import de.awacademy.weblogTilLeif.article.ArticleRepository;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/article/{articleId}")
public class CommentController {

	private final ArticleRepository articleRepository;
	private final UserRepository userRepository;
	private final CommentRepository commentRepository;

	public CommentController(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository) {
		this.articleRepository = articleRepository;
		this.userRepository = userRepository;
		this.commentRepository= commentRepository;
	}

	@GetMapping("/comment/new")
	public String commentCreationForm(Article article, Model model) {
		Comment comment = new Comment();
		article.addComment(comment);
		model.addAttribute("comment", comment);
		return "/comment/createOrUpdateComment";
	}

	@PostMapping("/comment/new")
	public String commentCreationForm(@ModelAttribute("currentArticle") Article article, @Valid CommentDTO commentDTO, BindingResult result, Model model, @ModelAttribute("currentUser") User currentUser) {
//		if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
//			result.rejectValue("name", "duplicate", "already exists");
//		}
		Comment comment=new Comment(commentDTO.getText(), currentUser, article);
		article.addComment(comment);
		if (result.hasErrors()) {
			model.addAttribute("comment", commentDTO);
			return "redirect:/";
		} else {
			this.commentRepository.save(comment);
			return "redirect:/";
		}
	}
}
