package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.rate.RateService;

@Configuration
public class RateConfig
{
	@Bean
	public RateService rateService()
	{
		return new RateService();
	}
}
