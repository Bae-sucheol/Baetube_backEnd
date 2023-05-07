package Baetube_backEnd.service.searchhistory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistoryInsertServiceTest
{
	@InjectMocks
	private SearchHistoryInsertService searchHistoryInsertService;
	
	@Mock
	private SearchHistoryMapper searchHistoryMapper;
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		SearchHistory searchHistory = new SearchHistory(1, "test");
		
		assertEquals(true, searchHistoryInsertService.insert(searchHistory));
		verify(searchHistoryMapper, atLeastOnce()).insert(searchHistory);
	}
	
	
}
