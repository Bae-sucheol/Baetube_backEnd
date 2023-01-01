package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Channel;

public interface ChannelMapper
{
	public void insert(@Param("channel") Channel channel);
	public void delete(Integer channelId);
	public void update(@Param("oldChanel") Channel oldChannel, @Param("newChannel") Channel newChannel);
	public void updateSubscribes(Integer channelId, Integer value);
	public void updateVideoCount(Integer channelId, Integer value);
	public Channel select(Integer channelId);	
}
