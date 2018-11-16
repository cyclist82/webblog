package de.awacademy.weblogTilLeif.signup;

import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignupController {

	private UserRepository userRepository;

	public SignupController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("signup")
	public String signup(Model model) {
		model.addAttribute("signup", new SignupDTO());
		return "signup";
	}

	@PostMapping("signup")
	public String signup(@ModelAttribute("signup") @Valid SignupDTO signupDTO, BindingResult bindingResult) {
		// Validation for existing User
		if (userRepository.existsByUsername(signupDTO.getUsername())) {
			bindingResult.addError(new FieldError("signup", "username", "User bereits vorhanden. Bitte einloggen"));
			return "redirect:/login";
		}
		// Validation for identical passwords
		if (!signupDTO.getPassword1().equals(signupDTO.getPassword2())) {
			bindingResult.addError(new FieldError("signup", "password2", "Passwörter stimmen nicht überein"));
		}
		// Validaiton for all errors in Form
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		// Happy Path. New User is created
		userRepository.save(new User(signupDTO.getUsername(), signupDTO.getPassword2()));
		return "redirect:/";
	}
}
