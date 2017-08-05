package robert.oauth2jwt;

import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import robert.oauth2jwt.db.entities.Role;
import robert.oauth2jwt.db.entities.User;
import robert.oauth2jwt.db.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;

@Component
@Profile("dev")
public class DevSettings {

	private final UserRepository repository;

	private final PasswordEncoder encoder;

	public DevSettings(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

	@PostConstruct
	public void init() {

		Role roleUser = new Role();
		Role roleAdmin = new Role();
		roleUser.setName("ROLE_USER");
		roleAdmin.setName("ROLE_ADMIN");


		User user = new User();
		user.setName("John");
		user.setSurname("Doe");
		user.setEmail("test@t.pl");
		user.setPassword(encoder.encode("password"));
		user.setRoles(Collections.singleton(roleUser));
		roleUser.setUsers(Collections.singleton(user));

		User admin = new User();
		admin.setName("Mark");
		admin.setSurname("Smith");
		admin.setPassword(encoder.encode("password"));
		admin.setEmail("admin@t.pl");

		user = repository.save(user);
		admin = repository.save(admin);

		roleUser = user.getRoles()
				.iterator()
				.next();

		admin.setRoles(new HashSet<>());
		admin.getRoles().add(roleUser);
		admin.getRoles().add(roleAdmin);
		roleAdmin.setUsers(Collections.singleton(admin));
		roleUser.getUsers().add(admin);

		repository.save(admin);

		System.out.println("Saved users:");
		repository.findAll()
				.forEach(System.out::println);
	}
}
