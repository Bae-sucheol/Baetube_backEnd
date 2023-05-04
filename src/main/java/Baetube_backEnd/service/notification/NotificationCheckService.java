package Baetube_backEnd.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationCheckService
{
	@Autowired
	private NotificationMapper notificationMapper;
	
	@Transactional
	public void checkNotifications(Integer request)
	{
		notificationMapper.checkNotifications(request);
	}
}
