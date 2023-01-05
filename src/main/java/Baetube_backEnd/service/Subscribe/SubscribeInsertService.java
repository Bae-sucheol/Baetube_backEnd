package Baetube_backEnd.service.Subscribe;

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

	public void setSubscribeMapper(SubscribeMapper subscribeMapper)
	{
		this.subscribeMapper = subscribeMapper;
	}
	
	@Transactional
	public boolean insert(Subscribers request)
	{
		Subscribers subscribers = subscribeMapper.select(request);
		
		if(subscribers != null)
		{
			throw new DuplicateSubscriberException();
		}
		
		subscribeMapper.subscribe(request);
		
		return true;
	}
}
