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

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.Vote;
import Baetube_backEnd.mapper.VoteMapper;
import Baetube_backEnd.service.vote.VoteDeleteOptionMultiService;
import Baetube_backEnd.service.vote.VoteDeleteOptionService;
import Baetube_backEnd.service.vote.VoteDeleteService;
import Baetube_backEnd.service.vote.VoteInsertOptionMultiService;
import Baetube_backEnd.service.vote.VoteInsertOptionService;
import Baetube_backEnd.service.vote.VoteInsertService;
import Baetube_backEnd.service.vote.VoteSelectOptionService;
import Baetube_backEnd.service.vote.VoteUpdateOptionService;
import Baetube_backEnd.service.vote.VoteUpdateService;

public class RestVoteControllerTest
{
	@Spy
	@InjectMocks
	private RestVoteController restVoteController;
	@Spy
	@InjectMocks
	private VoteInsertService voteInsertService;
	@Spy
	@InjectMocks
	private VoteInsertOptionService voteInsertOptionService;
	@Spy
	@InjectMocks
	private VoteInsertOptionMultiService voteInsertOptionMultiService;
	@Spy
	@InjectMocks
	private VoteDeleteService voteDeleteService;
	@Spy
	@InjectMocks
	private VoteDeleteOptionService voteDeleteOptionService;
	@Spy
	@InjectMocks
	private VoteDeleteOptionMultiService voteDeleteOptionMultiService;
	@Spy
	@InjectMocks
	private VoteUpdateService voteUpdateService;
	@Spy
	@InjectMocks
	private VoteUpdateOptionService voteUpdateOptionService;
	@Spy
	@InjectMocks
	private VoteSelectOptionService VoteSelectOptionService;
	
	@Mock
	private VoteMapper voteMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		restVoteController = spy(new RestVoteController());
		voteInsertService = spy(new VoteInsertService());
		voteInsertOptionService = spy(new VoteInsertOptionService());
		voteInsertOptionMultiService = spy(new VoteInsertOptionMultiService());
		voteDeleteService = spy(new VoteDeleteService());
		voteDeleteOptionService = spy(new VoteDeleteOptionService());
		voteDeleteOptionMultiService = spy(new VoteDeleteOptionMultiService());
		voteUpdateService = spy(new VoteUpdateService());
		voteUpdateOptionService = spy(new VoteUpdateOptionService());
		VoteSelectOptionService = spy(new VoteSelectOptionService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restVoteController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void voteInsertTest() throws Exception
	{
		Vote vote = new Vote(1, "test", "test");
		
		String content = objectMapper.writeValueAsString(vote);
		
		mockMvc.perform(post("/api/vote/insert")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).insertVote(any());
	}
	
	@Test
	public void voteInsertOptionTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
		Vote nullVote = new Vote(3, 3, "test3", 0);
		
		String correctContent = objectMapper.writeValueAsString(correctVote);
		String wrongContent = objectMapper.writeValueAsString(wrongVote);
		String nullContent = objectMapper.writeValueAsString(nullVote);
		
		ArrayList<Vote> correctVoteList = new ArrayList<>();
		correctVoteList.add(wrongVote);
		
		ArrayList<Vote> wrongVoteList = new ArrayList<>();
		wrongVoteList.add(wrongVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(correctVoteList);
		when(voteMapper.selectVoteOptions(2)).thenReturn(wrongVoteList);
		when(voteMapper.selectVoteOptions(3)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/insert_option")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).insertVoteOption(any());
		
		mockMvc.perform(post("/api/vote/insert_option")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		mockMvc.perform(post("/api/vote/insert_option")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(nullContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).insertVoteOption(any());
	}
	
	@Test
	public void voteInsertOptionMultiTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
		
		ArrayList<Vote> correctVoteList = new ArrayList<>();
		correctVoteList.add(correctVote);
		
		ArrayList<Vote> wrongVoteList = new ArrayList<>();
		wrongVoteList.add(wrongVote);
		
		String correctContent = objectMapper.writeValueAsString(correctVoteList);
		String wrongContent = objectMapper.writeValueAsString(wrongVoteList);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(wrongVoteList);
		when(voteMapper.selectVoteOptions(2)).thenReturn(wrongVoteList);
		
		mockMvc.perform(post("/api/vote/insert_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).insertVoteOptionMulti(any());
		
		mockMvc.perform(post("/api/vote/insert_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/insert_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).insertVoteOptionMulti(any());
	}
	
	@Test
	public void voteDeleteTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", "test");
		Vote wrongVote = new Vote(2, 2, "test2", "test2");
		
		String correctContent = objectMapper.writeValueAsString(correctVote);
		String wrongContent = objectMapper.writeValueAsString(wrongVote);
		
		when(voteMapper.selectVote(1)).thenReturn(correctVote);
		when(voteMapper.selectVote(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).deleteVote(any());
		
		mockMvc.perform(post("/api/vote/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void voteDeleteOptionTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
		
		String correctContent = objectMapper.writeValueAsString(correctVote);
		String wrongContent = objectMapper.writeValueAsString(wrongVote);
		
		when(voteMapper.selectVoteOption(1)).thenReturn(correctVote);
		when(voteMapper.selectVoteOption(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/delete_option")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).deleteVoteOption(any());
		
		mockMvc.perform(post("/api/vote/delete_option")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void voteDeleteOptionMultiTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
		
		ArrayList<Vote> correctVoteList = new ArrayList<>();
		correctVoteList.add(correctVote);
		
		ArrayList<Vote> wrongVoteList = new ArrayList<>();
		wrongVoteList.add(wrongVote);
		
		String correctContent = objectMapper.writeValueAsString(correctVoteList);
		String wrongContent = objectMapper.writeValueAsString(wrongVoteList);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(correctVoteList);
		when(voteMapper.selectVoteOptions(2)).thenReturn(correctVoteList);
		
		mockMvc.perform(post("/api/vote/delete_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).deleteVoteOptionMulti(any());
		
		mockMvc.perform(post("/api/vote/delete_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/delete_option_multi")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).deleteVoteOptionMulti(any());
	}
	
	@Test
	public void voteUpdateTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
	
		String correctContent = objectMapper.writeValueAsString(correctVote);
		String wrongContent = objectMapper.writeValueAsString(wrongVote);
		
		when(voteMapper.selectVote(1)).thenReturn(correctVote);
		when(voteMapper.selectVote(2)).thenReturn(null);
		
		mockMvc.perform(post("/api/vote/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(voteMapper, atLeastOnce()).updateVote(any(), any());
		
		mockMvc.perform(post("/api/vote/update")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}

	@Test
	public void VoteSelectOptionTest() throws Exception
	{
		Vote correctVote = new Vote(1, 1, "test", 0);
		Vote wrongVote = new Vote(2, 2, "test2", 0);
	
		String correctContent = objectMapper.writeValueAsString(correctVote);
		String wrongContent = objectMapper.writeValueAsString(wrongVote);
		
		ArrayList<Vote> voteList = new ArrayList<>();
		voteList.add(correctVote);
		
		when(voteMapper.selectVoteOptions(1)).thenReturn(voteList);
		when(voteMapper.selectVoteOptions(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/vote/select_vote_options")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		mockMvc.perform(get("/api/vote/select_vote_options")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
