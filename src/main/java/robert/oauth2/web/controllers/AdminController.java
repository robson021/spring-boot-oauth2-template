package robert.oauth2.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import robert.oauth2.web.svc.api.UserDetailsProvider;

@RestController
@RequestMapping("/admin")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    private final UserDetailsProvider userDetailsProvider;

    public AdminController(UserDetailsProvider userDetailsProvider) {
        this.userDetailsProvider = userDetailsProvider;
    }

    @GetMapping("/private")
    public String adminResource() {
	    log.debug("Accessing private resource");
	    return userDetailsProvider.getUserEmail() + ", you have accessed admin resource";
    }
}
