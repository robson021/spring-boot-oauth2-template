package robert.oauth2;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import robert.oauth2.web.svc.api.UserDetailsProvider;

@Configuration
public class MockBeansConfig {

    @Bean
    @Primary
    public UserDetailsProvider userDetailsProvider() {
        return Mockito.mock(UserDetailsProvider.class);
    }
}
