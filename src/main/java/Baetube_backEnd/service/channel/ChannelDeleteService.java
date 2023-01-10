package Baetube_backEnd.service.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelDeleteService
{
	@Autowired
	private ChannelMapper channelMapper;

	@Transactional
	public boolean deleteChannel(Integer request)
	{
		Channel channel = channelMapper.select(request);
		
		if(channel == null)
		{
			throw new NullChannelException();
		}
		
		channelMapper.delete(request);
		
		return true;
	}
}
