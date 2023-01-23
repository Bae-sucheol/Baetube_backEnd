package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestChannelController;
import Baetube_backEnd.controller.RestCommunityController;
import Baetube_backEnd.controller.RestContentsController;
import Baetube_backEnd.controller.RestFileController;
import Baetube_backEnd.controller.RestHistoryController;
import Baetube_backEnd.controller.RestHistoryControllerTest;
import Baetube_backEnd.controller.RestHlsController;
import Baetube_backEnd.controller.RestNestedReplyController;
import Baetube_backEnd.controller.RestPlaylistController;
import Baetube_backEnd.controller.RestRateController;
import Baetube_backEnd.controller.RestReplyController;
import Baetube_backEnd.controller.RestSearchHistoryController;
import Baetube_backEnd.controller.RestSubscribeController;
import Baetube_backEnd.controller.RestUserController;
import Baetube_backEnd.controller.RestVideoController;
import Baetube_backEnd.controller.RestVoteController;

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
	
	@Bean
	public RestContentsController restContentsController()
	{
		return new RestContentsController();
	}
	
	@Bean
	public RestReplyController restReplyController()
	{
		return new RestReplyController();
	}
	
	@Bean
	public RestNestedReplyController restNestedReplyController()
	{
		return new RestNestedReplyController();
	}
	
	@Bean
	public RestPlaylistController restPlaylistController()
	{
		return new RestPlaylistController();
	}
	
	@Bean
	public RestRateController restRateController()
	{
		return new RestRateController();
	
	}
	
	@Bean
	public RestSearchHistoryController restSearchHistoryController()
	{
		return new RestSearchHistoryController();
	}
	
	@Bean
	public RestSubscribeController restSubscribeController()
	{
		return new RestSubscribeController();
	}
	
	@Bean
	public RestVoteController restVoteController()
	{
		return new RestVoteController();
	}
	
	@Bean
	public RestFileController restFileController()
	{
		return new RestFileController();
	}
	
	@Bean
	public RestHlsController restHlsController()
	{
		return new RestHlsController();
	}
	      
}
