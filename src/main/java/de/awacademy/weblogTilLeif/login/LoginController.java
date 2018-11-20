package de.awacademy.weblogTilLeif.login;

import de.awacademy.weblogTilLeif.session.Session;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class LoginController {

	private UserRepository userRepository;
	private SessionRepository sessionRepository;

	public LoginController(UserRepository userRepository, SessionRepository sessionRepository) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
	}

	@GetMapping(value = "/login")
	public String login() {
		return "redirect:/";
	}

	//	Necessary for Impressum to work inside the layout
	@GetMapping("/impressum")
	public String impressum(Model model) {
		model.addAttribute("login", new LoginDTO());
		return "content/impressum";
	}

	@PostMapping("/login")
	public String loginSubmit(Model model, @ModelAttribute("login") LoginDTO loginDTO, HttpServletResponse response) {
		Optional<User> optionalUser = userRepository.findFirstByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		if (optionalUser.isPresent()) {
			Session session = new Session(optionalUser.get());
			sessionRepository.save(session);
			response.addCookie(new Cookie("sessionId", session.getId()));
			return "redirect:/";
		}
		model.addAttribute("login", loginDTO);
		return "redirect:/";
	}

	@PostMapping("/logout")
	public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
		sessionRepository.deleteById(sessionId);
		Cookie c = new Cookie("sessionId", "");
		c.setMaxAge(0);
		response.addCookie(c);
		return "redirect:/";
	}
}
