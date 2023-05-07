package Baetube_backEnd.service.subscribe;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
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
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeDeleteServiceTest
{
	@InjectMocks
	private SubscribeDeleteService subscribeDeleteService;
	
	@Mock
	private SubscribeMapper subscribeMapper;
	
	@Mock
	private ChannelMapper channelMapper;
	
	private List<Subscribers> subscribers;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		subscribers = new ArrayList<Subscribers>();
		subscribers.add(new Subscribers(1, 2));
	}
	
	@Test
	public void correctTest()
	{
		when(subscribeMapper.selectSubscribersList(any())).thenReturn(subscribers);
		
		assertEquals(true, subscribeDeleteService.delete(subscribers , 1));
		verify(subscribeMapper, atLeastOnce()).unSubscribe(any());
		verify(channelMapper, atLeastOnce()).updateSubscribes(any(), any());
	}
	
	@Test(expected = NullSubscriberException.class)
	public void wrongTest()
	{
		when(subscribeMapper.selectSubscribersList(any())).thenReturn(null);
		
		assertEquals(true, subscribeDeleteService.delete(subscribers, 1));
		verify(subscribeMapper, atLeastOnce()).unSubscribe(any());
		verify(channelMapper, atLeastOnce()).updateSubscribes(any(), any());
	}
}
