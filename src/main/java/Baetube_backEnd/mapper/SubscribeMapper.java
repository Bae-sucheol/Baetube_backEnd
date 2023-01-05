package Baetube_backEnd.mapper;

import org.apache.ibatis.annotations.Param;

import Baetube_backEnd.dto.Subscribers;

public interface SubscribeMapper
{
	//public void subscribe(Integer channelId, Integer subscriberId);
	//public void unSubscribe(Integer channelId, Integer subscriberId);
	//public Subscribers select(Integer channelId, Integer subscriberId);
	
	public void subscribe(@Param("subscribers") Subscribers subscribers);
	public void unSubscribe(@Param("subscribers") Subscribers subscribers);
	public Subscribers select(@Param("subscribers") Subscribers subscribers);
}
