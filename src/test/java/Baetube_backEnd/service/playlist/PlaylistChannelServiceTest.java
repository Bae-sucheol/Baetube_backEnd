package Baetube_backEnd.service.playlist;

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
import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistChannelServiceTest
{
	@InjectMocks
	private PlaylistChannelService playlistChannelService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private Channel channel;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		channel = new Channel(1, 1, 0, 0, "test", "test", "test", null);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Playlist> playlistList = new ArrayList<>();
		playlistList.add(any());
		
		when(playlistMapper.selectByChannelId(1)).thenReturn(playlistList);
		
		assertEquals(playlistList, playlistChannelService.select(channel.getChannelId()));
		verify(playlistMapper, atLeastOnce()).selectByChannelId(1);
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectByChannelId(1)).thenReturn(null);
		
		assertEquals(null, playlistChannelService.select(channel.getChannelId()));
		verify(playlistMapper, atLeastOnce()).selectByChannelId(1);
	}
}
