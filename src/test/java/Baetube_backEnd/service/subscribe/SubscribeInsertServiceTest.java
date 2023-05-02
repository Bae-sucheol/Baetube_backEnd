package Baetube_backEnd.service.subscribe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.DuplicateSubscriberException;
import Baetube_backEnd.mapper.SubscribeMapper;
import Baetube_backEnd.service.subscribe.SubscribeInsertService;

public class SubscribeInsertServiceTest
{
	@InjectMocks
	private SubscribeInsertService subscribeInsertService;
	
	@Mock
	private SubscribeMapper subscribeMapper;
	
	private Subscribers subscribers;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		subscribers = new Subscribers(1, 2);
	}
	
	@Test
	public void correctTest()
	{
		when(subscribeMapper.select(subscribers.getChannelId(), subscribers.getSubscriberId())).thenReturn(null);
		
		assertEquals(true, subscribeInsertService.insert(subscribers));
		
		verify(subscribeMapper, atLeastOnce()).subscribe(any());
	}
	
	@Test(expected = DuplicateSubscriberException.class)
	public void wrongTest()
	{
		when(subscribeMapper.select(subscribers.getChannelId(), subscribers.getSubscriberId())).thenReturn(subscribers);
		
		assertEquals(true, subscribeInsertService.insert(subscribers));
		
		verify(subscribeMapper, atLeastOnce()).subscribe(any());
	}
}
