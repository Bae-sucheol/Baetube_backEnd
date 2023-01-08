package Baetube_backEnd.service.subscribe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Subscribers;
import Baetube_backEnd.exception.NullSubscriberException;
import Baetube_backEnd.mapper.SubscribeMapper;

public class SubscribeDeleteService
{
	@Autowired
	private SubscribeMapper subscribeMapper;

	public void setSubscribeMapper(SubscribeMapper subscribeMapper)
	{
		this.subscribeMapper = subscribeMapper;
	}
	
	@Transactional
	public boolean delete(Subscribers request)
	{
		Subscribers subscribers = subscribeMapper.select(request);
		
		if(subscribers == null)
		{
			throw new NullSubscriberException();
		}
		
		subscribeMapper.unSubscribe(request);
		
		return true;
	}
}
