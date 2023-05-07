package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class RelatedVideoRequestServiceTest
{
	@InjectMocks
	private RelatedVideoRequestService relatedVideoRequestService;
	
	@Mock
	private VideoMapper videoMapper;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		//List<Video> videoList = videoMapper.selectRelatedVideo(videoId);
		List<Video> videoList = new ArrayList<Video>();
		videoList.add(any());
		
		when(videoMapper.selectRelatedVideo(1)).thenReturn(videoList);
		
		assertEquals(videoList, relatedVideoRequestService.requestVideo(1));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongTest()
	{
		//List<Video> videoList = videoMapper.selectRelatedVideo(videoId);
		List<Video> videoList = new ArrayList<Video>();
		videoList.add(any());
		
		when(videoMapper.selectRelatedVideo(1)).thenReturn(null);
		
		assertEquals(videoList, relatedVideoRequestService.requestVideo(1));
	}
}
