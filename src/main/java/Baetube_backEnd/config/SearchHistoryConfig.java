package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.searchhistory.SearchHistoryDeleteService;
import Baetube_backEnd.service.searchhistory.SearchHistoryInsertService;
import Baetube_backEnd.service.searchhistory.SearchHistorySelectService;

@Configuration
public class SearchHistoryConfig
{
	@Bean
	public SearchHistoryDeleteService searchHistoryDeleteService()
	{
		return new SearchHistoryDeleteService();
	}
	
	@Bean
	public SearchHistoryInsertService searchHistoryInsertService()
	{
		return new SearchHistoryInsertService();
	}
	
	@Bean
	public SearchHistorySelectService searchHistorySelectService()
	{
		return new SearchHistorySelectService();
	}
}
