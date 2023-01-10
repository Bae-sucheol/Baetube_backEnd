package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

public interface NotificationMapper
{
	public void delete(@Param("userId") Integer userId, @Param("contentsId") Integer contentsId);
}
