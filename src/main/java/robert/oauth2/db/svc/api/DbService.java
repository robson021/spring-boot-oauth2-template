package robert.oauth2.db.svc.api;

import robert.oauth2.db.entities.BaseEntity;
import robert.oauth2.db.entities.User;

import java.util.List;

public interface DbService {

	void persistEntity(BaseEntity entity);

	<T extends BaseEntity> List<T> getAllEntities(Class<T> clazz);

	User findUserByEmail(String email);

	void registerNewUser(User user);

	void grantAllAuthoritiesToUser(long userId);

	void grantAuthorityToUser(long userId, String roleName);
}
