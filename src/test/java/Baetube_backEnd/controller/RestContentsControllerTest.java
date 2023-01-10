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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Baetube_backEnd.dto.Contents;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.service.contents.ContentsDeleteService;

public class RestContentsControllerTest
{
	
	@Spy
	@InjectMocks
	private RestContentsController restContentsController;
	
	@Spy
	@InjectMocks
	private ContentsDeleteService contentsDeleteService;
	
	@Mock
	private ContentsMapper contentsMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		restContentsController = spy(new RestContentsController());
		contentsDeleteService = spy(new ContentsDeleteService());
		
		mockMvc = MockMvcBuilders.standaloneSetup(restContentsController).build();
		MockitoAnnotations.initMocks(this);
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void deleteContentsTest() throws Exception
	{
		Contents correctContents = new Contents(1L, 0);
		Contents wrongContents = new Contents(2L, 0);
		
		String correctContent = objectMapper.writeValueAsString(correctContents);
		String wrongContent = objectMapper.writeValueAsString(wrongContents);
		
		when(contentsMapper.selectByContentsId(1L)).thenReturn(correctContents);
		when(contentsMapper.selectByContentsId(2L)).thenReturn(null);
		
		mockMvc.perform(post("/api/contents/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(contentsMapper, atLeastOnce()).delete(1L, 0);
		
		mockMvc.perform(post("/api/contents/delete")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
		
	}
}
