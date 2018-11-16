package de.awacademy.weblogTilLeif.session;

import de.awacademy.weblogTilLeif.login.LoginDTO;
import de.awacademy.weblogTilLeif.user.User;
import de.awacademy.weblogTilLeif.user.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import java.util.Optional;

@ControllerAdvice
public class SessionControllerAdvice {

	private SessionRepository sessionRepository;

	public SessionControllerAdvice(UserRepository userRepository, SessionRepository sessionRepository) {
		this.sessionRepository = sessionRepository;
	}

	// runs on every request, no whatter which it is
	// This way it is possible to use it everywhere in the template
	@ModelAttribute("currentUser")
	public User currentUser(@CookieValue(value = "sessionId", defaultValue = "") String sessionId) {
		Optional<Session> session = sessionRepository.findById(sessionId);
		if (session.isPresent()) {
			return session.get().getUser();
		}
		return null;
	}

	@ModelAttribute("login")
	public LoginDTO loginDTO(){
		return new LoginDTO();
	}


}
