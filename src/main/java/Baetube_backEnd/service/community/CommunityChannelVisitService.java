package Baetube_backEnd.service.community;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import Baetube_backEnd.dto.Channel;
import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.ChannelMapper;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunityChannelVisitService
{
	@Autowired
	private CommunityMapper communityMapper;
	@Autowired
	private ChannelMapper channelMapper;

	public void setCommunityMapper(CommunityMapper communityMapper)
	{
		this.communityMapper = communityMapper;
	}
	
	public List<Community> selectCommunity(Integer request)
	{
		List<Community> communityList = communityMapper.selectByChannel(request);
		
		if(communityList == null)
		{
			throw new NullCommunityException();
		}
		
		return communityList;
	}
}
