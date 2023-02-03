package Baetube_backEnd.controller;

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

import Baetube_backEnd.dto.Notification;
import Baetube_backEnd.mapper.NotificationMapper;
import Baetube_backEnd.service.notification.NotificationDeleteService;

public class RestNotificationControllerTest
{
	@Spy
	@InjectMocks
	private RestNotificationController restNotificationController;
	@Spy
	@InjectMocks
	private NotificationDeleteService notificationDeleteService;
	
	@Mock
	private NotificationMapper notificationMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void setUp() throws JsonProcessingException
	{
		restNotificationController = spy(new RestNotificationController());
		notificationDeleteService = spy(new NotificationDeleteService());
		
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(restNotificationController).build();
		objectMapper = new ObjectMapper();
	}
	
	@Test
	public void deleteTest() throws Exception
	{
		Notification notification = new Notification(1, 1L);
		String correctContent = objectMapper.writeValueAsString(notification);
		
		when(notificationMapper.select(1, 1L)).thenReturn(notification);
		
		mockMvc.perform(post("/api/notification/delete")
				.contentType(MediaType.APPLICATION_JSON)
				.content(correctContent))
		.andExpect(status().isOk())
		.andDo(print());
		
		verify(notificationMapper, atLeastOnce()).delete(1, 1L);
	}
}
