package robert.oauth2.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import robert.oauth2.MvcTest;
import robert.oauth2.db.entities.User;
import robert.oauth2.web.svc.api.UserDetailsProvider;

public class UserControllerTest extends MvcTest {

	private static String EMAIL = "test@user.pl";

	private static final long ID = 21L;

	@Autowired
	private UserDetailsProvider userDetailsProvider;

	@Before
	public void setup() {
		Mockito.when(userDetailsProvider.getUserId())
				.thenReturn(ID);

		Mockito.when(userDetailsProvider.getUserEmail())
				.thenReturn(EMAIL);
	}

	@Test
	public void sayHello() throws Exception {
		MvcResult result = mockMvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andReturn();

		String resultAsString = result.getResponse()
				.getContentAsString();

		System.out.println(resultAsString);
		Assertions.assertThat(resultAsString)
				.contains(EMAIL)
				.contains(String.valueOf(ID));
	}

	@Test
	public void registerNewUser() throws Exception {
		User user = generateTestUser();

		mockMvc.perform(post("/register").content(objectToJson(user))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void failRegistration() throws Exception {
		User user = generateTestUser();
		user.setPassword("short");
		mockMvc.perform(post("/register").content(objectToJson(user))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}

