package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Notification;

public interface NotificationMapper
{
	public Notification select(@Param("userId") Integer userId, @Param("contentsId") Long contentsId);
	public void delete(@Param("userId") Integer userId, @Param("contentsId") Long contentsId);
	public void insert(@Param("subscribersUserId") List<Integer> subscribersUserId, @Param("contentsId") Long contentsId);
	
}
