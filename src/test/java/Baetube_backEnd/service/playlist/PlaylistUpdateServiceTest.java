package Baetube_backEnd.service.playlist;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class PlaylistUpdateServiceTest
{
	@InjectMocks
	private PlaylistUpdateService playlistUpdateService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	@Mock
	private FileUploadService fileUploadService;
	@Mock
	private VideoMapper videoMapper;
	
	private Playlist oldPlaylist;
	private Playlist newPlaylist;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		oldPlaylist = new Playlist(1, 1, "test", 1, 0, "test");
		newPlaylist = new Playlist(1, 1, "test2", 1, 0, "test2");
	}
	
	@Test
	public void correctTest() throws IOException
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(oldPlaylist);
		when(videoMapper.selectByThumbnail("test")).thenReturn(null);
		
		assertEquals(false, playlistUpdateService.update(newPlaylist).isEmpty());
		verify(fileUploadService, atLeastOnce()).deleteImage(any(), any(), any());
	}
	
	@Test
	public void correctVideoNotNullTest() throws IOException
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(oldPlaylist);
		when(videoMapper.selectByThumbnail("test")).thenReturn(any());
		
		assertEquals(false, playlistUpdateService.update(newPlaylist).isEmpty());
	}
	
	@Test
	public void correctSameThumbnailTest() throws IOException
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(newPlaylist);
		
		assertEquals(true, playlistUpdateService.update(newPlaylist).isEmpty());
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest() throws IOException
	{
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(null);
		
		assertEquals(true, playlistUpdateService.update(newPlaylist));
		verify(playlistMapper, atLeastOnce()).selectByPlaylistId(1);
	}
}
