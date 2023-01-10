package Baetube_backEnd.service.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelSubscribersService
{
	@Autowired
	private ChannelMapper channelMapper;
	
	public List<Channel> selectSubscribers(Integer request)
	{
		List<Channel> subscriberList = channelMapper.selectSubscribers(request);
		
		if(subscriberList == null)
		{
			throw new NullChannelException();
		}
		
		return subscriberList;
	}
}
