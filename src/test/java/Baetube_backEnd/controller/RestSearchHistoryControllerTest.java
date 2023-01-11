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

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.mapper.SearchHistoryMapper;
import Baetube_backEnd.service.searchhistory.SearchHistoryDeleteService;
import Baetube_backEnd.service.searchhistory.SearchHistoryInsertService;
import Baetube_backEnd.service.searchhistory.SearchHistorySelectService;

public class RestSearchHistoryControllerTest
{
	@Spy
	@InjectMocks
	private RestSearchHistoryController restSearchHistoryController;
	@Spy
	@InjectMocks
	private SearchHistoryDeleteService searchHistoryDeleteService;
	@Spy
	@InjectMocks
	private SearchHistoryInsertService searchHistoryInsertService;
	@Spy
	@InjectMocks
	private SearchHistorySelectService searchHistorySelectService;
	
	@Mock
	private SearchHistoryMapper searchHistoryMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	private SearchHistory correctSearchHistory;
	private SearchHistory wrongSearchHistory;
	
	private String correctContent;
	private String wrongContent;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		restSearchHistoryController = spy(new RestSearchHistoryController());
		searchHistoryDeleteService = spy(new SearchHistoryDeleteService());
		searchHistoryInsertService = spy(new SearchHistoryInsertService());
		searchHistorySelectService = spy(new SearchHistorySelectService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restSearchHistoryController).build();
		objectMapper = new ObjectMapper();
		
		correctSearchHistory = new SearchHistory(1, "test");
		wrongSearchHistory = new SearchHistory(2, "test");
		correctContent = objectMapper.writeValueAsString(correctSearchHistory);
		wrongContent = objectMapper.writeValueAsString(wrongSearchHistory);
	}
	
	@Test
	public void searchHistoryDeleteTest() throws Exception
	{
		when(searchHistoryMapper.select(any())).thenReturn(correctSearchHistory);
		
		mockMvc.perform(post("/api/search_history/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(searchHistoryMapper, atLeastOnce()).delete(any());
		when(searchHistoryMapper.select(any())).thenReturn(null);
		
		mockMvc.perform(post("/api/search_history/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
	
	@Test
	public void searchHistoryInsertTest() throws Exception
	{	
		mockMvc.perform(post("/api/search_history/insert")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(searchHistoryMapper, atLeastOnce()).insert(any());

	}
	
	@Test
	public void searchHistorySelectTest() throws Exception
	{
		ArrayList<SearchHistory> searchHistoryList = new ArrayList<>();
		searchHistoryList.add(correctSearchHistory);
		
		when(searchHistoryMapper.selectAll(1)).thenReturn(searchHistoryList);
		
		mockMvc.perform(get("/api/search_history/select")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		when(searchHistoryMapper.selectAll(2)).thenReturn(null);
		
		mockMvc.perform(get("/api/search_history/select")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
