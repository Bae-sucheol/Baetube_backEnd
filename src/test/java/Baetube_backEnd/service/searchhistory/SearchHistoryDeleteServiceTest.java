package Baetube_backEnd.service.searchhistory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.exception.NullSearchHistoryException;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistoryDeleteServiceTest
{
	@InjectMocks
	private SearchHistoryDeleteService searchHistoryDeleteService;
	
	@Mock
	private SearchHistoryMapper searchHistoryMapper;
	
	private SearchHistory searchHistory;
	
	@Before
	public void setUp()
	{
		searchHistoryDeleteService = new SearchHistoryDeleteService();
		searchHistoryDeleteService.setSearchHistoryMapper(searchHistoryMapper);
		
		MockitoAnnotations.initMocks(this);
		
		searchHistory = new SearchHistory(1, "test");
	}
	
	@Test
	public void correctTest()
	{
		when(searchHistoryMapper.select(searchHistory)).thenReturn(searchHistory);
		
		assertEquals(true, searchHistoryDeleteService.delete(searchHistory));
		verify(searchHistoryMapper, atLeastOnce()).select(searchHistory);
	}
	
	@Test(expected = NullSearchHistoryException.class)
	public void wrongTest()
	{
		when(searchHistoryMapper.select(searchHistory)).thenReturn(null);
		
		assertEquals(true, searchHistoryDeleteService.delete(searchHistory));
		verify(searchHistoryMapper, atLeastOnce()).select(searchHistory);
	}
}
