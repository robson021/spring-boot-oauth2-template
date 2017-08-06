package robert.oauth2jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "robert.oauth2jwt.db.entities")
@EnableTransactionManagement
public class Oauth2JwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2JwtDemoApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Autowired
	public void authenticationManager(AuthenticationManagerBuilder builder, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) throws Exception {
		builder
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder);
	}
}

/*
* to get token, use command (linux or mac):
* curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=password&username=user@t.pl&grant_type=password&scope=openid&client_secret=secret&client_id=client"
*/