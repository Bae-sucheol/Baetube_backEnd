package Baetube_backEnd.service.notification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Notification;
import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationDeleteServiceTest
{
	@InjectMocks
	private NotificationDeleteService notificationDeleteService;
	
	@Mock
	private NotificationMapper notificationMapper;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void deleteTest()
	{
		Notification notification = new Notification(1, 1L);
		
		when(notificationMapper.select(1, 1L)).thenReturn(notification);
		
		assertEquals(true, notificationDeleteService.delete(notification));
		verify(notificationMapper, atLeastOnce()).delete(1, 1L);
	}
}
