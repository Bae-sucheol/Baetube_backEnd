package Baetube_backEnd.service.searchhistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.exception.NullSearchHistoryException;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistoryDeleteService
{
	@Autowired
	private SearchHistoryMapper searchHistoryMapper;

	public void setSearchHistoryMapper(SearchHistoryMapper searchHistoryMapper)
	{
		this.searchHistoryMapper = searchHistoryMapper;
	}
	
	@Transactional
	public boolean delete(SearchHistory request)
	{
		SearchHistory searchHistory = searchHistoryMapper.select(request);
		
		if(searchHistory == null)
		{
			throw new NullSearchHistoryException();
		}
		
		searchHistoryMapper.delete(request);
		
		return true;
	}
}
