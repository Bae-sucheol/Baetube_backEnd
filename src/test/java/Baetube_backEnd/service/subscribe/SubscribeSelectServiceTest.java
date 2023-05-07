package Baetube_backEnd.service.subscribe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeSelectServiceTest
{
	@InjectMocks
	private SubscribeSelectService subscribeSelectService;
	
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
	public void correctSelectTest()
	{
		when(subscribeMapper.select(subscribers.getChannelId(), subscribers.getSubscriberId())).thenReturn(subscribers);
		
		assertEquals(subscribers, subscribeSelectService.select(subscribers.getChannelId(), subscribers.getSubscriberId()));
	}
	
	@Test(expected = NullSubscriberException.class)
	public void wrongSelectTest()
	{
		when(subscribeMapper.select(subscribers.getChannelId(), subscribers.getSubscriberId())).thenReturn(null);
		
		assertEquals(subscribers, subscribeSelectService.select(subscribers.getChannelId(), subscribers.getSubscriberId()));
	}
	
	@Test
	public void correctSelectChannelSubscribersTokenTest()
	{
		List<String> subscribersTokens = new ArrayList<>();
		
		when(subscribeMapper.selectChannelSubscribers(1)).thenReturn(subscribersTokens);
		
		assertEquals(subscribersTokens, subscribeSelectService.selectChannelSubscribersToken(1));
	}
	
	@Test
	public void correctSelectChannelSubscribersUserIdTest()
	{
		List<Integer> subscribersUserId = new ArrayList<>();
		
		when(subscribeMapper.selectChannelSubscribersUserId(1)).thenReturn(subscribersUserId);
		
		assertEquals(subscribersUserId, subscribeSelectService.selectChannelSubscribersUserId(1));
	}
}
