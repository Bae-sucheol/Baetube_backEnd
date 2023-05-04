package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.notification.NotificationCheckService;
import Baetube_backEnd.service.notification.NotificationDeleteService;
import Baetube_backEnd.service.notification.NotificationInsertService;
import Baetube_backEnd.service.notification.NotificationSelectService;

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
	
	@Bean
	public NotificationSelectService notificationSelectService()
	{
		return new NotificationSelectService();
	}
	
	@Bean
	public NotificationCheckService notificationCheckService()
	{
		return new NotificationCheckService();
	}
}
