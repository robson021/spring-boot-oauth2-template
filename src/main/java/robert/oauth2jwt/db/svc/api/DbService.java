package robert.oauth2jwt.db.svc.api;

import java.util.List;

import robert.oauth2jwt.db.entities.BaseEntity;
import robert.oauth2jwt.db.entities.User;

public interface DbService {

	void persistEntity(BaseEntity entity);

	<T> List<T> getAllEntities(Class<T> clazz);

	User findUserByEmail(String email);

	void grantAllAuthoritiesToUser(long userId);

	void grantAuthorityToUser(long userId, String roleName);
}