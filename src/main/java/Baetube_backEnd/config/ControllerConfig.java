package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestUserController;

@Configuration
public class ControllerConfig
{
	
	@Bean
	public RestUserController restUserController()
	{
		return new RestUserController();
	}
	
}
