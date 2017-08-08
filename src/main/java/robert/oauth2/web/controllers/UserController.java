package robert.oauth2.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import robert.oauth2.web.svc.api.UserDetailsProvider;

@RestController
public class UserController {

	private final UserDetailsProvider userDetailsProvider;

	public UserController(UserDetailsProvider userDetailsProvider) {
		this.userDetailsProvider = userDetailsProvider;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello " + userDetailsProvider.getUserEmail() + " your id = " + userDetailsProvider.getUserId();
	}
}
