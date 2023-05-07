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

public class ChannelInsertServiceTest
{
	@InjectMocks
	private ChannelInsertService channelInsertService;
	
	@Mock
	private ChannelMapper channelMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void CorrectTest()
	{
		Channel channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		
		assertEquals(channel.getChannelId(), channelInsertService.insertChannel(channel));
		verify(channelMapper, atLeastOnce()).insert(channel);
	}

}
