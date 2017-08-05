package robert.oauth2jwt.security.config.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import robert.oauth2jwt.db.entities.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class UserDetailsImpl implements UserDetails {

	private final String username;

	private final String password;

	private final Set<? extends GrantedAuthority> authorities;

	UserDetailsImpl(User user) {
		Assert.notNull(user, "No user has been found");
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
