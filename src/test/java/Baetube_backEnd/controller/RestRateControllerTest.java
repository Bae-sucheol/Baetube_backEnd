package Baetube_backEnd.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
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

import Baetube_backEnd.dto.Rate;
import Baetube_backEnd.mapper.RateMapper;
import Baetube_backEnd.service.rate.RateService;

public class RestRateControllerTest
{
	@Spy
	@InjectMocks
	private RestRateController restRateController;
	@Spy
	@InjectMocks
	private RateService rateService;
	
	@Mock
	private RateMapper rateMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp()
	{
		restRateController = spy(new RestRateController());
		rateService = spy(new RateService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restRateController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void rateTest() throws Exception
	{
		Rate correctRate = new Rate(2L, 2, 1, 1);
		Rate wrongRate = new Rate(1L, 1, 1, null);
		
		
		String correctContent = objectMapper.writeValueAsString(correctRate);
		String wrongContent = objectMapper.writeValueAsString(wrongRate);
		
		mockMvc.perform(post("/api/rate/rate_contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(rateMapper, atLeastOnce()).insert(any());
		
		mockMvc.perform(post("/api/rate/rate_contents")
				.contentType(MediaType.APPLICATION_JSON)
				.content(wrongContent))
		.andExpect(status().isConflict())
		.andDo(print());
	}
}
