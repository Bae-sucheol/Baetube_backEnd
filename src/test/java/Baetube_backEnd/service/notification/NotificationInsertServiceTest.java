package Baetube_backEnd.service.notification;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationInsertServiceTest
{
	@InjectMocks
	private NotificationInsertService notificationInsertService;
	
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
		List<Integer> subscribersUserId = new ArrayList<>();
		Long contentsId = 1L;
		
		assertEquals(true, notificationInsertService.insert(subscribersUserId, contentsId));

		verify(notificationMapper, atLeastOnce()).insert(any(), any());
	}
}
