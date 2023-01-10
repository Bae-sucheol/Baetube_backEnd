package Baetube_backEnd.service.searchhistory;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.SearchHistory;
import Baetube_backEnd.mapper.SearchHistoryMapper;

public class SearchHistoryInsertService
{
	@Autowired
	private SearchHistoryMapper searchHistoryMapper;
	
	public boolean insert(SearchHistory request)
	{
		searchHistoryMapper.insert(request);
		
		return true;
	}
}
