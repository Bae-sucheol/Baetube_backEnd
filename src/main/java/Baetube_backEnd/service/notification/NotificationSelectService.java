package Baetube_backEnd.service.notification;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Notification;
import Baetube_backEnd.exception.NullNotificationException;
import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationSelectService
{
	@Autowired
	private NotificationMapper notificationMapper;
	
	@Transactional
	public List<HashMap<String, String>> selectVideoNotifications(Integer request) throws NullNotificationException
	{
		List<HashMap<String, String>> notifications = notificationMapper.selectVideoNotifications(request);
		
		if(notifications == null || notifications.isEmpty())
		{
			throw new NullNotificationException();
		}
		
		return notifications;
	}
	
	@Transactional
	public List<HashMap<String, String>> selectCommunityNotifications(Integer request) throws NullNotificationException
	{
		List<HashMap<String, String>> notifications = notificationMapper.selectCommunityNotifications(request);
		
		if(notifications == null || notifications.isEmpty())
		{
			throw new NullNotificationException();
		}
		
		return notifications;
	}
	
	
	@Transactional
	public List<Notification> selectNewUserNotification(Integer request) throws NullNotificationException
	{
		List<Notification> notifications = notificationMapper.selectNewUserNotifications(request);
		
		if(notifications == null || notifications.isEmpty())
		{
			throw new NullNotificationException();
		}
		
		return notifications;
	}
	
}
