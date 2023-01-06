package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.contents.ContentsDeleteService;

@Configuration
public class ContentsConfig
{
	@Bean
	public ContentsDeleteService contentsDeleteService()
	{
		return new ContentsDeleteService();
	}
}
