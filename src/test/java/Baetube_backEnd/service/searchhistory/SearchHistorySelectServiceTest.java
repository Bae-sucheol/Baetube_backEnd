package Baetube_backEnd.service.searchhistory;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import Baetube_backEnd.dto.SearchHistory;
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
		searchHistorySelectService = new SearchHistorySelectService();
		searchHistorySelectService.setSearchHistoryMapper(searchHistoryMapper);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void correctTest()
	{
		ArrayList<SearchHistory> searchHistoryList = new ArrayList<>();
		searchHistoryList.add(any());
		
		when(searchHistoryMapper.selectAll(1)).thenReturn(searchHistoryList);
		when(searchHistoryMapper.selectAll(0)).thenReturn(null);
		
		assertEquals(searchHistoryList, searchHistorySelectService.select(1));
		verify(searchHistoryMapper, atLeastOnce()).selectAll(1);
		
		assertEquals(null, searchHistorySelectService.select(0));
		verify(searchHistoryMapper, atLeastOnce()).selectAll(0);
	}
}
