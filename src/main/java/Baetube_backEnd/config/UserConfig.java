package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestUserController;
import Baetube_backEnd.service.ChangePasswordService;
import Baetube_backEnd.service.UserLoginService;
import Baetube_backEnd.service.UserRegisterService;
import Baetube_backEnd.service.UserUnregisterService;

@Configuration
public class UserConfig
{

	@Bean
	public UserRegisterService userRegisterService()
	{
		return new UserRegisterService();
	}
	
	@Bean
	public UserUnregisterService userUnregisterService()
	{
		return new UserUnregisterService();
	}
	
	@Bean
	public UserLoginService userLoginService()
	{
		return new UserLoginService();
	}
	
	@Bean
	public ChangePasswordService changePasswordService()
	{
		return new ChangePasswordService();
	}
	
}
