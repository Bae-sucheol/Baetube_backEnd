package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoUpdateServiceTest
{
	@InjectMocks
	private VideoUpdateService videoUpdateService;
	
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
		Video correctVideo = new Video(1, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
		
		when(videoMapper.selectByVideoId(1)).thenReturn(correctVideo);
		
		assertEquals(true, videoUpdateService.update(correctVideo));
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongTest()
	{
		Video newVideo = new Video(0, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
		
		when(videoMapper.selectByVideoId(0)).thenReturn(null);
		
		videoUpdateService.update(newVideo);
	}
	

}
