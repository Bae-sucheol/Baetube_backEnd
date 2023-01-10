package Baetube_backEnd.service.channel;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelInsertService
{
	@Autowired
	private ChannelMapper channelMapper;

	
	public boolean insertChannel(Channel request)
	{
		channelMapper.insert(request);
		
		return true;
	}
	
}
