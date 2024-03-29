package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.JwtTokenProvider;
import Baetube_backEnd.service.jwt.JwtAccessTokenService;
import Baetube_backEnd.service.jwt.JwtTokenDataExtractService;

@Configuration
public class JwtConfig
{
	@Bean
	public JwtTokenProvider jwtTokenProvider()
	{
		return new JwtTokenProvider();
	}
	
	@Bean
	public JwtAccessTokenService jwtAccessTokenService()
	{
		return new JwtAccessTokenService();
	}
	
	@Bean
	public JwtTokenDataExtractService jwtTokenDataExtractService()
	{
		return new JwtTokenDataExtractService();
	}

}
