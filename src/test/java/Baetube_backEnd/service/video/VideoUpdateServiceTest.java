package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.Video;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.VideoMapper;
import Baetube_backEnd.service.file.FileUploadService;

public class VideoUpdateServiceTest
{
	@InjectMocks
	private VideoUpdateService videoUpdateService;
	
	@Mock
	private VideoMapper videoMapper;
	
	@Mock
	private FileUploadService fileUploadService;
	
	private Video video;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	
		video = new Video(1, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
	}
	
	@Test
	public void correctTest() throws IOException
	{
		Video videoNewThumbnail = new Video(1, 1L, 1, "1234", 1, "1234", "12345", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
		videoNewThumbnail.setThumbnail("12345");
		
		when(videoMapper.selectByVideoId(1)).thenReturn(videoNewThumbnail);
		
		assertEquals(false, videoUpdateService.update(video).isEmpty());
		verify(fileUploadService).deleteImage(any(), any(), any());
		verify(videoMapper).update(any(), any());
	}
	
	@Test
	public void correctSameThumbnailTest() throws IOException
	{
		when(videoMapper.selectByVideoId(1)).thenReturn(video);
		
		assertEquals(true, videoUpdateService.update(video).isEmpty());
		verify(videoMapper).update(any(), any());
	}
	
	@Test(expected = NullVideoException.class)
	public void wrongTest() throws IOException
	{
		when(videoMapper.selectByVideoId(1)).thenReturn(null);
		
		videoUpdateService.update(video);
	}
	

}
