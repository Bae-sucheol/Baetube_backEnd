package Baetube_backEnd.service.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Notification;
import Baetube_backEnd.exception.NullNotificationException;
import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationDeleteService
{
	@Autowired
	private NotificationMapper notificationMapper;
	
	@Transactional
	public boolean delete(Notification request) throws NullNotificationException
	{
		Notification notification = notificationMapper.select(request.getUserId(), request.getContentsId());
		
		if(notification == null)
		{
			throw new NullNotificationException();
		}
		
		notificationMapper.delete(request.getUserId(), request.getContentsId());
		
		return true;
	}
}
