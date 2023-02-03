package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Notification;

public interface NotificationMapper
{
	public Notification select(@Param("userId") Integer userId, @Param("contentsId") Long contentsId);
	public void delete(@Param("userId") Integer userId, @Param("contentsId") Long contentsId);
}
