package Baetube_backEnd.service.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelUpdateService
{
	@Autowired
	private ChannelMapper channelMapper;

	public void setChannelMapper(ChannelMapper channelMapper)
	{
		this.channelMapper = channelMapper;
	}
	
	@Transactional
	public boolean updateChannel(Channel request)
	{
		Channel channel = channelMapper.select(request.getChannelId());
		
		if(channel == null)
		{
			throw new NullChannelException();
		}
		
		channelMapper.update(channel, request);
		
		return true;
	}
}
