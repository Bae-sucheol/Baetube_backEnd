package Baetube_backEnd.service.channel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelSubscribersServiceTest
{
	@InjectMocks
	private ChannelSubscribersService channelSubscribersService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Before
	public void setUp()
	{
		channelSubscribersService = new ChannelSubscribersService();
		channelSubscribersService.setChannelMapper(channelMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Channel> channelList = new ArrayList<>();
		channelList.add(any());
		
		when(channelMapper.selectSubscribers(0)).thenReturn(null);
		when(channelMapper.selectSubscribers(1)).thenReturn(channelList);
		
		assertEquals(channelList, channelSubscribersService.selectSubscribers(1));
		verify(channelMapper, atLeastOnce()).selectSubscribers(1);
		
		assertEquals(null, channelSubscribersService.selectSubscribers(0));
		verify(channelMapper, atLeastOnce()).selectSubscribers(0);
	}
}
