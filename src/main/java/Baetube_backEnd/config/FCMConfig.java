package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.fcm.FCMInitializer;
import Baetube_backEnd.service.fcm.FCMSaveService;
import Baetube_backEnd.service.fcm.FCMSendService;

@Configuration
public class FCMConfig
{
	@Bean
	public FCMInitializer fcmInitializer()
	{
		return new FCMInitializer();
	}
	
	@Bean
	public FCMSaveService fcmSaveService()
	{
		return new FCMSaveService();
	}
	
	@Bean
	public FCMSendService fcmSendService()
	{
		return new FCMSendService();
	}
}
