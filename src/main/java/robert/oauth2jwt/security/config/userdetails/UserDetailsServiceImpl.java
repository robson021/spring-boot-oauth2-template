package robert.oauth2jwt.security.config.userdetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import robert.oauth2jwt.db.entities.User;
import robert.oauth2jwt.db.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (log.isDebugEnabled()) {
			log.debug("trying to get details for user '{}'", username);
		}
		User user = userRepository.findByEmail(username);
		return new UserDetailsImpl(user);
	}
}
