package robert.oauth2.db.svc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import robert.oauth2.db.entities.BaseEntity;
import robert.oauth2.db.entities.Role;
import robert.oauth2.db.entities.User;
import robert.oauth2.db.svc.api.DbService;
import robert.oauth2.exceptions.TooShortPasswordException;

import javax.persistence.EntityManager;
import java.util.List;

import static robert.oauth2.exceptions.TooShortPasswordException.MIN_PASSWORD_LENGTH;

@Service
@Transactional
public class DbServiceImpl implements DbService {

    private static final Logger log = LoggerFactory.getLogger(DbServiceImpl.class);

    private final EntityManager em;

    private final PasswordEncoder passwordEncoder;

    public DbServiceImpl(EntityManager em, PasswordEncoder passwordEncoder) {
        this.em = em;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void persistEntity(BaseEntity entity) {
        em.persist(entity);
        if (log.isDebugEnabled()) {
            log.debug("Saved new entity: {}", entity);
        }
    }

    @Override
    public <T extends BaseEntity> List<T> getAllEntities(Class<T> clazz) {
        return em.createQuery("from " + clazz.getSimpleName(), clazz)
                .getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        return em.createQuery("from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public void registerNewUser(User user) {
        String password = user.getPassword();
        if (password.length() < MIN_PASSWORD_LENGTH) {
            throw new TooShortPasswordException();
        }
        user.setPassword(passwordEncoder.encode(password));
        this.persistEntity(user);
    }

    @Override
    public void grantAllAuthoritiesToUser(long userId) {
        User user = em.getReference(User.class, userId);
        user.getRoles()
                .addAll(getAllRoles());
    }

    @Override
    public void grantAuthorityToUser(long userId, String roleName) {
        User user = em.getReference(User.class, userId);
        user.getRoles()
                .add(getRoleByName(roleName));
    }

    private List<Role> getAllRoles() {
        return em.createQuery("from Role r", Role.class)
                .getResultList();
    }

    private Role getRoleByName(String roleName) {
        return em.createQuery("from Role r where r.name = :name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
    }
}
