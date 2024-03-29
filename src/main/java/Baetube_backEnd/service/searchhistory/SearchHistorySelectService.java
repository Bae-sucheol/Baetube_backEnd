package Baetube_backEnd.service.searchhistory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.exception.NullSearchHistoryException;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistorySelectService
{
	@Autowired
	private SearchHistoryMapper searchHistoryMapper;
	
	public List<SearchHistory> select(Integer request) throws NullSearchHistoryException
	{
		List<SearchHistory> searchHistoryList = searchHistoryMapper.selectAll(request);
		
		if(searchHistoryList == null)
		{
			throw new NullSearchHistoryException();
		}
		
		return searchHistoryList;
	}
}
