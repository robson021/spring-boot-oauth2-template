package robert.oauth2;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import robert.oauth2.db.entities.User;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public abstract class SpringTest {

	protected User generateTestUser() {
		User user = new User();
		String email = String.format("test%s@t.pl", System.nanoTime());
		user.setEmail(email);
		user.setName("Test");
		user.setSurname("Test");
		user.setPassword("Password.123");
		return user;
	}

}
