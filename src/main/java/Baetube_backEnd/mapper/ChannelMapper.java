package Baetube_backEnd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Subscribers;

public interface ChannelMapper
{
	public void insert(@Param("channel") Channel channel);
	public void delete(@Param("channelId") Integer channelId);
	public void update(@Param("oldChannel") Channel oldChannel, @Param("newChannel") Channel newChannel);
	public void updateSubscribes(@Param("subscribers") List<Subscribers> subscribers, @Param("value") Integer value);
	public void updateVideoCount(@Param("channelId") Integer channelId, @Param("value") Integer value);
	public Channel select(@Param("channelId") Integer channelId);	
	public List<Channel> selectSubscribers(@Param("channelId") Integer channelId);
	public List<Channel> selectChannelByUserEmail(@Param("email") String email);
	public List<Channel> selectByKeywords(@Param("keywords") String keywords);
}
