package robert.oauth2jwt.security.config.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import robert.oauth2jwt.db.entities.User;
import robert.oauth2jwt.db.svc.api.DbService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private final DbService dbService;

	public UserDetailsServiceImpl(DbService dbService) {
		this.dbService = dbService;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if ( log.isDebugEnabled() ) {
			log.debug("trying to get details for user '{}'", email);
		}
		User user = dbService.findUserByEmail(email);
		return new UserDetailsImpl(user);
	}
}
