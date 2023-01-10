package Baetube_backEnd.service.video;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
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
import Baetube_backEnd.dto.VideoViewRequest;
import Baetube_backEnd.exception.NullVideoException;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.mapper.VideoMapper;

public class VideoViewServiceTest
{
	@InjectMocks
	private VideoViewService videoViewService;
	
	@Mock
	private VideoMapper videoMapper;
	@Mock
	private HistoryMapper historyMapper;
	
	private Video correctVideo;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		
		correctVideo = new Video(1, 1L, 1, "1234", 1, "1234", "1234", "1234", "1234", 1, 100, 0, 0, 0, 
				null, 1, "1234", null, null, null);
		
		when(videoMapper.selectByVideoId(0)).thenReturn(null);
		when(videoMapper.selectByVideoId(1)).thenReturn(correctVideo);
	}
	
	@Test
	public void correctTest()
	{
		VideoViewRequest videoViewRequest = new VideoViewRequest(1, 1, 1);
		Video video = videoViewService.selectVideo(videoViewRequest);
		
		// updateViews�� �ѹ� ����Ǿ��ٸ� ���
		verify(videoMapper, atLeastOnce()).updateViews(1, 1);
		// ����Ǿ��ٸ� ��ȸ�� 1 ����
		video.setViews(video.getViews() + 1);
		
		verify(historyMapper, atLeastOnce()).insert(1, 1);
		
		assertTrue(1 == video.getVideoId());
		assertTrue(101 == video.getViews());
	}
	
	@Test(expected = NullVideoException.class)
	public void nullVideoTest()
	{
		VideoViewRequest videoViewRequest = new VideoViewRequest(0, 1, 1);
		
		Video video = videoViewService.selectVideo(videoViewRequest);
		
		verify(videoMapper, atLeastOnce()).updateViews(1, 1);
		verify(historyMapper, atLeastOnce()).insert(1, 1);
	}
	
}
