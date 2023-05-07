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

import Baetube_backEnd.dto.NestedReply;
import Baetube_backEnd.mapper.NestedReplyMapper;
import Baetube_backEnd.service.nestedreply.NestedReplyInsertService;
import Baetube_backEnd.service.nestedreply.NestedReplySelectService;
import Baetube_backEnd.service.nestedreply.NestedReplyUpdateService;

public class RestNestedReplyControllerTest
{
	@Spy
	@InjectMocks
	private RestNestedReplyController restNestedReplyController;
	@Spy
	@InjectMocks
	private NestedReplyInsertService nestedReplyInsertService;
	@Spy
	@InjectMocks
	private NestedReplySelectService nestedReplySelectService;
	@Spy
	@InjectMocks
	private NestedReplyUpdateService nestedReplyUpdateService;
	
	@Mock
	private NestedReplyMapper nestedReplyMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private NestedReply correctNestedReply;
	private NestedReply wrongNestedReply;
	private String correctContent;
	private String wrongContent;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		restNestedReplyController = spy(new RestNestedReplyController());
		nestedReplyInsertService = spy(new NestedReplyInsertService());
		nestedReplySelectService = spy(new NestedReplySelectService());
		nestedReplyUpdateService = spy(new NestedReplyUpdateService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restNestedReplyController).build();
		
		objectMapper = new ObjectMapper();
		
		correctNestedReply = new NestedReply(1, 1, 1L, 1, "test", null, "test", "test");
		wrongNestedReply = new NestedReply(2, 2, 2L, 2, "test2", null, "test2", "test2");
		
		correctContent = objectMapper.writeValueAsString(correctNestedReply);
		wrongContent = objectMapper.writeValueAsString(wrongNestedReply);
	}
	
	@Test
	public void nestedReplyInsertTest() throws Exception
	{
		mockMvc.perform(post("/api/nestedreply/insert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(nestedReplyMapper, atLeastOnce()).insert(any());
	}
	
	@Test
	public void nestedReplySelectTest() throws Exception
	{
		ArrayList<NestedReply> arrayList = new ArrayList<>();
		arrayList.add(correctNestedReply);
		
		when(nestedReplyMapper.selectByReplyId(1)).thenReturn(arrayList);
		when(nestedReplyMapper.selectByReplyId(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/nestedreply/select")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/nestedreply/select")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void nestedReplyUpdateTest() throws Exception
	{
		when(nestedReplyMapper.selectByNestedReplyId(1)).thenReturn(correctNestedReply);
		when(nestedReplyMapper.selectByNestedReplyId(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/nestedreply/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(nestedReplyMapper, atLeastOnce()).updateComment(1, "test");
		
		mockMvc.perform(get("/api/nestedreply/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
