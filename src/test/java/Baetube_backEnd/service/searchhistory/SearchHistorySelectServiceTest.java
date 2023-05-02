package Baetube_backEnd.service.searchhistory;


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

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.exception.NullSearchHistoryException;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistorySelectServiceTest
{
	@InjectMocks
	private SearchHistorySelectService searchHistorySelectService;
	
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
		List<SearchHistory> searchHistoryList = new ArrayList<SearchHistory>();
		
		when(searchHistoryMapper.selectAll(1)).thenReturn(searchHistoryList);
		
		assertEquals(searchHistoryList, searchHistorySelectService.select(1));
	}
	
	@Test(expected = NullSearchHistoryException.class)
	public void wrongTest()
	{
		when(searchHistoryMapper.selectAll(1)).thenReturn(null);
		
		assertEquals(any(), searchHistorySelectService.select(1));
	}
}
