package Baetube_backEnd.service.channel;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelDeleteService
{
	@Autowired
	private ChannelMapper channelMapper;

	public void setChannelMapper(ChannelMapper channelMapper)
	{
		this.channelMapper = channelMapper;
	}
	
	public boolean deleteChannel(Integer channelId)
	{
		channelMapper.delete(channelId);
		
		return true;
	}
}
