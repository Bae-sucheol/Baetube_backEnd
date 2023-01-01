package Baetube_backEnd;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.controller.RestUserController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/webapp/WEB-INF/spring/**/root-context.xml" })
public class test
{
	@Autowired
	private RestUserController restUserController;
	
	
	@Mock
	private MockMvc mockMvc;
	
	@Before
	public void setUp() 
	{
		mockMvc = MockMvcBuilders.standaloneSetup(restUserController).build();
    }
	
	@Test
	public void Test() throws Exception
	{
		 Map<String, String> input = new HashMap<>();
		    input.put("email", "test2@google.com");
		    input.put("password", "test2_password");
		    input.put("name", "test2");
		    input.put("gender", "1");
		    input.put("birth", "1996-06-07 00:00:00");
		    input.put("fcmToken", "test2_token");
		    input.put("phone", "test2_phone");
		    input.put("address", "test2_address");
		    
		    ObjectMapper objectMapper = new ObjectMapper();
	 
		mockMvc.perform(MockMvcRequestBuilders.post("/api/regist")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(objectMapper.writeValueAsString(input)))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
}
