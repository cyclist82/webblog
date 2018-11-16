package de.awacademy.weblogTilLeif.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

	private UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping
	private String findUsers(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "users/users";
	}

//	@ModelAttribute("user")
//	public User findOwner(@PathVariable("ownerId") int ownerId) {
//		return this.userRepository.findById(ownerId);
//	}

//	@GetMapping("/{id}/toAdmin")
//	public String makeAdmin(@PathVariable("id") String userId, Model model) {
//		User user = this.userRepository.findById(userId).get();
//		model.addAttribute(user);
//		return "/users/users";
//	}
////
//	@PostMapping("/owners/{ownerId}/edit")
//	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
//		if (result.hasErrors()) {
//			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
//		} else {
//			owner.setId(ownerId);
//			this.owners.save(owner);
//			return "redirect:/owners/{ownerId}";
//		}
//	}


	@PostMapping("/{id}/toAdmin")
	private String makeAdmin(@PathVariable("id") String userId) {
		User user=userRepository.findById(userId).get();
		user.setAdmin(true);
		this.userRepository.save(user);
		return "redirect:/";
	}

}
