package robert.oauth2.db.svc.api;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;

import robert.oauth2.SpringTest;
import robert.oauth2.db.entities.Role;
import robert.oauth2.db.entities.User;

public class DbServiceTest extends SpringTest {

	@Autowired
	private DbService dbService;

	@Test
	public void persistEntity() throws Exception {
		User user = generateTestUser();
		dbService.persistEntity(user);

		List<User> allEntities = dbService.getAllEntities(User.class);

		Assertions.assertThat(allEntities.contains(user))
				.isTrue();
	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	public void getAllEntities() throws Exception {
		final int numberOfEntities = 3;

		for (int i = 0; i < numberOfEntities; i++) {
			dbService.persistEntity(generateTestUser());
		}

		List<User> allEntities = dbService.getAllEntities(User.class);
		Assertions.assertThat(allEntities.size())
				.isEqualTo(numberOfEntities);
	}

	@Test
	public void findUserByEmail() throws Exception {
		User user = generateTestUser();
		dbService.persistEntity(user);

		User userByEmail = dbService.findUserByEmail(user.getEmail());

		Assertions.assertThat(userByEmail)
				.isEqualTo(user);
	}

	@Test
	@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
	public void grantAllAuthoritiesToUser() throws Exception {
		List<Role> roles = generateRoles();
		roles.forEach(dbService::persistEntity);

		User user = generateTestUser();
		dbService.persistEntity(user);

		Long userId = dbService.findUserByEmail(user.getEmail())
				.getId();
		dbService.grantAllAuthoritiesToUser(userId);

		user = dbService.findUserByEmail(user.getEmail());

		user.getRoles()
				.forEach(role -> Assertions.assertThat(roles)
						.contains(role));
	}

	@Test
	public void grantAuthorityToUser() throws Exception {
		User user = generateTestUser();
		dbService.persistEntity(user);

		Role role = new Role();
		role.setName("ROLE_TEST");
		dbService.persistEntity(role);

		// select for actual userId
		user = dbService.findUserByEmail(user.getEmail());

		dbService.grantAuthorityToUser(user.getId(), role.getName());
		user = dbService.findUserByEmail(user.getEmail());

		Assertions.assertThat(user.getRoles()
				.size())
				.isEqualTo(1);

		Assertions.assertThat(user.getRoles()
				.contains(role))
				.isTrue();
	}

	private List<Role> generateRoles() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");

		return Arrays.asList(roleUser, roleAdmin);
	}

}