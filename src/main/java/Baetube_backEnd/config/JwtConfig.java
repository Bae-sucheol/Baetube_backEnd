package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.JwtTokenProvider;

@Configuration
public class JwtConfig
{
	@Bean
	public JwtTokenProvider jwtTokenProvider()
	{
		return new JwtTokenProvider();
	}
}
