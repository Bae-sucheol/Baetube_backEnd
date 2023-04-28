package Baetube_backEnd.service.channel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.exception.NullChannelException;
import Baetube_backEnd.mapper.ChannelMapper;

public class ChannelSelectService
{
	@Autowired
	private ChannelMapper channelMapper;
	
	@Transactional
	public List<Channel> selectByKeywords(String request) throws NullChannelException
	{
		List<Channel> channelList = channelMapper.selectByKeywords(request);
		
		if(channelList == null || channelList.isEmpty())
		{
			throw new NullChannelException();
		}
		
		return channelList;
	}
}
