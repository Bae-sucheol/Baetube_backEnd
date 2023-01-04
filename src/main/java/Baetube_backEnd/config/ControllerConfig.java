package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestChannelController;
import Baetube_backEnd.controller.RestCommunityController;
import Baetube_backEnd.controller.RestHistoryController;
import Baetube_backEnd.controller.RestUserController;
import Baetube_backEnd.controller.RestVideoController;

@Configuration
public class ControllerConfig
{
	
	@Bean
	public RestUserController restUserController()
	{
		return new RestUserController();
	}
	
	@Bean
	public RestVideoController restVideoController()
	{
		return new RestVideoController();
	}
	
	@Bean
	public RestChannelController restChannelController()
	{
		return new RestChannelController();
	}
	
	@Bean
	public RestCommunityController restCommunityController()
	{
		return new RestCommunityController();
	}
	
	@Bean
	public RestHistoryController restHistoryController()
	{
		return new RestHistoryController();
	}
}
