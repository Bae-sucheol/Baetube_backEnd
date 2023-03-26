package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.fcm.FCMInitializer;

@Configuration
public class FCMConfig
{
	@Bean
	public FCMInitializer fcmInitializer()
	{
		return new FCMInitializer();
	}
}
