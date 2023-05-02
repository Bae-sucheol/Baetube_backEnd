package Baetube_backEnd.service.history;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.History;
import Baetube_backEnd.exception.NullHistoryException;
import Baetube_backEnd.mapper.HistoryMapper;

public class HistoryDeleteServiceTest
{
	@InjectMocks
	private HistoryDeleteService historyDeleteService;
	@Mock
	private HistoryMapper historyMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		History history = new History(1, 1);
		
		when(historyMapper.select(1, 1)).thenReturn(history);
		
		assertEquals(true, historyDeleteService.deleteHistory(history));
		
		verify(historyMapper, atLeastOnce()).delete(history.getUserId(), history.getVideoId());
	}
	
	@Test(expected = NullHistoryException.class)
	public void wrongTest()
	{
		History history = new History(1, 1);
		
		when(historyMapper.select(1, 1)).thenReturn(null);
		
		assertEquals(true, historyDeleteService.deleteHistory(history));
		
		verify(historyMapper, atLeastOnce()).delete(history.getUserId(), history.getVideoId());
	}
	
}
