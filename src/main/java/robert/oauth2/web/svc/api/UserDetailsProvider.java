package robert.oauth2.web.svc.api;

import robert.oauth2.web.security.userdetails.UserDetailsImpl;

public interface UserDetailsProvider {

	UserDetailsImpl getUserDetails();

	long getUserId();

	String getUserEmail();
}
