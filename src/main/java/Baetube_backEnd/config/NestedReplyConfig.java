package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.nestedreply.NestedReplySelectService;
import Baetube_backEnd.service.nestedreply.NestedReplyUpdateService;
import Baetube_backEnd.service.nestedreply.NestedReplyInsertService;

@Configuration
public class NestedReplyConfig
{
	@Bean
	public NestedReplyInsertService nestedReplyInsertService()
	{
		return new NestedReplyInsertService();
	}
	
	@Bean
	public NestedReplySelectService nestedReplySelectService()
	{
		return new NestedReplySelectService();
	}
	
	@Bean
	public NestedReplyUpdateService nestedReplyUpdateService()
	{
		return new NestedReplyUpdateService();
	}
}
