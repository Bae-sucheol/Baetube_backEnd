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

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.mapper.CommunityMapper;
import Baetube_backEnd.service.community.CommunityChannelVisitService;
import Baetube_backEnd.service.community.CommunityDeleteService;
import Baetube_backEnd.service.community.CommunityInsertService;

public class RestCommunityControllerTest
{
	@Spy
	@InjectMocks
	private RestCommunityController restCommunityController;
	@Spy
	@InjectMocks
	private CommunityChannelVisitService communityChannelVisitService;
	@Spy
	@InjectMocks
	private CommunityDeleteService communityDeleteService;
	@Spy
	@InjectMocks
	private CommunityInsertService communityInsertService;
	
	@Mock
	private CommunityMapper communityMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private Community correctCommunity;
	private Community wrongCommunity;
	
	@Before
	public void setUp()
	{
		objectMapper = new ObjectMapper();
		
		restCommunityController = spy(new RestCommunityController());
		communityChannelVisitService = spy(new CommunityChannelVisitService());
		communityDeleteService = spy(new CommunityDeleteService());
		communityInsertService = spy(new CommunityInsertService());
		
		mockMvc = MockMvcBuilders.standaloneSetup(restCommunityController).build();
		MockitoAnnotations.initMocks(this);
		
		correctCommunity = new Community(1, 1L, 1, 0, 0, 0, "test", "test", null);
		wrongCommunity = new Community(2, 2L, 2, 0, 0, 0, "test", "test", null);
	}
	
	@Test
	public void communityChannelVisitTest() throws Exception
	{
		Channel correctChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null, "test");
		Channel wrongChannel = new Channel(2, 2, 0, 0, "test", "test", "test", null, "test");
		
		ArrayList<Community> communityList = new ArrayList<>();
		communityList.add(correctCommunity);
		
		when(communityMapper.selectByChannel(1, 1)).thenReturn(communityList);
		when(communityMapper.selectByChannel(2, 2)).thenReturn(null);
		
		String correctContent = objectMapper.writeValueAsString(correctChannel);
	
		mockMvc.perform(get("/api/community/channel_visit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		String wrongContent = objectMapper.writeValueAsString(wrongChannel);
		
		mockMvc.perform(get("/api/community/channel_visit")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
	}
	
	@Test
	public void communityDeleteTest() throws Exception
	{
		when(communityMapper.selectByCommunityId(1)).thenReturn(correctCommunity);
		when(communityMapper.selectByCommunityId(2)).thenReturn(null);
		
		String correctContent = objectMapper.writeValueAsString(correctCommunity);
	
		mockMvc.perform(post("/api/community/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(communityMapper, atLeastOnce()).delete(1);
		
		String wrongContent = objectMapper.writeValueAsString(wrongCommunity);
		
		mockMvc.perform(post("/api/community/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
	}
	
	@Test
	public void communityInsertTest() throws Exception
	{
		String correctContent = objectMapper.writeValueAsString(correctCommunity);
	
		mockMvc.perform(post("/api/community/insert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(communityMapper, atLeastOnce()).insert(any());
	}
	
}
