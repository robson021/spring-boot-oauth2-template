package robert.oauth2.web.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import robert.oauth2.MvcTest;
import robert.oauth2.web.svc.api.UserDetailsProvider;

public class UserControllerTest extends MvcTest {

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Before
    public void setup() {
        Mockito.when(userDetailsProvider.getUserId())
                .thenReturn(1L);

        Mockito.when(userDetailsProvider.getUserEmail())
                .thenReturn("test@user.pl");
    }

    @Test
    public void sayHello() throws Exception {

    }

    @Test
    public void registerNewUser() throws Exception {
    }

}