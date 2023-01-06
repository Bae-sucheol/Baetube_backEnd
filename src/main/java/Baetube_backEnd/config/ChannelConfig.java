package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.channel.ChannelDeleteService;
import Baetube_backEnd.service.channel.ChannelInsertService;
import Baetube_backEnd.service.channel.ChannelSubscribersService;
import Baetube_backEnd.service.channel.ChannelUpdateService;
import Baetube_backEnd.service.channel.ChannelVisitService;

@Configuration
public class ChannelConfig
{
	@Bean
	public ChannelDeleteService channelDeleteService()
	{
		return new ChannelDeleteService();
	}
	
	@Bean
	public ChannelInsertService channelInsertService()
	{
		return new ChannelInsertService();
	}
	
	@Bean
	public ChannelSubscribersService channelSubscribersService()
	{
		return new ChannelSubscribersService();
	}
	
	@Bean
	public ChannelUpdateService channelUpdateService()
	{
		return new ChannelUpdateService();
	}
	
	@Bean
	public ChannelVisitService channelVisitService()
	{
		return new ChannelVisitService();
	}
}
