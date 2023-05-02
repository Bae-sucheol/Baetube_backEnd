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

public class VideoSelectServiceTest
{
	@InjectMocks
	private VideoSelectService videoSelectService;
	
	@Mock
	private VideoMapper videoMapper;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctSelectByVideoIdTest()
	{
		Video video = new Video();
		
		when(videoMapper.selectByVideoId(1)).thenReturn(video);
		
		assertEquals(video, videoSelectService.selectByVideoId(1));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongSelectByVideoIdTest()
	{
		Video video = new Video();
		
		when(videoMapper.selectByVideoId(1)).thenReturn(null);
		
		assertEquals(video, videoSelectService.selectByVideoId(1));
	}
	
	@Test
	public void correctSelectByKeywordsTest()
	{
		List<Video> videoList = new ArrayList<Video>();
		videoList.add(any());
		
		when(videoMapper.selectByKeywords("test")).thenReturn(videoList);
		
		assertEquals(videoList, videoSelectService.selectByKeywords("test"));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongSelectByKeywordsTest()
	{
		List<Video> videoList = new ArrayList<Video>();
		videoList.add(any());
		
		when(videoMapper.selectByKeywords("test")).thenReturn(null);
		
		assertEquals(videoList, videoSelectService.selectByKeywords("test"));
	}
}
