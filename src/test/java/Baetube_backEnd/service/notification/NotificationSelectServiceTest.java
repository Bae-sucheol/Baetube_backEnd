package Baetube_backEnd.service.notification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.exception.NullNotificationException;
import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationSelectServiceTest
{
	@InjectMocks
	private NotificationSelectService notificationSelectService;
	
	@Mock
	private NotificationMapper notificationMapper;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctSelectVideoNotificationsTest()
	{
		List<HashMap<String, String>> notifications = new ArrayList<>();
		notifications.add(any());
		
		when(notificationMapper.selectVideoNotifications(1)).thenReturn(notifications);
		
		assertEquals(notifications, notificationSelectService.selectVideoNotifications(1));
	}
	
	@Test
	public void correctSelectCommunityNotificationsTest()
	{
		List<HashMap<String, String>> notifications = new ArrayList<>();
		notifications.add(any());
		
		when(notificationMapper.selectCommunityNotifications(1)).thenReturn(notifications);
		
		assertEquals(notifications, notificationSelectService.selectCommunityNotifications(1));
	}
	
	@Test(expected = NullNotificationException.class)
	public void wrongSelectVideoNotificationsTest()
	{
		List<HashMap<String, String>> notifications = new ArrayList<>();
		
		when(notificationMapper.selectVideoNotifications(1)).thenReturn(notifications);
		
		assertEquals(notifications, notificationSelectService.selectVideoNotifications(1));
	}
	
	@Test(expected = NullNotificationException.class)
	public void wrongSelectCommunityNotificationsTest()
	{
		List<HashMap<String, String>> notifications = new ArrayList<>();
		
		when(notificationMapper.selectCommunityNotifications(1)).thenReturn(notifications);
		
		assertEquals(notifications, notificationSelectService.selectCommunityNotifications(1));
	}
}
