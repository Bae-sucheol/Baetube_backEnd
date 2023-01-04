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

public class MainVideoRequestServiceTest
{
	@InjectMocks
	private MainVideoRequestService mainVideoRequestService;
	
	@Mock
	private VideoMapper videoMapper;
	@Mock
	private PlaylistMapper playlistMapper;
	
	@Before
	public void setUp()
	{
		mainVideoRequestService = new MainVideoRequestService();
		mainVideoRequestService.setVideoMapper(videoMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<Video> videoList = new ArrayList<>();
		videoList.add(any());
		
		when(videoMapper.selectMainVideo(1)).thenReturn(videoList);
		when(videoMapper.selectMainVideo(0)).thenReturn(null);
		
		assertEquals(videoList, mainVideoRequestService.requestVideo(1));
		assertEquals(null, mainVideoRequestService.requestVideo(0));
	}

}
