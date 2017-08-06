package robert.oauth2.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import robert.oauth2.web.svc.api.UserDetailsProvider;

@RestController
public class TestController {

	private final UserDetailsProvider userDetailsProvider;

	public TestController(UserDetailsProvider userDetailsProvider) {
		this.userDetailsProvider = userDetailsProvider;
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello " + userDetailsProvider.getUserEmail() + " your id is: " + userDetailsProvider.getUserId();
	}
}
