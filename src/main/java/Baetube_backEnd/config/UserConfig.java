package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.controller.RestUserController;
import Baetube_backEnd.service.user.ChangePasswordService;
import Baetube_backEnd.service.user.UserLoginService;
import Baetube_backEnd.service.user.UserRegisterService;
import Baetube_backEnd.service.user.UserUnregisterService;

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
