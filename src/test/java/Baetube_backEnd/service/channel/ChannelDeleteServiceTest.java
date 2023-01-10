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

public class ChannelDeleteServiceTest
{
	@InjectMocks
	private ChannelDeleteService channelDeleteService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	private Channel channel;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
	}
	
	@Test
	public void CorrectTest()
	{
		when(channelMapper.select(1)).thenReturn(channel);
		
		assertEquals(true, channelDeleteService.deleteChannel(1));
		verify(channelMapper, atLeastOnce()).select(1);
		verify(channelMapper, atLeastOnce()).delete(1);
	}
	
	@Test(expected = NullChannelException.class)
	public void wrongTest()
	{
		when(channelMapper.select(1)).thenReturn(null);
		
		assertEquals(true, channelDeleteService.deleteChannel(1));
		verify(channelMapper, atLeastOnce()).select(1);
		verify(channelMapper, atLeastOnce()).delete(1);
	}
}
