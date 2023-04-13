package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.community.CommunityChannelVisitService;
import Baetube_backEnd.service.community.CommunityDeleteService;
import Baetube_backEnd.service.community.CommunityInsertService;
import Baetube_backEnd.service.community.CommunityUpdateService;

@Configuration
public class CommunityConfig
{
	@Bean
	public CommunityChannelVisitService communityChannelVisitService()
	{
		return new CommunityChannelVisitService();
	}
	
	@Bean
	public CommunityDeleteService communityDeleteService()
	{
		return new CommunityDeleteService();
	}
	
	@Bean
	public CommunityInsertService communityInsertService()
	{
		return new CommunityInsertService();
	}
	
	@Bean
	public CommunityUpdateService communityUpdateService()
	{
		return new CommunityUpdateService();
	}
}	
