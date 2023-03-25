package Baetube_backEnd.service.subscribe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeDeleteService
{
	@Autowired
	private SubscribeMapper subscribeMapper;
	@Autowired
	private ChannelMapper channelMapper;
	
	@Transactional
	public boolean delete(List<Subscribers> request)
	{
		List<Subscribers> subscribers = subscribeMapper.selectSubscribersList(request);
		
		if(subscribers == null)
		{
			throw new NullSubscriberException();
		}
		
		subscribeMapper.unSubscribe(request);
		channelMapper.updateSubscribes(subscribers, -1);
		
		return true;
	}
}
