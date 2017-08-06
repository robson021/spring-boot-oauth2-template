package robert.oauth2jwt.web.svc.api;

import robert.oauth2jwt.web.config.security.userdetails.UserDetailsImpl;

public interface UserDetailsProvider {

	UserDetailsImpl getUserDetails();

	long getUserId();

	String getUserEmail();
}
