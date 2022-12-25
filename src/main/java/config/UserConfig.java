package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.UserRegisterService;

@Configuration
public class UserConfig
{

	@Bean
	public UserRegisterService userRegisterService()
	{
		return new UserRegisterService();
	}
	
}
