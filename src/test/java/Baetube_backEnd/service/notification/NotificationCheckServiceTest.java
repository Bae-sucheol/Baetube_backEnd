package Baetube_backEnd.service.notification;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationCheckServiceTest
{
	@InjectMocks
	private NotificationCheckService notificationCheckService;
	
	@Mock
	private NotificationMapper notificationMapper;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		
	}
}
