package de.awacademy.weblogTilLeif.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String login(Model model){
		model.addAttribute("login", new LoginDTO());
		return "login";
	}

	@PostMapping("/login")
	public String loginSubmit(@ModelAttribute("login") LoginDTO loginDTO, Model model){
		return "index";
	}
}
