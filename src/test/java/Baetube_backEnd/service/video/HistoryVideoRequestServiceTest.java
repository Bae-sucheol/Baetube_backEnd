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

import Baetube_backEnd.dto.Video;
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
		historyVideoRequestService = new HistoryVideoRequestService();
		historyVideoRequestService.setVideoMapper(videoMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectHistoryVideo(1)).thenReturn(videoList);
		when(videoMapper.selectHistoryVideo(0)).thenReturn(null);
		
		assertEquals(videoList, historyVideoRequestService.requestVideo(1));
		assertEquals(null, historyVideoRequestService.requestVideo(0));
	}

}
