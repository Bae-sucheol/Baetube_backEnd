package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.notification.NotificationDeleteService;
import Baetube_backEnd.service.notification.NotificationInsertService;

@Configuration
public class NotificationConfig
{
	@Bean
	public NotificationDeleteService notificationDeleteService()
	{
		return new NotificationDeleteService();
	}
	
	@Bean
	public NotificationInsertService notificationInsertService()
	{
		return new NotificationInsertService();
	}
}
