package de.awacademy.weblogTilLeif.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	private String findUsers(Model model, @ModelAttribute("currentUser") User currentUser) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		model.addAttribute("users", userRepository.findAll());
		return "content/users";
	}

	@PostMapping("/{id}/toAdmin")
	private String makeAdmin(@PathVariable("id") String userId, @ModelAttribute("currentUser") User currentUser, Model model) {
		if (!currentUser.isAdmin()) {
			return "redirect:/";
		}
		model.addAttribute("users", userRepository.findAll());
		User user = userRepository.findById(userId).get();
		user.setAdmin(true);
		this.userRepository.save(user);
		return "content/users";
	}

}
