package robert.oauth2jwt.web.config.security.userdetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import robert.oauth2jwt.db.entities.User;

public class UserDetailsImpl implements UserDetails {

	private final String username;

	private final String password;

	private final Set<? extends GrantedAuthority> authorities;

	UserDetailsImpl(User user) {
		Assert.notNull(user, "No user has been found");
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toSet());
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

	@Override
	public String toString() {
		return "UserDetailsImpl{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", authorities=" + authorities + '}';
	}
}
