package Baetube_backEnd.service.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.DuplicateSubscriberException;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeInsertService
{
	@Autowired
	private SubscribeMapper subscribeMapper;
	
	@Transactional
	public boolean insert(Subscribers request)
	{
		Subscribers subscribers = subscribeMapper.select(request.getChannelId(), request.getSubscriberId());
		
		if(subscribers != null)
		{
			throw new DuplicateSubscriberException();
		}
		
		subscribeMapper.subscribe(request);
		
		return true;
	}
}
