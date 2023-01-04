package Baetube_backEnd.service.channel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelVisitServiceTest
{
	@InjectMocks
	private ChannelVisitService channelVisitService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Before
	public void setUp()
	{
		channelVisitService = new ChannelVisitService();
		channelVisitService.setChannelMapper(channelMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Channel channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		
		when(channelMapper.select(1)).thenReturn(channel);
		
		assertEquals(channel, channelVisitService.selectChannel(1));
		verify(channelMapper, atLeastOnce()).select(1);
	}
	
	@Test(expected = NullChannelException.class)
	public void wrongTest()
	{
		when(channelMapper.select(1)).thenReturn(null);
		
		assertEquals(null, channelVisitService.selectChannel(1));
	}
}
