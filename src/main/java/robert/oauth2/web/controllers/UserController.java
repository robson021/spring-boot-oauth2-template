package robert.oauth2.web.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import robert.oauth2.db.entities.User;
import robert.oauth2.db.svc.api.DbService;
import robert.oauth2.web.svc.api.UserDetailsProvider;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserDetailsProvider userDetailsProvider;

    private final DbService dbService;

    public UserController(UserDetailsProvider userDetailsProvider, DbService dbService) {
        this.userDetailsProvider = userDetailsProvider;
        this.dbService = dbService;
    }

    @GetMapping("/hello")
    public String sayHello() {
        log.debug("Hello request");
        return "Hello " + userDetailsProvider.getUserEmail() + " your id = " + userDetailsProvider.getUserId();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void registerNewUser(@RequestBody User user) {
        log.debug("Register new user request");
        dbService.registerNewUser(user);
    }
}
