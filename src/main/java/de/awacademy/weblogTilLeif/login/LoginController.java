package de.awacademy.weblogTilLeif.login;

import de.awacademy.weblogTilLeif.session.Session;
import de.awacademy.weblogTilLeif.session.SessionRepository;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


@Controller
public class LoginController {

	//	@Autowired
//	UserRepository userRepository;
	private UserRepository userRepository;
	private SessionRepository sessionRepository;

	public LoginController(UserRepository userRepository, SessionRepository sessionRepository) {
		this.userRepository = userRepository;
		this.sessionRepository = sessionRepository;
	}

	@GetMapping(value = "/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginDTO());
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute("login") LoginDTO loginDTO, Model model, HttpServletResponse response) {
		Optional<User> optionalUser = userRepository.findFirstByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());
		if (optionalUser.isPresent()) {
			Session session = new Session(optionalUser.get());
			sessionRepository.save(session);
			Cookie cookie = new Cookie("sessionId", session.getId());
			response.addCookie(cookie);
			return "redirect:/";
		}
		model.addAttribute("login", loginDTO);
		return "login";
	}
}
