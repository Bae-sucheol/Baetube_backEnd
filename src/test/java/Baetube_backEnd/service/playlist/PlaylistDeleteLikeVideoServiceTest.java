package Baetube_backEnd.service.playlist;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.PlaylistItem;
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistDeleteLikeVideoServiceTest
{
	@InjectMocks
	private PlaylistDeleteLikeVideoService playlistDeleteLikeVideoService;
	
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
		when(playlistMapper.selectLikePlaylist(1, 1)).thenReturn(new PlaylistItem());
		
		Video video = new Video();
		video.setChannelId(1);
		video.setVideoId(1);
		
		playlistDeleteLikeVideoService.deleteLikeVideo(video);
		
		verify(playlistMapper, atLeastOnce()).deleteItem(any());
	}
	
}
