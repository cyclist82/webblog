package de.awacademy.weblogTilLeif.signup;

import de.awacademy.weblogTilLeif.image.Image;
import de.awacademy.weblogTilLeif.image.ImageRepository;
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
import java.io.IOException;

@Controller
public class SignupController {

	private UserRepository userRepository;
	private SessionRepository sessionRepository;
	private ImageRepository imageRepository;

	public SignupController(UserRepository userRepository, SessionRepository sessionRepository, ImageRepository imageRepository) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
		this.imageRepository = imageRepository;
	}

	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("signup", new SignupDTO());
		model.addAttribute("login", new LoginDTO());
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(@ModelAttribute("signup") @Valid SignupDTO signupDTO, BindingResult bindingResult, Model model, HttpServletResponse response) throws IOException {
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

		// Happy Path. New User is created
		User user = new User(signupDTO.getUsername(), signupDTO.getPassword2());
		// Der erste neue Benutzer wird automatisch Admin
		if (userRepository.count() == 0) {
			user.setAdmin(true);
		}
		System.out.println("geht nicht");
		if (!signupDTO.getFile1().isEmpty()) {
			Image image = new Image(signupDTO.getFile1().getName(), signupDTO.getFile1().getContentType(), signupDTO.getFile1().getBytes());
			imageRepository.save(image);
			user.setBackgroundImage(image);
		}
		//		 Validaiton for all errors in Form
		if (bindingResult.hasErrors()) {
			return "signup";
		}
		userRepository.save(user);
		Session session = new Session(user);
		sessionRepository.save(session);
		response.addCookie(new Cookie("sessionId", session.getId()));
		return "redirect:/";
	}
}
