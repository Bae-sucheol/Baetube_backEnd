package Baetube_backEnd.service.channel;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelVisitService
{
	@Autowired
	private ChannelMapper channelMapper;

	public void setChannelMapper(ChannelMapper channelMapper)
	{
		this.channelMapper = channelMapper;
	}
	
	public Channel selectChannel(Integer channelId)
	{
		Channel channel = channelMapper.select(channelId);
		
		if(channel == null)
		{
			throw new NullChannelException();
		}
		
		return channel;
	}
}
