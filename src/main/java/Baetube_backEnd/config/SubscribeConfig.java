package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.subscribe.SubscribeInsertService;
import Baetube_backEnd.service.subscribe.SubscribeSelectService;
import Baetube_backEnd.service.subscribe.SubscribeDeleteService;

@Configuration
public class SubscribeConfig
{
	@Bean
	public SubscribeDeleteService subscribeDeleteService()
	{
		return new SubscribeDeleteService();
	}
	
	@Bean
	public SubscribeInsertService subscribeInsertService()
	{
		return new SubscribeInsertService();
	}
	
	@Bean
	public SubscribeSelectService subscribeSelectService()
	{
		return new SubscribeSelectService();
	}
}
