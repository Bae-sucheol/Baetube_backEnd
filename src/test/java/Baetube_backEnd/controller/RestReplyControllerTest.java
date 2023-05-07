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

import Baetube_backEnd.dto.Reply;
import Baetube_backEnd.mapper.ReplyMapper;
import Baetube_backEnd.service.reply.ReplyInsertService;
import Baetube_backEnd.service.reply.ReplySelectService;
import Baetube_backEnd.service.reply.ReplyUpdateService;

public class RestReplyControllerTest
{
	@Spy
	@InjectMocks
	private RestReplyController replyController;
	@Spy
	@InjectMocks
	private ReplyInsertService replyInsertService;
	@Spy
	@InjectMocks
	private ReplySelectService replySelectService;
	@Spy
	@InjectMocks
	private ReplyUpdateService replyUpdateService;
	
	@Mock
	private ReplyMapper replyMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private Reply correctReply;
	private Reply wrongReply; 
	
	private String correctContent;
	private String wrongContent;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		replyController = spy(new RestReplyController());
		replyInsertService = spy(new ReplyInsertService());
		replySelectService = spy(new ReplySelectService());
		replyUpdateService = spy(new ReplyUpdateService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(replyController).build();
		objectMapper = new ObjectMapper();
		
		correctReply = new Reply(1, 1L, 1L, 1, "test", 0, 0, null, 0, "test", "test");
		wrongReply = new Reply(2, 2L, 2L, 2, "test2", 0, 0, null, 0, "test2", "test2");
		
		correctContent = objectMapper.writeValueAsString(correctReply);
		wrongContent = objectMapper.writeValueAsString(wrongReply);
	}
	
	@Test
	public void replyInsertTest() throws Exception
	{
		mockMvc.perform(post("/api/reply/insert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(replyMapper, atLeastOnce()).insert(any());
	}
	
	@Test
	public void replySelectTest() throws Exception
	{
		ArrayList<Reply> replyList = new ArrayList<>();
		replyList.add(correctReply);
		
		
		when(replyMapper.selectByContentsId(1L)).thenReturn(replyList);
		when(replyMapper.selectByContentsId(2L)).thenReturn(null);
		
		mockMvc.perform(get("/api/reply/select")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/reply/select")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}

	@Test
	public void replyUpdateTest() throws Exception
	{
		when(replyMapper.selectByReplyId(1)).thenReturn(correctReply);
		when(replyMapper.selectByReplyId(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/reply/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(replyMapper, atLeastOnce()).updateComment(any(), any());
		
		mockMvc.perform(post("/api/reply/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
}
