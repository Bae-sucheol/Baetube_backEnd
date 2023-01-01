package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestUserController;
import Baetube_backEnd.service.UserRegisterService;

@Configuration
public class UserConfig
{

	@Bean
	public RestUserController restUserController()
	{
		return new RestUserController();
	}
	
	@Bean
	public UserRegisterService userRegisterService()
	{
		return new UserRegisterService();
	}
	
}
