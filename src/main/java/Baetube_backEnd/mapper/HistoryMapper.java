package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.History;

public interface HistoryMapper
{
	public void insert(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
	public void delete(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
	public History select(@Param("userId") Integer userId, @Param("videoId") Integer videoId);
}
