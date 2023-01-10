package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Channel;

public interface ChannelMapper
{
	public void insert(@Param("channel") Channel channel);
	public void delete(@Param("channelId") Integer channelId);
	public void update(@Param("oldChanel") Channel oldChannel, @Param("newChannel") Channel newChannel);
	public void updateSubscribes(@Param("channelId") Integer channelId, @Param("value") Integer value);
	public void updateVideoCount(@Param("channelId") Integer channelId, @Param("value") Integer value);
	public Channel select(@Param("channelId") Integer channelId);	
	public List<Channel> selectSubscribers(@Param("channelId") Integer channelId);
}
