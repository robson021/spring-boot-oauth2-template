package robert.oauth2jwt.db.repositories;

import org.springframework.data.repository.CrudRepository;
import robert.oauth2jwt.db.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
}
