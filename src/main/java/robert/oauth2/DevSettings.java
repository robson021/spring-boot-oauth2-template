package robert.oauth2;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import robert.oauth2.db.entities.Role;
import robert.oauth2.db.entities.User;
import robert.oauth2.db.svc.api.DbService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

@Component
@Profile("dev")
public class DevSettings {

    private final DbService dbService;

    private final PasswordEncoder encoder;

    public DevSettings(PasswordEncoder encoder, DbService dbService) {
        this.encoder = encoder;
        this.dbService = dbService;
    }

    @PostConstruct
    public void init() {
        saveRoles();
        saveTestUsers();
        addRolesToUsers();

        System.out.println("saved users:");
        List<User> allUsers = dbService.getAllEntities(User.class);
        allUsers.forEach(System.out::println);
    }

    private void saveRoles() {
        Role roleUser = new Role();
        Role roleAdmin = new Role();
        roleUser.setName("ROLE_USER");
        roleAdmin.setName("ROLE_ADMIN");

        Stream.of(roleUser, roleAdmin)
                .forEach(dbService::persistEntity);
    }

    private void saveTestUsers() {
        User user = new User();
        user.setName("John");
        user.setSurname("Doe");
        user.setEmail("user@t.pl");
        user.setPassword(encoder.encode("password"));

        User admin = new User();
        admin.setName("Mark");
        admin.setSurname("Smith");
        admin.setPassword(encoder.encode("password"));
        admin.setEmail("admin@t.pl");

        Stream.of(user, admin)
                .forEachOrdered(dbService::persistEntity);
    }

    private void addRolesToUsers() {
        dbService.grantAuthorityToUser(1L, "ROLE_USER"); // grant ROLE_USER to user
        dbService.grantAllAuthoritiesToUser(2L); // grant all roles to admin
    }
}
