package de.awacademy.weblogTilLeif.signup;

import de.awacademy.weblogTilLeif.login.LoginController;
import de.awacademy.weblogTilLeif.login.LoginDTO;
import de.awacademy.weblogTilLeif.session.Session;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class SignupController {

	private UserRepository userRepository;
	private SessionRepository sessionRepository;

	public SignupController(UserRepository userRepository, SessionRepository sessionRepository) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signup", new SignupDTO());
		model.addAttribute("login", new LoginDTO());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("signup") @Valid SignupDTO signupDTO, BindingResult bindingResult, Model model, HttpServletResponse response) {
		// Validation for existing User
		model.addAttribute("login", new LoginDTO());
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
		User user = new User(signupDTO.getUsername(), signupDTO.getPassword2());
		userRepository.save(user);
		Session session = new Session(user);
		sessionRepository.save(session);
		response.addCookie(new Cookie("sessionId", session.getId()));
		return "redirect:/";
	}
}
