package Baetube_backEnd.service.playlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertServiceTest
{
	@InjectMocks
	private PlaylistInsertService playlistInsertService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Playlist playlist = new Playlist(1, 1, "test", 1, 0, null);
		
		assertTrue(playlistInsertService.insert(playlist) == 1);
		verify(playlistMapper, atLeastOnce()).insert(playlist);
	}
}
