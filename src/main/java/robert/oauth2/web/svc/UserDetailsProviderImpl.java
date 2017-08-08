package robert.oauth2.web.svc;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import robert.oauth2.web.security.userdetails.UserDetailsImpl;
import robert.oauth2.web.svc.api.UserDetailsProvider;

@Service
public class UserDetailsProviderImpl implements UserDetailsProvider {

	@Override
	public UserDetailsImpl getUserDetails() {
		return (UserDetailsImpl) SecurityContextHolder.getContext()
				.getAuthentication()
				.getPrincipal();
	}

	@Override
	public long getUserId() {
		return this.getUserDetails()
				.getUserId();
	}

	@Override
	public String getUserEmail() {
		return this.getUserDetails()
				.getUsername();
	}
}
