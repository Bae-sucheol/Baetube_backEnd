package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoInsertServiceTest
{
	@InjectMocks
	private VideoInsertService videoInsertService;
	
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
		Video video = new Video(1, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
		
		assertEquals(false, videoInsertService.insert(video).isEmpty());
		verify(videoMapper, atLeastOnce()).insert(any());
	}
}
