package Baetube_backEnd.service.community;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import Baetube_backEnd.dto.Community;
import Baetube_backEnd.exception.NullCommunityException;
import Baetube_backEnd.mapper.CommunityMapper;

public class CommunitySelectService
{
	@Autowired
	private CommunityMapper communityMapper;
	
	@Transactional
	public Community selectCommunity(Integer request) throws IOException
	{
		Community community = communityMapper.selectByCommunityId(request);
		
		if(community == null)
		{
			throw new NullCommunityException();
		}
		
		return community;
	}
	
	@Transactional
	public List<Community> selectSubscribersCommunity(Integer request)
	{
		List<Community> communityList = communityMapper.selectSubscribersCommunity(request);
		
		if(communityList == null || communityList.isEmpty())
		{
			throw new NullCommunityException();
		}
		
		return communityList;
	}
}
