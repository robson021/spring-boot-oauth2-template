package robert.oauth2;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class MvcTest extends SpringTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private WebApplicationContext wac;

	protected MockMvc mockMvc;

	@Before
	public void init() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.build();
	}

	protected static <T> T jsonToObject(String json, Class<T> clazz) throws Exception {
		return objectMapper.readValue(json, clazz);
	}

	protected static String objectToJson(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}
}
