package Baetube_backEnd.service.notification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.exception.NullNotificationException;
import Baetube_backEnd.mapper.NotificationMapper;

public class NotificationInsertService
{
	@Autowired
	private NotificationMapper notificationMapper;
	
	@Transactional
	public boolean insert(List<Integer> subscribersUserId, Long contentsId) throws NullNotificationException
	{
		notificationMapper.insert(subscribersUserId, contentsId);
		
		return true;
	}
}
