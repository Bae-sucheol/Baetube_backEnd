package Baetube_backEnd.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.History;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.service.history.HistoryDeleteService;

public class RestHistoryControllerTest
{
	@Spy
	@InjectMocks
	private RestHistoryController restHistoryController;
	@Spy
	@InjectMocks
	private HistoryDeleteService historyDeleteService;
	@Mock
	private HistoryMapper historyMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		restHistoryController = spy(new RestHistoryController());
		historyDeleteService = spy(new HistoryDeleteService());
		
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(restHistoryController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void historyDeleteTest() throws Exception
	{
		History correctHistory = new History(1, 1, null);
		History wrongHistory = new History(2, 2, null);
		
		String correctContent = objectMapper.writeValueAsString(correctHistory);
		String wrongContent = objectMapper.writeValueAsString(wrongHistory);
		
		when(historyMapper.select(1, 1)).thenReturn(correctHistory);
		when(historyMapper.select(2, 2)).thenReturn(null);
		
		
		mockMvc.perform(post("/api/history/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(historyMapper, atLeastOnce()).delete(1, 1);
		
		mockMvc.perform(post("/api/history/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
	}
	
	
}
