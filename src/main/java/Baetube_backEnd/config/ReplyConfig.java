package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.reply.ReplyInsertService;
import Baetube_backEnd.service.reply.ReplySelectService;
import Baetube_backEnd.service.reply.ReplyUpdateService;

@Configuration
public class ReplyConfig
{
	@Bean
	public ReplyInsertService replyInsertService()
	{
		return new ReplyInsertService();
	}
	
	@Bean
	public ReplySelectService replySelectService()
	{
		return new ReplySelectService();
	}
	
	@Bean
	public ReplyUpdateService replyUpdateService()
	{
		return new ReplyUpdateService();
	}
	
}
