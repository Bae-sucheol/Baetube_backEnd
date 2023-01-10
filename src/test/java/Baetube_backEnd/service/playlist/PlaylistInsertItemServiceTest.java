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

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertItemServiceTest
{
	@InjectMocks
	private PlaylistInsertItemService playlistInsertItemService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private PlaylistItem playlistItem;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		playlistItem = new PlaylistItem(1, 1);
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectPlaylistItem(playlistItem)).thenReturn(null);
		
		assertEquals(true, playlistInsertItemService.insertItem(playlistItem));
		verify(playlistMapper, atLeastOnce()).selectPlaylistItem(playlistItem);
	}
	
	@Test(expected = DuplicatePlaylistItemException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectPlaylistItem(playlistItem)).thenReturn(playlistItem);
		
		assertEquals(true, playlistInsertItemService.insertItem(playlistItem));
		verify(playlistMapper, atLeastOnce()).selectPlaylistItem(playlistItem);
	}
}
