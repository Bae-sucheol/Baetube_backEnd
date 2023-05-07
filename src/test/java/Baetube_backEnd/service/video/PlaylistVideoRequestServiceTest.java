package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Playlist;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullPlaylistException;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class PlaylistVideoRequestServiceTest
{
	@InjectMocks
	private PlaylistVideoRequestService playlistVideoRequestService;
	
	@Mock
	private VideoMapper videoMapper;
	@Mock
	private PlaylistMapper playlistMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		Playlist playlist = new Playlist(1, 1, "test", 1, 1, "test");
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(playlistMapper.selectByPlaylistId(1)).thenReturn(playlist);
		when(videoMapper.selectPlaylistVideo(1)).thenReturn(videoList);
		
		assertEquals(videoList, playlistVideoRequestService.requestVideo(1));
	}
	
	@Test(expected = NullPlaylistException.class)
	public void wrongTest()
	{
		when(playlistMapper.selectByPlaylistId(0)).thenReturn(null);
		when(videoMapper.selectPlaylistVideo(0)).thenReturn(null);
		
		assertEquals(null, playlistVideoRequestService.requestVideo(1));
	}
}
