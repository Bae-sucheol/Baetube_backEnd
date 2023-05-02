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
import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.DuplicatePlaylistItemException;
import Baetube_backEnd.mapper.PlaylistMapper;

public class PlaylistInsertLikeVideoServiceTest
{
	@InjectMocks
	private PlaylistInsertLikeVideoService playlistInsertLikeVideoService;
	
	@Mock
	private PlaylistMapper playlistMapper;
	
	private PlaylistItem playlistItem;
	
	private Video video;
	
	@Before
	public void setUP()
	{
		MockitoAnnotations.initMocks(this);
		
		playlistItem = new PlaylistItem(1, 1);
		
		video = new Video();
		video.setChannelId(1);
		video.setVideoId(1);
	}
	
	@Test
	public void correctTest()
	{
		when(playlistMapper.selectLikePlaylist(video.getChannelId(), video.getVideoId())).thenReturn(playlistItem);
		
		playlistInsertLikeVideoService.insertLikeVideo(video);
		
		verify(playlistMapper, atLeastOnce()).deleteItem(any());
	}
	
	@Test
	public void correctDupleTest()
	{
		when(playlistMapper.selectLikePlaylist(video.getChannelId(), video.getVideoId())).thenReturn(null);
		
		playlistInsertLikeVideoService.insertLikeVideo(video);
		
		verify(playlistMapper, atLeastOnce()).insertLikeVideo(any(), any());
	}
}
