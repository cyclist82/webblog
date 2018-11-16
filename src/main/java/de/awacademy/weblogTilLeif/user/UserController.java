package de.awacademy.weblogTilLeif.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/users")
	private String findUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users/users";
	}

}
