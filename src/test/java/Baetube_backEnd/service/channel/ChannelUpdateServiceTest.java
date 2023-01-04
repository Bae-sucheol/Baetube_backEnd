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

public class ChannelUpdateServiceTest
{
	@InjectMocks
	private ChannelUpdateService channelUpdateService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Before
	public void setUp()
	{
		channelUpdateService = new ChannelUpdateService();
		channelUpdateService.setChannelMapper(channelMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Channel oldChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		Channel newChannel = new Channel(1, 1, 0, 0, "test2", "test2", "test2", null);
		
		when(channelMapper.select(1)).thenReturn(oldChannel);
		
		assertEquals(true, channelUpdateService.updateChannel(newChannel));
		verify(channelMapper, atLeastOnce()).update(oldChannel, newChannel);
	}
	
	@Test(expected = NullChannelException.class)
	public void wrongTest()
	{
		Channel channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		
		when(channelMapper.select(1)).thenReturn(null);
		
		assertEquals(true, channelUpdateService.updateChannel(channel));
	}
	
}

