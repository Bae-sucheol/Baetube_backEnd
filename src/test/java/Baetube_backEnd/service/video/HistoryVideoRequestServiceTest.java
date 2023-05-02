package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.PlaylistMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class HistoryVideoRequestServiceTest
{
	@InjectMocks
	private HistoryVideoRequestService historyVideoRequestService;
	
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
	public void correctRequestVideoTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectHistoryVideo(1)).thenReturn(videoList);
		
		assertEquals(videoList, historyVideoRequestService.requestVideo(1));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongRequestVideoTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectHistoryVideo(1)).thenReturn(null);
		
		assertEquals(videoList, historyVideoRequestService.requestVideo(1));
	}
	
	@Test
	public void correctRequestVideoKeywordsTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectHistoryVideoKeywords(1, eq("test"))).thenReturn(videoList);
		
		assertEquals(videoList, historyVideoRequestService.requestVideoKeywords(1, "test"));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongRequestVideoKeywordsTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectHistoryVideoKeywords(1, eq("test"))).thenReturn(null);
		
		assertEquals(videoList, historyVideoRequestService.requestVideoKeywords(1, "test"));
	}

}
