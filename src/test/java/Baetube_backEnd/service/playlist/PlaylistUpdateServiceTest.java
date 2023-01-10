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

public class PlaylistUpdateServiceTest
{
	@InjectMocks
	private PlaylistUpdateService playlistUpdateService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private Playlist oldPlaylist;
	private Playlist newPlaylist;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		oldPlaylist = new Playlist(1, 1, "test", 1, 0, null);
		newPlaylist = new Playlist(1, 1, "test2", 1, 0, null);
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(oldPlaylist);
		
		assertEquals(true, playlistUpdateService.update(newPlaylist));
		verify(playlistMapper, atLeastOnce()).selectByPlaylistId(1);
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(null);
		
		assertEquals(true, playlistUpdateService.update(newPlaylist));
		verify(playlistMapper, atLeastOnce()).selectByPlaylistId(1);
	}
}
