package Baetube_backEnd.service.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteServiceTest
{
	@InjectMocks
	private PlaylistDeleteService playlistDeleteService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private Playlist playlist; 
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		playlist = new Playlist(1, 1, "test", 1, 0, null);
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(playlist);
		
		assertEquals(true, playlistDeleteService.delete(playlist));
		verify(playlistMapper, atLeastOnce()).delete(playlist);
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectByChannelId(1)).thenReturn(null);
		
		assertEquals(null, playlistDeleteService.delete(playlist));
		verify(playlistMapper, atLeastOnce()).selectByChannelId(1);
	}
}
