package Baetube_backEnd.service.subscribe;

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
	public Subscribers select(Subscribers request)
	{
		Subscribers subscribers = subscribeMapper.select(request);
		
		if(subscribers == null)
		{
			throw new NullSubscriberException();
		}
		
		return subscribers;
	}
}
