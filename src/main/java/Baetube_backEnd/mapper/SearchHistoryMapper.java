package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.SearchHistory;

public interface SearchHistoryMapper
{
	public void insert(@Param("searchHistory") SearchHistory searchHistory);
	public void delete(@Param("searchHistory") SearchHistory searchHistory);
	public List<SearchHistory> selectAll(Integer userId);
	public SearchHistory select(@Param("searchHistory") SearchHistory searchHistory);
}
