package Baetube_backEnd.service.channel;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelDeleteServiceTest
{
	@InjectMocks
	private ChannelDeleteService channelDeleteService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Before
	public void setUp()
	{
		channelDeleteService = new ChannelDeleteService();
		channelDeleteService.setChannelMapper(channelMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void CorrectTest()
	{
		Channel channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		
		assertEquals(true, channelDeleteService.deleteChannel(channel.getChannelId()));
		verify(channelMapper, atLeastOnce()).delete(1);
	}
}
