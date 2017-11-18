package com.wedevol.springdatacouchbase.core.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.wedevol.springdatacouchbase.core.EntryPoint;
import com.wedevol.springdatacouchbase.core.dao.UserRepository;
import com.wedevol.springdatacouchbase.core.dao.doc.UserDoc;

/**
 * Test the User Controller: test the endpoints directly
 *
 * @author Charz++
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EntryPoint.class)
@WebAppConfiguration
public class UserControllerTest {

	private static final Long USER_ONE_ID = 1L;
	private static final Long USER_TWO_ID = 2L;
	private static final Long USER_THREE_ID = 3L;
	private static final Long USER_FOUR_ID = 4L;
	private static final String USER_ONE_KEY = UserDoc.getKeyFor(USER_ONE_ID);
	private static final String USER_TWO_KEY = UserDoc.getKeyFor(USER_TWO_ID);
	private static final String USER_THREE_KEY = UserDoc.getKeyFor(USER_THREE_ID);
	private static final String USER_FOUR_KEY = UserDoc.getKeyFor(USER_FOUR_ID);
	private static MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	private MockMvc mockMvc;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {
		mappingJackson2HttpMessageConverter = Arrays.asList(converters)
															.stream()
															.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
															.findAny()
															.orElse(null);
		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void init() {
		mockMvc = webAppContextSetup(webApplicationContext).build();
		// create the users
		UserDoc user1 = new UserDoc(USER_ONE_KEY, USER_ONE_ID, "Carlos", Arrays.asList("charz"), 26, "carlos@yopmail.com");
		UserDoc user2 = new UserDoc(USER_TWO_KEY, USER_TWO_ID, "Luis", Arrays.asList("charz", "lucho"), 25, "luis@yopmail.com");
		UserDoc user3 = new UserDoc(USER_THREE_KEY, USER_THREE_ID, "Jose", Arrays.asList("pepe"), 26, "jose@yopmail.com");
		// save the users in the database
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
	}
	
	@After
	public void tearDown() {
		// delete all the test data created from the database
		userRepository.delete(USER_ONE_KEY);
		userRepository.delete(USER_TWO_KEY);
		userRepository.delete(USER_THREE_KEY);
		userRepository.delete(USER_FOUR_KEY);
		userRepository.delete("user::counter");
	}
	
	@Test (expected = NestedServletException.class)
    public void getNonExistingUser() throws Exception {
        mockMvc.perform(get("/users/100"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(CONTENT_TYPE));
    }

    @Test
    public void getExistingUser() throws Exception {
        mockMvc.perform(get("/users/" + USER_ONE_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Carlos")))
                .andExpect(jsonPath("$.age", Matchers.is(26)))
                .andExpect(jsonPath("$.nicknames[0]", Matchers.is("charz")))
                .andExpect(jsonPath("$.email", Matchers.is("carlos@yopmail.com")));
    }
    
    @Test
    public void createUser() throws Exception {
    	UserDoc user4 = new UserDoc(USER_FOUR_KEY, USER_FOUR_ID, "Alfredo", Arrays.asList("alfredo"), 40, "alfredo@yopmail.com");
        mockMvc.perform(post("/users")
               .contentType(CONTENT_TYPE)
               .content(json(user4)))
               .andExpect(status().isCreated());
    }
    
    @Test
    public void updateUser() throws Exception {
    	UserDoc user3 = new UserDoc(USER_THREE_ID, "Jose", Arrays.asList("pancho"));
        mockMvc.perform(put("/users/" + USER_THREE_ID)
               .contentType(CONTENT_TYPE)
               .content(json(user3)))
               .andExpect(status().isOk());
    }
    
    @Test
    public void existUser() throws Exception {
        mockMvc.perform(get("/users/" + USER_ONE_ID + "/exists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", Matchers.is(true)));
    }
    
    @Test
    public void getExistingUserByEmail() throws Exception {
        mockMvc.perform(get("/users/find/email")
        			.param("email", "luis@yopmail.com"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.id", Matchers.is(2)))
                .andExpect(jsonPath("$.email", Matchers.is("luis@yopmail.com")));
    }
    
    @Test
    public void getExistingUserByNickname() throws Exception {
        mockMvc.perform(get("/users/find/nickname")
        			.param("nickname", "charz"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
	public void findAll() throws Exception{
		mockMvc.perform(get("/users/find/all"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(CONTENT_TYPE))
				.andExpect(jsonPath("$", Matchers.hasSize(3)));
	}

	@Test
	public void countAll() throws Exception{
		mockMvc.perform(get("/users/count/all"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(CONTENT_TYPE))
				.andExpect(jsonPath("$", Matchers.is(3)));
	}

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

}
