package Baetube_backEnd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import Baetube_backEnd.service.notification.NotificationDeleteService;

@Configuration
public class NotificationConfig
{
	@Bean
	public NotificationDeleteService notificationDeleteService()
	{
		return new NotificationDeleteService();
	}
}
