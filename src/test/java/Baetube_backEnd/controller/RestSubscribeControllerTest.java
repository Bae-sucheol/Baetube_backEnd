package Baetube_backEnd.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
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

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.mapper.SubscribeMapper;
import Baetube_backEnd.service.subscribe.SubscribeDeleteService;
import Baetube_backEnd.service.subscribe.SubscribeInsertService;
import Baetube_backEnd.service.subscribe.SubscribeSelectService;

public class RestSubscribeControllerTest
{
	@Spy
	@InjectMocks
	private RestSubscribeController restSubscribeController;
	@Spy
	@InjectMocks
	private SubscribeInsertService subscribeInsertService;
	@Spy
	@InjectMocks
	private SubscribeDeleteService subscribeDeleteService;
	@Spy
	@InjectMocks
	private SubscribeSelectService subscribeSelectService;
	
	@Mock
	private SubscribeMapper subscribeMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private Subscribers subscribers;
	private String content;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		restSubscribeController = spy(new RestSubscribeController());
		subscribeInsertService = spy(new SubscribeInsertService());
		subscribeDeleteService = spy(new SubscribeDeleteService());
		subscribeSelectService = spy(new SubscribeSelectService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restSubscribeController).build();
		objectMapper = new ObjectMapper();
		
		subscribers = new Subscribers(1, 1);
		content = objectMapper.writeValueAsString(subscribers);
	}
	
	@Test
	public void subscribeInsertTest() throws Exception
	{	
		when(subscribeMapper.select(any(), any())).thenReturn(null);
		
		mockMvc.perform(post("/api/subscribe/subscribe")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(subscribeMapper, atLeastOnce()).subscribe(any());
		when(subscribeMapper.select(any(), any())).thenReturn(subscribers);
		
		mockMvc.perform(post("/api/subscribe/subscribe")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void subscribeDeleteTest() throws Exception
	{
		when(subscribeMapper.select(any(), any())).thenReturn(subscribers);
		
		mockMvc.perform(post("/api/subscribe/unsubscribe")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(subscribeMapper, atLeastOnce()).unSubscribe(any());
		when(subscribeMapper.select(any(), any())).thenReturn(null);
		
		mockMvc.perform(post("/api/subscribe/unsubscribe")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void subscribeSelectTest() throws Exception
	{
		Subscribers subscribers = new Subscribers(1, 1);
		String content = objectMapper.writeValueAsString(subscribers);
		
		when(subscribeMapper.select(any(), any())).thenReturn(subscribers);
		
		mockMvc.perform(get("/api/subscribe/select")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isOk())
		.andDo(print());
		
		when(subscribeMapper.select(any(), any())).thenReturn(null);
		
		mockMvc.perform(get("/api/subscribe/select")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
