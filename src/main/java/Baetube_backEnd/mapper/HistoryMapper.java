package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

public interface HistoryMapper
{
	public void insert(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
	public void delete(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
}
