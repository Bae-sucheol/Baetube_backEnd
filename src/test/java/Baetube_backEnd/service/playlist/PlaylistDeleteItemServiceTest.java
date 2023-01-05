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
import Baetube_backEnd.exception.NullPlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteItemServiceTest
{
	@InjectMocks
	private PlaylistDeleteItemService playlistDeleteItemService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private PlaylistItem playlistItem;
	
	@Before
	public void setUP()
	{
		playlistDeleteItemService = new PlaylistDeleteItemService();
		playlistDeleteItemService.setPlaylistMapper(playlistMapper);
		
		MockitoAnnotations.initMocks(this);
		
		playlistItem = new PlaylistItem(1, 1);
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectPlaylistItem(playlistItem)).thenReturn(playlistItem);
		
		assertEquals(true, playlistDeleteItemService.deleteItem(playlistItem));
		verify(playlistMapper, atLeastOnce()).deleteItem(playlistItem);
	}
	
	@Test(expected = NullPlaylistItemException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectPlaylistItem(playlistItem)).thenReturn(null);
		
		assertEquals(true, playlistDeleteItemService.deleteItem(playlistItem));
		verify(playlistMapper, atLeastOnce()).deleteItem(playlistItem);
	}
}
