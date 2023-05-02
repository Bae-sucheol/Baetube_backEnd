package Baetube_backEnd.service.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertItemMultiServiceTest
{
	@InjectMocks
	private PlaylistInsertItemMultiService playlistInsertItemMultiService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private List<PlaylistItem> playlistItem;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		playlistItem = new ArrayList<PlaylistItem>();
		playlistItem.add(new PlaylistItem(1, 1));
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectPlaylistItemCount(playlistItem)).thenReturn(0);
		
		assertEquals(true, playlistInsertItemMultiService.insertItemMulti(playlistItem));
		
		verify(playlistMapper, atLeastOnce()).insertItem(any());
		verify(playlistMapper, atLeastOnce()).updateVideoCountMulti(any(), any());
	}
	
	@Test(expected = DuplicatePlaylistItemException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectPlaylistItemCount(playlistItem)).thenReturn(9999);
		
		assertEquals(true, playlistInsertItemMultiService.insertItemMulti(playlistItem));
		
		verify(playlistMapper, atLeastOnce()).insertItem(any());
		verify(playlistMapper, atLeastOnce()).updateVideoCount(any(), any());
	}
}
