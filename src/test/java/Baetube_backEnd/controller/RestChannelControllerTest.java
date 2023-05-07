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

import java.util.ArrayList;

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

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.service.channel.ChannelDeleteService;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.channel.ChannelSelectService;
import Baetube_backEnd.service.channel.ChannelSubscribersService;
import Baetube_backEnd.service.channel.ChannelUpdateService;
import Baetube_backEnd.service.channel.ChannelVisitService;

public class RestChannelControllerTest
{
	
	@Spy
	@InjectMocks
	private RestChannelController restChannelController;
	@Spy
	@InjectMocks
	private ChannelDeleteService channelDeleteService;
	@Spy
	@InjectMocks
	private ChannelInsertService channelInsertService;
	@Spy
	@InjectMocks
	private ChannelUpdateService channelUpdateService;
	@Spy
	@InjectMocks
	private ChannelVisitService channelVisitService;
	@Spy
	@InjectMocks
	private ChannelSubscribersService channelSubscribersService;
	@Spy
	@InjectMocks
	private ChannelSelectService channelSelectService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private Channel correctChannel;
	private Channel wrongChannel;
	
	private String correctContent;
	private String wrongContent;
	
	@Before
	public void setUp() throws JsonProcessingException
	{	
		restChannelController = spy(new RestChannelController());
		channelDeleteService = spy(new ChannelDeleteService());
		channelInsertService = spy(new ChannelInsertService());
		channelUpdateService = spy(new ChannelUpdateService());
		channelVisitService = spy(new ChannelVisitService());
		channelSubscribersService = spy(new ChannelSubscribersService());
		channelSelectService = spy(new ChannelSelectService());
		
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();
		
		mockMvc = MockMvcBuilders.standaloneSetup(restChannelController).build();
		
		correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null, "test");
		wrongChannel = new Channel(2, 2, 0, 0, "test", "test", "test", null, "test");
		
		correctContent = objectMapper.writeValueAsString(correctChannel);
		wrongContent = objectMapper.writeValueAsString(wrongChannel);
	}
	
	@Test
	public void ChannelDeleteTest() throws Exception
	{	
		when(channelMapper.select(1)).thenReturn(correctChannel);
		when(channelMapper.select(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/channel/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(1);
		verify(channelMapper, atLeastOnce()).delete(1);
		
		mockMvc.perform(post("/api/channel/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(2);
	}
	
	@Test
	public void ChannelInsertTest() throws Exception
	{	
		String correctContent = objectMapper.writeValueAsString(correctChannel);
		
		mockMvc.perform(post("/api/channel/insert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).insert(any());
	}
	
	@Test
	public void ChannelUpdateTest() throws Exception
	{
		when(channelMapper.select(1)).thenReturn(correctChannel);
		when(channelMapper.select(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/channel/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(1);
		verify(channelMapper, atLeastOnce()).update(any(), any());
		
		mockMvc.perform(post("/api/channel/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(2);
	}
	
	@Test
	public void ChannelVisitTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null, "test");
		
		when(channelMapper.select(1)).thenReturn(correctChannel);
		when(channelMapper.select(2)).thenReturn(null);

		mockMvc.perform(get("/api/channel/visit/1"))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(1);

		mockMvc.perform(get("/api/channel/visit/2"))
		.andExpect(status().isConflict())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).select(2);
	}
	
	@Test
	public void ChannelSubscribersTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null, "test");
		
		ArrayList<Channel> correctChannelList = new ArrayList<>();
		correctChannelList.add(correctChannel);
		
		when(channelMapper.selectSubscribers(1)).thenReturn(correctChannelList);
		when(channelMapper.selectSubscribers(2)).thenReturn(null);

		mockMvc.perform(get("/api/channel/subscribers/0"))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).selectSubscribers(1);

		mockMvc.perform(get("/api/channel/subscribers/1"))
		.andExpect(status().isConflict())
		.andDo(print());
		
		verify(channelMapper, atLeastOnce()).selectSubscribers(2);
		
	}
}
