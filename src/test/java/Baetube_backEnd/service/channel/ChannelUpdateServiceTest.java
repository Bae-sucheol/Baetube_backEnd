package Baetube_backEnd.service.channel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class ChannelUpdateServiceTest
{
	@InjectMocks
	private ChannelUpdateService channelUpdateService;
	
	@Mock
	private ChannelMapper channelMapper;

	@Mock
	private FileUploadService fileUploadService;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest() throws IOException
	{
		Channel oldChannel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		Channel newChannel = new Channel(1, 1, 0, 0, "test2", "test2", "test2", null);
		
		oldChannel.setProfile("test");
		newChannel.setProfile("test2");
		
		when(channelMapper.select(1)).thenReturn(oldChannel);
		
		HashMap<String, String> result = channelUpdateService.updateChannel(newChannel);
		
		assertTrue(result.containsKey("arts"));
		assertTrue(result.containsKey("profile"));
		verify(channelMapper, atLeastOnce()).update(oldChannel, newChannel);
	}
	
	@Test(expected = NullChannelException.class)
	public void wrongTest() throws IOException
	{
		Channel channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
		
		when(channelMapper.select(1)).thenReturn(null);
		
		assertEquals(true, channelUpdateService.updateChannel(channel));
	}
	
}

