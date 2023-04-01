package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Contents;

public interface ContentsMapper
{
	public void delete(@Param("contentsId") Long contentsId, @Param("type") Integer type);
	public Contents selectByContentsId(@Param("contentsId") Long contentsId);
	public String selectFCMTokenByContentsId(@Param("contentsId") Long contentsId, @Param("fcmToken") String fcmToken,
			@Param("type") Integer type, @Param("resultId") Integer resultId);
}
