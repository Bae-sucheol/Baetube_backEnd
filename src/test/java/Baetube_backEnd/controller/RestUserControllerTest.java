package Baetube_backEnd.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.User;
import Baetube_backEnd.mapper.UserMapper;
import Baetube_backEnd.service.user.ChangePasswordService;
import Baetube_backEnd.service.user.UserLoginService;
import Baetube_backEnd.service.user.UserRegisterService;
import Baetube_backEnd.service.user.UserUnregisterService;
import Baetube_backEnd.service.user.UserUpdateService;

public class RestUserControllerTest
{
	@Spy
	@InjectMocks
	private RestUserController restUserController;
	@Spy
	@InjectMocks
	private UserRegisterService userRegisterService;
	@Spy
	@InjectMocks
	private UserLoginService userLoginService;
	@Spy
	@InjectMocks
	private UserUnregisterService userUnregisterService;
	@Spy
	@InjectMocks
	private ChangePasswordService changePasswordService;
	@Spy
	@InjectMocks
	private UserUpdateService userUpdateService;
	
	@Mock
	private UserMapper userMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private User correctUser;
	private User wrongUser;
	
	private String correctContent;
	private String wrongContent;
	
	private User defaultPasswordUser;
	private User wrongPasswordUser;
	private String wrongPasswordContent;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		restUserController = spy(new RestUserController());
		userRegisterService = spy(new UserRegisterService());
		userLoginService = spy(new UserLoginService());
		userUnregisterService = spy(new UserUnregisterService());
		changePasswordService = spy(new ChangePasswordService());
		userUpdateService = spy(new UserUpdateService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restUserController).build(); 
		objectMapper = new ObjectMapper();
		
		correctUser = new User(1, "test", "1234", "1234", 1, null, "1234", "1234", "1234", null);
		wrongUser = new User(2, "test2", "1234", "1234", 1, null, "1234", "1234", "1234", null);
		
		correctContent = objectMapper.writeValueAsString(correctUser);
		wrongContent = objectMapper.writeValueAsString(wrongUser);
		
		defaultPasswordUser = new User(3, "test3", "1234", "1234", 1, null, "1234", "1234", "1234", null);
		wrongPasswordUser = new User(3, "test3", "12345", "1234", 1, null, "1234", "1234", "1234", null);
		wrongPasswordContent = objectMapper.writeValueAsString(defaultPasswordUser);
	}
	@Ignore
	@Test
	public void userRegisterTest() throws Exception
	{
		when(userMapper.selectByEmail("test")).thenReturn(null);
		when(userMapper.selectByEmail("test2")).thenReturn(wrongUser);
		
		mockMvc.perform(post("/api/user/regist")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(userMapper, atLeastOnce()).insert(any());
		
		mockMvc.perform(post("/api/user/regist")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	@Ignore
	@Test
	public void userUnregisterTest() throws Exception
	{
		when(userMapper.selectByEmail("test")).thenReturn(correctUser);
		when(userMapper.selectByEmail("test2")).thenReturn(null);
		
		mockMvc.perform(post("/api/user/unregist")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(userMapper, atLeastOnce()).delete(any());
		
		mockMvc.perform(post("/api/user/unregist")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	@Ignore
	@Test
	public void userLoginTest() throws Exception
	{
		when(userMapper.selectByEmail("test")).thenReturn(correctUser);
		when(userMapper.selectByEmail("test2")).thenReturn(null);
		when(userMapper.selectByEmail("test3")).thenReturn(wrongPasswordUser);
		
		
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		/*
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		mockMvc.perform(post("/api/user/login")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongPasswordContent))
		.andExpect(status().isConflict())
		.andDo(print());
		*/
	}
	@Ignore
	@Test
	public void userUpdateTest() throws Exception
	{
		when(userMapper.selectByEmail("test")).thenReturn(correctUser);
		when(userMapper.selectByEmail("test2")).thenReturn(null);
		
		mockMvc.perform(post("/api/user/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(userMapper, atLeastOnce()).update(any(), any());
		
		mockMvc.perform(post("/api/user/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	@Ignore
	@Test
	public void userChangePasswordTest() throws Exception
	{
		when(userMapper.selectByEmail("test")).thenReturn(correctUser);
		when(userMapper.selectByEmail("test2")).thenReturn(null);
		when(userMapper.selectByEmail("test3")).thenReturn(wrongPasswordUser);
		
		mockMvc.perform(post("/api/user/change_password")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(userMapper, atLeastOnce()).changePassword(any(), any());
		
		mockMvc.perform(post("/api/user/change_password")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		mockMvc.perform(post("/api/user/change_password")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongPasswordContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
