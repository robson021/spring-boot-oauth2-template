package robert.oauth2.web.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import robert.oauth2.web.svc.api.UserDetailsProvider;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserDetailsProvider userDetailsProvider;

    public AdminController(UserDetailsProvider userDetailsProvider) {
        this.userDetailsProvider = userDetailsProvider;
    }

    @GetMapping("/private")
    public String adminResource() {
        return userDetailsProvider.getUserEmail() + ", you have accessed admin resource";
    }
}
