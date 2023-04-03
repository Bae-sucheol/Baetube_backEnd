package Baetube_backEnd.service.subscribe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeSelectService
{
	@Autowired
	private SubscribeMapper subscribeMapper;
	
	@Transactional
	public Subscribers select(Integer channelId, Integer subscriberId)
	{
		Subscribers subscribers = subscribeMapper.select(channelId, subscriberId);
		
		if(subscribers == null)
		{
			throw new NullSubscriberException();
		}
		
		return subscribers;
	}
	
	@Transactional
	public List<String> selectChannelSubscribersToken(Integer channelId)
	{
		List<String> subscribersTokens = subscribeMapper.selectChannelSubscribers(channelId);
		
		return subscribersTokens;
	}
	
	@Transactional
	public List<Integer> selectChannelSubscribersUserId(Integer channelId)
	{
		List<Integer> subscribersUserId = subscribeMapper.selectChannelSubscribersUserId(channelId);
		
		return subscribersUserId;
	}
}
