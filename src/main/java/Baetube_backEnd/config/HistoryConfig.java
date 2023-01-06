package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.history.HistoryDeleteService;

@Configuration
public class HistoryConfig
{
	@Bean
	public HistoryDeleteService historyDeleteService()
	{
		return new HistoryDeleteService();
	}
}
