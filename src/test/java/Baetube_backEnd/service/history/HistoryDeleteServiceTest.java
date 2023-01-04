package Baetube_backEnd.service.history;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.DeleteHistoryRequest;
import Baetube_backEnd.mapper.ContentsMapper;
import Baetube_backEnd.mapper.HistoryMapper;
import Baetube_backEnd.service.contents.ContentsDeleteService;

public class HistoryDeleteServiceTest
{
	@InjectMocks
	private HistoryDeleteService historyDeleteService;
	@Mock
	private HistoryMapper historyMapper;
	
	@Before
	public void setUp()
	{
		historyDeleteService = new HistoryDeleteService();
		historyDeleteService.setHistoryMapper(historyMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		DeleteHistoryRequest deleteHistoryRequest = new DeleteHistoryRequest(1, 1);
		
		assertEquals(true, historyDeleteService.deleteHistory(deleteHistoryRequest));
		verify(historyMapper, atLeastOnce()).delete(deleteHistoryRequest.getUserId(), deleteHistoryRequest.getVideoId());
	}
	
}
