package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoViewServiceTest
{
	@InjectMocks
	private VideoViewService videoViewService;
	
	@Mock
	private VideoMapper videoMapper;
	
	private Video correctVideo = new Video(1, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
			null, 1, "1234", null, null, null);

	@Before
	public void setUp()
	{
		videoViewService = new VideoViewService();
		videoViewService.setVideoMapper(videoMapper);
		MockitoAnnotations.initMocks(this);
		
		when(videoMapper.selectByVideoId(0)).thenReturn(null);
		when(videoMapper.selectByVideoId(1)).thenReturn(correctVideo);
	}
	
	@Test
	public void correctTest()
	{
		Video video = videoViewService.selectVideo(1);
		
		// updateViews가 한번 실행되었다면 통과
		verify(videoMapper, atMost(1)).updateViews(1, 1);
		// 실행되었다면 조회수 1 증가
		video.setViews(video.getViews() + 1);
		
		assertTrue(1 == video.getVideoId());
		assertTrue(101 == video.getViews());
	}
	
	@Test(expected = NullVideoException.class)
	public void nullVideoTest()
	{
		Video video = videoViewService.selectVideo(0);
	}
	
}
