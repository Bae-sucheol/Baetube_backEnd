package Baetube_backEnd.service.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistSelectServiceTest
{
	@InjectMocks
	private PlaylistSelectService playlistSelectService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private Playlist playlist;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		playlist = new Playlist();
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(playlist);
		
		assertEquals(playlist, playlistSelectService.selectPlaylistData(1));
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(null);
		
		assertEquals(playlist, playlistSelectService.selectPlaylistData(1));
	}
}
