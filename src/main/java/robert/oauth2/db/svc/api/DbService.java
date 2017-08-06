package robert.oauth2.db.svc.api;

import java.util.List;

import robert.oauth2.db.entities.BaseEntity;
import robert.oauth2.db.entities.User;

public interface DbService {

	void persistEntity(BaseEntity entity);

	<T extends BaseEntity> List<T> getAllEntities(Class<T> clazz);

	User findUserByEmail(String email);

	void grantAllAuthoritiesToUser(long userId);

	void grantAuthorityToUser(long userId, String roleName);
}
